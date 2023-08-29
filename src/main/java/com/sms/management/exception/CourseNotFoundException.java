package com.sms.management.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class CourseNotFoundException extends RuntimeException {
        private final String message;

    public CourseNotFoundException(String message) {
        this.message = message;
    }
}
