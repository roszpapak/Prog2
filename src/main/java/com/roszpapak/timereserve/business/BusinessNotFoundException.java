package com.roszpapak.timereserve.business;

public class BusinessNotFoundException extends Exception {

    public BusinessNotFoundException() {
    }

    public BusinessNotFoundException(String message) {
        super(message);
    }
}
