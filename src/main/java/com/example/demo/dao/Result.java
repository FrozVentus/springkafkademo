package com.example.demo.dao;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Result {

    private String content;
    
    public Result() {
    }
    
    public Result(String content) {
        this.content = content;
    }
    
    public String getValue() {
        return this.content;
    }
    
    public void setValue(String content) {
        this.content = content;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Result)) {
            return false;
        }
        Result result = (Result) o;
        return Objects.equals(this.content, result.content);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.content);
    }
    
    @Override
    public String toString() {
        return "Result{content='" + this.content +"\'}";
    }
}
