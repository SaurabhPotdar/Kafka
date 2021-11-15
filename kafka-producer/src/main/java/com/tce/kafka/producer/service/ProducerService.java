package com.tce.kafka.producer.service;

import com.tce.kafka.producer.dto.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    private static final String TOPIC = "quickstart-events";

    public ProducerService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishToQueue(String key, Message value) {
        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send(TOPIC, key, value);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                System.out.println("Sent message with offset=" + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed");
            }
        });
    }
}
