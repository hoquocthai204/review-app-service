package net.sparkminds.review.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.service.SendLogService;

@Service
@RequiredArgsConstructor
public class SendLogServiceImpl implements SendLogService {
    private final HttpServletRequest request;
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Value(value = "${kafka.topic_name}")
    private String kafkaTopicName;

    @Override
    public void sendingMessage(String email, String message) {
        String logMessage = String.format("%s, %s, %s", email, request.getRemoteHost(), message);
        kafkaTemplate.send(kafkaTopicName, logMessage);
    }

}
