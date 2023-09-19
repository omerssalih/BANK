package com.sms.management.exception;

import lombok.Getter;

@Getter
public class SMSException extends RuntimeException {
  private final String message;

  public SMSException(String message) {
    this.message = message;
  }
}
