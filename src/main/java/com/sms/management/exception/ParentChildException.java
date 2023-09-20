package com.sms.management.exception;

import lombok.Getter;

@Getter
public class ParentChildException extends RuntimeException {
  private final String message;

  public ParentChildException(String message) {
    this.message = message;
  }
}
