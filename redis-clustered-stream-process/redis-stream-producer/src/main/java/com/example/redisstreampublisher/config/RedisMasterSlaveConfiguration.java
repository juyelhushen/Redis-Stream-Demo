package com.example.redisstreampublisher.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisMasterSlaveConfiguration {

    /*@Autowired
    RedisInstance re*/

    private RedisInstance master;
    private List<RedisInstance> slaves;

    public RedisInstance getMaster() {
        return master;
    }

    public void setMaster(RedisInstance master) {
        this.master = master;
    }

    public List<RedisInstance> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<RedisInstance> slaves) {
        this.slaves = slaves;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();
        RedisStaticMasterReplicaConfiguration staticMasterReplicaConfiguration = new RedisStaticMasterReplicaConfiguration(this.getMaster().getHost(), this.getMaster().getPort());
        this.getSlaves().forEach(slave -> staticMasterReplicaConfiguration.addNode(slave.getHost(), slave.getPort()));
        return new LettuceConnectionFactory(staticMasterReplicaConfiguration, clientConfig);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        return redisTemplate;
    }
}
