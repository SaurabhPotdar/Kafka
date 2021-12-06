package com.tce.kafka.producer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.tce.kafka.schema.MessageKafka;

@Service
public class ProducerService {

    private final KafkaTemplate<String, MessageKafka> kafkaTemplate;

    private static final String TOPIC = "quickstart-events";

    public ProducerService(KafkaTemplate<String, MessageKafka> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishToQueue(String key, MessageKafka value) {
        ListenableFuture<SendResult<String, MessageKafka>> future = kafkaTemplate.send(TOPIC, key, value);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, MessageKafka> result) {
                System.out.println("Sent message with offset=" + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed");
            }
        });
    }
}
