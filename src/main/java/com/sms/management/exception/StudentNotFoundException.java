package com.sms.management.exception;

import lombok.Getter;

@Getter
public class StudentNotFoundException extends RuntimeException {
    private final String message;

    public StudentNotFoundException(String message) {
        this.message = message;
    }
}
