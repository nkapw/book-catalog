package com.tentang.tech.service;

import java.util.List;

import com.tentang.tech.dto.BookCreateRequestDTO;
import com.tentang.tech.dto.BookDetailResponseDTO;
import com.tentang.tech.dto.BookUpdateRequestDTO;

public interface BookService {

	public BookDetailResponseDTO findBookDetailById(String secureId);

	public List<BookDetailResponseDTO> findBookListDetail();

	public void createNewBook(BookCreateRequestDTO dto);

	public void updateBook(Long bookId, BookUpdateRequestDTO dto);

	public void deleteBook(Long bookId);

}
