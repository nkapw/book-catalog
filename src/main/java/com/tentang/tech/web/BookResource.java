package com.tentang.tech.web;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tentang.tech.dto.BookCreateRequestDTO;
import com.tentang.tech.dto.BookDetailResponseDTO;
import com.tentang.tech.dto.BookUpdateRequestDTO;
import com.tentang.tech.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {

	private final BookService bookService;

	// nama yang salah /get-book/{bookId}
	@GetMapping("/v1/book/{bookId}")
	public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable("bookId") String id) {
		StopWatch stopWatch = new StopWatch();
		log.info("start findBookDetail " + id);
		stopWatch.start();
		BookDetailResponseDTO result = bookService.findBookDetailById(id);
		log.info("finish findBookDetail. execution time = {}", stopWatch.getTotalTimeMillis());
		return ResponseEntity.ok().body(result);

	}

	// nama yang salah /save-book /create-book
	@PostMapping("/v1/book")
	public ResponseEntity<Void> createANewBook(@RequestBody BookCreateRequestDTO dto) {
		bookService.createNewBook(dto);
		return ResponseEntity.created(URI.create("/  ")).build();
	}

	@GetMapping("/v1/book")
	public ResponseEntity<List<BookDetailResponseDTO>> findBookList() {
		return ResponseEntity.ok().body(bookService.findBookListDetail());

	}

	// PUT /book
	@PutMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId,
			@RequestBody BookUpdateRequestDTO dto) {
		bookService.updateBook(bookId, dto);
		return ResponseEntity.ok().build();
	}

	// DELETE /book
	@DeleteMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId) {
		bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}

}
