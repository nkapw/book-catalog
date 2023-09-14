package com.tentang.tech.dto;

import com.tentang.tech.enums.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ErrorResponseDTO {

  private Date timestamp;

  private String message;

  private ErrorCode errorCode;

  private List<String> details;

  private HttpStatus status;

  public static ErrorResponseDTO of(final String message, final ErrorCode errorCode, List<String> details, HttpStatus status) {
    return new ErrorResponseDTO(message, errorCode, details, status);
  }

  public ErrorResponseDTO(String message, ErrorCode errorCode, List<String> details, HttpStatus status) {
    this.message = message;
    this.errorCode = errorCode;
    this.details = details;
    this.status = status;
    this.timestamp = new Date();
  }
}
