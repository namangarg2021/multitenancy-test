package com.dlt.kafka;

import com.dlt.entities.Product;
import com.dlt.tenant.TenantContext;
import com.dlt.tenant.TenantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.arc.Arc;
import io.quarkus.arc.InjectableContext;
import io.quarkus.arc.ManagedContext;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.jbosslog.JBossLog;
import org.apache.kafka.common.header.Header;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
@JBossLog
public class ProductRequestListener {
	
	@Inject
	TenantService tenantService;
	
	@Inject
	private ObjectMapper objectMapper;
	
	@Incoming("product-request")
	@Blocking
	@Acknowledgment(Acknowledgment.Strategy.POST_PROCESSING)
	public CompletionStage<Void> consume(KafkaRecord<String, String> record) {
		Header header = record.getHeaders().lastHeader("X-Tenant-ID");
		String tenantName = new String(header.value());
		String payload = record.getPayload();
		ManagedContext managedContext = Arc.container().requestContext();
		if(!managedContext.isActive())
			managedContext.activate();
		TenantContext.setCurrentTenant(tenantName);
		try {
			Product product = objectMapper.readValue(payload, Product.class);
			tenantService.addProduct(product);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}finally {
			if(managedContext.isActive())
				managedContext.terminate();
			TenantContext.clear();
		}
		return record.ack();
	}
}
