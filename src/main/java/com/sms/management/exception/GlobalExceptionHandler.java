package com.sms.management.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity studentNotFoundException(StudentNotFoundException exception) {
    log.error("student not found exception." + exception.getMessage());
    return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity courseNotFoundException(CourseNotFoundException exception) {
    log.error("course not found exception." + exception.getMessage());
    return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(TeacherNotFoundException.class)
  public ResponseEntity teacherNotFoundException(TeacherNotFoundException exception) {
    log.error("teacher not found exception." + exception.getMessage());
    return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity generalException(Exception exception) {
    log.error("Bilinmeyen bir hata oluştu." + exception.getMessage());
    return new ResponseEntity("Bilinmeyen bir hata oluştu", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SMSException.class)
  public ResponseEntity smsException(SMSException exception) {
    log.error("Bilinmeyen bir hata oluştu." + exception.getMessage());
    return new ResponseEntity("Kaydetmek istediğiniz sınıf doludur.", HttpStatus.BAD_REQUEST);
  }
}
