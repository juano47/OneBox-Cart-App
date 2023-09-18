package com.delaiglesia.onebox_cart_app.infrastructure.api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(final Exception ex) {
    printException(ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected API error");
  }

  @ExceptionHandler
  public ResponseEntity<String> handleEntityNotFoundException(final EntityNotFoundException ex) {
    printException(ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleIllegalArgumentException(final IllegalArgumentException ex) {
    printException(ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  private void printException(final Exception ex) {
    /* it is possible customize the log message in the console, for example, adding info about the request, etc.
    it can be a security issue to show the stack trace in the console, so it is better to log it in a file */
    log.error("Exception: ", ex);
  }
}
