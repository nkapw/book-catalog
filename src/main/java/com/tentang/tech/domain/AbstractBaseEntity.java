package com.tentang.tech.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Index;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
@Table(indexes = {
    @Index(name = "uk_secure_id", columnList = "secure_id")
})
public class AbstractBaseEntity {


  @Column(name = "secure_id", nullable = false, unique = true)
  private String secureId = UUID.randomUUID().toString();
  @Column(name = "deleted", columnDefinition = "boolean default false")
  private Boolean deleted;

}
