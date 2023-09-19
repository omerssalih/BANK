package com.sms.management.exception;

import lombok.Getter;

@Getter
public class TeacherNotFoundException extends RuntimeException {
  private final String message;

  public TeacherNotFoundException(String message) {
    this.message = message;
  }
}
