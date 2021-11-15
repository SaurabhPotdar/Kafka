package com.tce.kafka.consumer.service;

import com.tce.kafka.consumer.dto.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    private static final String TOPIC = "quickstart-events";

    @KafkaListener(topics = TOPIC)
    public void messageListener(ConsumerRecord<String, Message> consumerRecord) {

        String key = consumerRecord.key();
        Message value = consumerRecord.value();
        int partition = consumerRecord.partition();

        System.out.println("Consumed message : " + value
                + " with key : " + key
                + " from partition : " + partition);
    }

}
