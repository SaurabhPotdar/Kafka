package com.tce.kafka.producer.controller;

import com.tce.kafka.producer.dto.Message;
import com.tce.kafka.producer.service.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final ProducerService producerService;

    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public ResponseEntity<?> sendMessage(@RequestParam("key") String key, @RequestBody Message message){
        producerService.publishToQueue(key, message);
        return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
    }

}
