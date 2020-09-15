package com.example.demo.entity;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Search {

    private @Id @GeneratedValue Long id;
    private String code;
    private Status status;
    
    Search() {}
    
    Search(String code, Status status) {
        this.code = code;
        this.status = status;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public Status getStatus() {
        return this.status;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Search)) {
            return false;
        }
        Search search = (Search) o;
        return Objects.equals(this.id,  search.id) && Objects.equals(this.code, search.code)
                && this.status == search.status;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.code, this.status);
    }
    
    @Override
    public String toString() {
        return "Search{" + "id=" + this.id + ", code='" + this.code + "\'" + ", status=" 
                + this.status + "}";
    }
}
