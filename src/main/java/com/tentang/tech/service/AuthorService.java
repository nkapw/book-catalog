package com.tentang.tech.service;

import java.util.List;

import com.tentang.tech.domain.Author;
import com.tentang.tech.dto.AuthorCreateRequestDTO;
import com.tentang.tech.dto.AuthorResponseDTO;
import com.tentang.tech.dto.AuthorUpdateRequestDTO;

public interface AuthorService {

	public AuthorResponseDTO findAuthorById(String id);

	public void createNewAuthor(List<AuthorCreateRequestDTO> dto);

	public void updateAuthor(String authorId, AuthorUpdateRequestDTO dto);

	public void deleteAuthor(String authorId);

	public List<Author> findByAuthors(List<String> listAuthorId);

	public List<AuthorResponseDTO> constructDTO(List<Author> authors);

}
