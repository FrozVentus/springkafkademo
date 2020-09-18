package com.example.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.example.demo.controller.BookController;

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
