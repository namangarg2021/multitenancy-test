package com.dlt.kafka;

import io.smallrye.common.annotation.Identifier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import lombok.extern.jbosslog.JBossLog;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@JBossLog
public class KafkaConfigBean {

    @ConfigProperty(name = "kafka.bootstrap.servers", defaultValue = "localhost:9092")
    String kafkaServers;
    @ConfigProperty(name = "kafka.sasl.jaas.config", defaultValue = "test")
    String kafkaJaasConfig;
    @ConfigProperty(name = "environment", defaultValue = "local")
    String environment;
    @ConfigProperty(name = "quarkus.application.name")
    String appName;
    @Produces
    @Identifier("kafkaConsumerConfig")
    public Map<String, Object> kafkaConfig() {
        log.debug("kafkaServers : " + kafkaServers);
        HashMap<String, Object> config = new HashMap<>();
        if(!"local".equalsIgnoreCase(environment)){
            config.put("security.protocol", "SASL_SSL");
            config.put("sasl.mechanism", "SCRAM-SHA-512");
            config.put("sasl.jaas.config", kafkaJaasConfig);
        }
        config.put(ConsumerConfig.GROUP_ID_CONFIG, environment + "." + appName);
        log.info("Initialized Kafka config");
        return config;
    }

}
