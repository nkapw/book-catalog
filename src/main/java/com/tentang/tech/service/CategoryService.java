package com.tentang.tech.service;

import java.util.List;

import com.tentang.tech.domain.Category;
import com.tentang.tech.dto.CategoryCreateUpdateDTO;
import com.tentang.tech.dto.CategoryListResponseDTO;
import com.tentang.tech.dto.ResultPageResponseDTO;

public interface CategoryService {

  public void createAndUpdateCategory(CategoryCreateUpdateDTO categoryCreateUpdateDTO);

  public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages, Integer limit, String sortBy,
      String direction, String publisherName);

  public List<Category> findCategories(List<String> categoryList);

  public List<CategoryListResponseDTO> constructDTO(List<Category> categories);
}
