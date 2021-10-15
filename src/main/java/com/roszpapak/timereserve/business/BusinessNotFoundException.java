package com.roszpapak.timereserve.business;

public class BusinessNotFoundException extends RuntimeException{

    public BusinessNotFoundException() {
    }

    public BusinessNotFoundException(String message) {
        super(message);
    }
}
