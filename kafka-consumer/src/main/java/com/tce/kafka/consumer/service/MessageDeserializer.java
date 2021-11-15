package com.tce.kafka.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tce.kafka.consumer.dto.Message;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class MessageDeserializer implements Deserializer<Message> {
    @Override
    public Message deserialize(String topic, byte[] data) {

        Message message = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(data, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
