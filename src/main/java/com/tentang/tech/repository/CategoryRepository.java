package com.tentang.tech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tentang.tech.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

  Optional<Category> findByCode(String code);

  Page<Category> findByNameLikeIgnoreCase(String categoryName, Pageable pageable);

  public List<Category> findByCodeIn(List<String> codes);
}
