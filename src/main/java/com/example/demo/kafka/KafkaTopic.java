package com.example.demo.kafka;

public enum KafkaTopic {
    QUERY("query"),
    RESPONSE("response");
    
    private String topic;
    
    KafkaTopic(String topic) {
        this.topic = topic;
    }
    
    public String getTopic() {
        return this.topic;
    }
}
