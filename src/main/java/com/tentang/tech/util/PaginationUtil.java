package com.tentang.tech.util;

import com.tentang.tech.dto.ResultPageResponseDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PaginationUtil {

  public static <T>ResultPageResponseDTO<T> createResultPageDTO(List<T> dtos, Long totalElements, Integer pages){
    ResultPageResponseDTO<T> responseDTO = new ResultPageResponseDTO<>();
    responseDTO.setPages(pages);
    responseDTO.setResult(dtos);
    responseDTO.setElements(totalElements);
    return responseDTO;
  }
  public static Sort.Direction getSortBy(String sortBy) {
    if (sortBy.equals("asc")) {
      return Sort.Direction.ASC;
    } else {
      return Sort.Direction.DESC;
    }
  }
}
