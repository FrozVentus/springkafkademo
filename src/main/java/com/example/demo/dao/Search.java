package com.example.demo.dao;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Search {

    @NotNull
    @Pattern(regexp = "[0-9]+$")
    private String code;
    
    public Search() {
        this.code = "";
    }
    
    public Search(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
        return Objects.equals(this.code, search.code);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }
    
    @Override
    public String toString() {
        return "Search{code='" + this.code +"\'}";
    }
}
