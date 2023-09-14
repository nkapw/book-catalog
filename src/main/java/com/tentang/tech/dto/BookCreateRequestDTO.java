package com.tentang.tech.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookCreateRequestDTO {

  @NotBlank
  private String bookTitle;

  @NotEmpty
  private List<String> listAuthorId;

  @NotBlank
  private List<String> listCategory;

  @NotBlank
  private String publisherId;

  @JsonProperty("synopsis")
  private String description;

}
