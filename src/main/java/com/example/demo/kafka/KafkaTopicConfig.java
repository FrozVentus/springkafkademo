package com.example.demo.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.demo.service.BookController;

@Configuration
public class KafkaTopicConfig {
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    
    @Bean
    public NewTopic topic1() {
        log.info("Creating topic: query");
        return TopicBuilder.name(KafkaTopic.QUERY.getTopic()).build();
    }
    
    @Bean
    public NewTopic topic2() {
        log.info("Creating topic: response");
        return TopicBuilder.name(KafkaTopic.RESPONSE.getTopic()).build();
    }
}
