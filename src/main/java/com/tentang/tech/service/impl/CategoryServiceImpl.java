package com.tentang.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tentang.tech.domain.Category;
import com.tentang.tech.dto.CategoryCreateUpdateDTO;
import com.tentang.tech.dto.CategoryListResponseDTO;
import com.tentang.tech.dto.ResultPageResponseDTO;
import com.tentang.tech.exception.BadRequestException;
import com.tentang.tech.repository.CategoryRepository;
import com.tentang.tech.service.CategoryService;
import com.tentang.tech.util.PaginationUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
  private CategoryRepository categoryRepository;

  @Override
  public void createAndUpdateCategory(CategoryCreateUpdateDTO dto) {
    Category category = categoryRepository.findByCode(dto.getCode().toLowerCase()).orElse(new Category());
    if (category.getCode() == null) {
      category.setCode(dto.getCode());
    }
    category.setName(dto.getName());
    category.setDescription(dto.getDescription());

    categoryRepository.save(category);
  }

  @Override
  public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages, Integer limit, String sortBy,
      String direction, String categoryName) {
    categoryName = StringUtils.isBlank(categoryName) ? "%" : categoryName + "%";
    Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
    Pageable pageable = PageRequest.of(pages, limit, sort);
    Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);
    List<CategoryListResponseDTO> responseDTOS = pageResult.stream().map(category -> {
      CategoryListResponseDTO dto = new CategoryListResponseDTO();
      dto.setCode(category.getCode());
      dto.setName(category.getName());
      dto.setDescription(category.getDescription());
      return dto;
    }).collect(Collectors.toList());
    System.out.println(responseDTOS);
    return PaginationUtil.createResultPageDTO(responseDTOS, pageResult.getTotalElements(), pageResult.getTotalPages());

  }

  @Override
  public List<Category> findCategories(List<String> categoryList) {
    List<Category> categories = categoryRepository.findByCodeIn(categoryList);
    if (categories == null)
      throw new BadRequestException("category cant empty");
    return categories;
  }

  @Override
  public List<CategoryListResponseDTO> constructDTO(List<Category> categories) {
    return categories.stream().map(c -> {
      CategoryListResponseDTO dto = new CategoryListResponseDTO();
      dto.setName(c.getName());
      dto.setCode(c.getCode());
      dto.setDescription(c.getDescription());
      return dto;
    }).collect(Collectors.toList());
  }

}
