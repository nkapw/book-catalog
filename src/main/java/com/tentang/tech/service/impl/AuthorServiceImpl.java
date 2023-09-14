package com.tentang.tech.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.tentang.tech.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.tentang.tech.domain.Author;
import com.tentang.tech.dto.AuthorCreateRequestDTO;
import com.tentang.tech.dto.AuthorResponseDTO;
import com.tentang.tech.dto.AuthorUpdateRequestDTO;
import com.tentang.tech.exception.BadRequestException;
import com.tentang.tech.repository.AuthorRepository;
import com.tentang.tech.service.AuthorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public AuthorResponseDTO findAuthorById(String id) {
    // TODO Auto-generated method stub
    // 1. fetch data from databse
    Author author = authorRepository.findBySecureId(id).orElseThrow(() -> new ResourceNotFoundException("invalid.authorId"));
    // 2. author -> authorResponseDTO
    AuthorResponseDTO dto = new AuthorResponseDTO();
    dto.setAuthorName(author.getName());
    dto.setBirthDate(author.getBirthDate().toEpochDay());
    return dto;
  }

  @Override
  public void createNewAuthor(List<AuthorCreateRequestDTO> dtos) {

    List<Author> authors = dtos.stream().map((dto) -> {
      Author author = new Author();
      author.setName(dto.getAuthorName());
      author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
      return author;
    }).collect(Collectors.toList());

    authorRepository.saveAll(authors);
  }

  @Override
  public void updateAuthor(String authorId, AuthorUpdateRequestDTO dto) {
    Author author = authorRepository.findBySecureId(authorId)
        .orElseThrow(() -> new BadRequestException("invalid.authorId"));
    author.setName(dto.getAuthorName() == null ? author.getName() : dto.getAuthorName());
    author.setBirthDate(
        dto.getBirthDate() == null ? author.getBirthDate() : LocalDate.ofEpochDay(dto.getBirthDate()));

    authorRepository.save(author);

  }

  // oracle db -> flashback technologies
  // softdelete
  @Override
  public void deleteAuthor(String authorId) {
    // 1 select data
    // 2 delete
    // or
    // 1 delete (harddelete)
    // authorRepository.deleteById(authorId);

    // softdelete
    // 1. select data deleted=false
    Author author = authorRepository.findBySecureId(authorId)
        .orElseThrow(() -> new BadRequestException("invalid.authorId"));
    authorRepository.delete(author);
    // 2. update deleted=true
    // author.setDeleted(Boolean.TRUE);
    // authorRepository.save(author);
  }

  @Override
  public List<Author> findByAuthors(List<String> listAuthorId) {
    List<Author> authorList = authorRepository.findBySecureIdIn(listAuthorId);
    if (authorList == null)
      throw new BadRequestException("author cant empty");
    return authorList;
  }

  @Override
  public List<AuthorResponseDTO> constructDTO(List<Author> authors) {
    return authors.stream().map(a -> {
      AuthorResponseDTO dto = new AuthorResponseDTO();
      dto.setAuthorName(a.getName());
      dto.setBirthDate(a.getBirthDate().toEpochDay());
      return dto;
    }).collect(Collectors.toList());
  }

}
