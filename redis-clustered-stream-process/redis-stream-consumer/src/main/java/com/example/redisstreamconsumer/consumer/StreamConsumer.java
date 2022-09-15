package com.example.redisstreamconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class StreamConsumer implements StreamListener<String, ObjectRecord<String,String>> {

    @Value("${stream.key}")
    private String streamKey;

    @Value("${stream.size}")
    private Long streamSize;
    private static Logger log = LoggerFactory.getLogger(StreamConsumer.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void onMessage(ObjectRecord<String, String> message) {
        log.info("Consumed"+message);
        this.redisTemplate.opsForStream().trim(streamKey,streamSize);

    }
}
