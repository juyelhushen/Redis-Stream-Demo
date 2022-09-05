package com.example.redisstreampublisher.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProducerImp {

    private static Logger log = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/publisher",method = RequestMethod.POST)
    public void publish(@RequestBody String message){
        log.info("publishing >>" + message);
        producer.publishEvent(message.toString());
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getMsg(){
        return "hello-juyel";
    }

}
