package com.sms.management.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
    private final String message;
    public AuthenticationException(String message) {
        this.message = message;
    }
}
