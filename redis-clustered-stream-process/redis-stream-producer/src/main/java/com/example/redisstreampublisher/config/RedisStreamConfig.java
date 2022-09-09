package com.example.redisstreampublisher.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class RedisStreamConfig {

    @Value("${stream.key}")
    private String streamKey;

     @Autowired
     private ClusterProperties properties;

//    @Value("${host.redis}")
//    private String redisHost;
//
//    @Value("${port.redis}")
//    private int redisPort;

   /* cluster Configuration with jedis for synchroneous process*/

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
////        RedisStandaloneConfiguration factory = new RedisStandaloneConfiguration();
////        factory.setHostName(redisHost);
////        factory.setPort(redisPort);
////        return new JedisConnectionFactory(factory);
//        return new JedisConnectionFactory(new RedisClusterConfiguration(properties.getNodes()));
//    }


    /*Cluster configuration with Lettuce*/

   /* @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("192.168.68.107");
        configuration.setPort(6379);
        return new LettuceConnectionFactory(configuration);
    }*/

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        final LettuceConnectionFactory factory = new LettuceConnectionFactory(
                new RedisClusterConfiguration(properties.getNodes()));
        return factory;
    }



    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        return redisTemplate;

    }
}
