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
    private KafkaTopic topic1 = KafkaTopic.QUERY;
    private KafkaTopic topic2 = KafkaTopic.RESPONSE;

    @Bean
    public NewTopic topic1() {
        log.info("Creating topic: " + topic1);
        return TopicBuilder.name(topic1.getTopic()).build();
    }
    
    @Bean
    public NewTopic topic2() {
        log.info("Creating topic: " + topic2);
        return TopicBuilder.name(topic2.getTopic()).build();
    }
}
