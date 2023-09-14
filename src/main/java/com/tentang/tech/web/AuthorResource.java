package com.tentang.tech.web;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tentang.tech.dto.AuthorCreateRequestDTO;
import com.tentang.tech.dto.AuthorResponseDTO;
import com.tentang.tech.dto.AuthorUpdateRequestDTO;
import com.tentang.tech.service.AuthorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AuthorResource {

  private final AuthorService authorService;

  //author detail
  @GetMapping("/v1/author/{id}/detail")
  public ResponseEntity<AuthorResponseDTO> findAuthorById(@PathVariable String id) {
    return ResponseEntity.ok().body(authorService.findAuthorById(id));
  }

  @PostMapping("/v1/author")
  public ResponseEntity<Void> createNewAuthor(@RequestBody @Valid List<AuthorCreateRequestDTO> dto) {
    authorService.createNewAuthor(dto);
    return ResponseEntity.created(URI.create("/v1/author")).build();
  }


  @PutMapping("/v1/author/{authorId}")
  public ResponseEntity<Void> updateAuthor(@PathVariable String authorId,
                                           @RequestBody AuthorUpdateRequestDTO dto) {
    authorService.updateAuthor(authorId, dto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/v1/author/{authorId}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable String authorId) {
    authorService.deleteAuthor(authorId);
    return ResponseEntity.ok().build();
  }


}

