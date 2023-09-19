package com.sms.management.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class CourseNotFoundException extends RuntimeException {
        private final String message;

    public CourseNotFoundException(String message) {

    this.message = message;
    }
}
