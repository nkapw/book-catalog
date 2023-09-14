package com.tentang.tech.exception;

import com.tentang.tech.dto.ErrorResponseDTO;
import com.tentang.tech.enums.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());
    ErrorResponseDTO dataNotFound = ErrorResponseDTO.of("data not found", ErrorCode.DATA_NOT_FOUND, details, HttpStatus.BAD_REQUEST);
    return ResponseEntity.badRequest().body(dataNotFound);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error :
      ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.of("invalid data", ErrorCode.INVALID_DATA, details, HttpStatus.BAD_REQUEST);

    return ResponseEntity.badRequest().body(errorResponseDTO);
  }
}
