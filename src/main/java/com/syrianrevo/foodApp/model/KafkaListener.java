package com.syrianrevo.foodApp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.syrianrevo.foodApp.kafkaConfig.Constants;



@Component
public class KafkaListener {

    @Autowired
    private SimpMessagingTemplate template;

    @StreamListener(target = Constants.KAFKA_TOPIC)
    public void processMessage(Entry pushMessage){
        this.template.convertAndSend("/topic/pushNotification", pushMessage);
    }
}

