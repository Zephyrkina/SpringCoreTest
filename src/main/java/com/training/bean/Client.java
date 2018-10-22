package com.training.bean;

import org.springframework.beans.factory.annotation.Value;


public class Client {
    private String id;
    private String fullName;
    private String greeting;


    public Client() {
    }

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    @Value("Hi there")
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}