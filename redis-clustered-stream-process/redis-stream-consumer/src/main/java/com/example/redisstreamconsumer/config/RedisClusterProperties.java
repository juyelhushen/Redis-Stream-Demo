package com.example.redisstreamconsumer.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterProperties {

    List<String> nodes;

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}