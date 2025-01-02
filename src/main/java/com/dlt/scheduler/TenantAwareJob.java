package com.dlt.scheduler;

import com.dlt.tenant.TenantContext;
import com.dlt.tenant.TenantService;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TenantAwareJob {
	
	@Inject
	TenantService tenantService;
	
	@Scheduled(every = "1m")
	void executeTenantJobs() {
		for (String tenantId : tenantService.getAllTenantIds()) {
			try {
				TenantContext.setCurrentTenant(tenantId);
				
				tenantService.processTenantJob(tenantId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				TenantContext.clear();
			}
		}
	}
}