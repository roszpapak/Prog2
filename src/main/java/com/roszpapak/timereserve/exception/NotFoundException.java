package com.roszpapak.timereserve.exception;

import com.roszpapak.timereserve.user.User;

public class NotFoundException extends Throwable {
    public NotFoundException(Class<User> userClass) {
    }

    public NotFoundException(String message) {
    }
}
