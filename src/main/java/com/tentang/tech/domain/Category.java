package com.tentang.tech.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

  @Id
  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = true)
  private String description;

  @ManyToMany(mappedBy = "categories")
  private List<Book> books;
}
