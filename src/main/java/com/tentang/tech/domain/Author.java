package com.tentang.tech.domain;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
// @DynamicUpdate
@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Author extends AbstractBaseEntity {

  // postgre-> bigserial
  // mysql->autoincrement
  // strategy -> identity -> cons: batch insert disabled
  // batch insert -> stored producured

  // strategy sequence -> pros: enable batch insert
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
  @SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq")
  private Long id;

  @Column(name = "author_name", nullable = false, columnDefinition = "varchar(300)")
  private String name;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

}
