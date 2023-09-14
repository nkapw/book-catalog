package com.tentang.tech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tentang.tech.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	public Optional<Book> findById(Long id);

	public Optional<Book> findBySecureId(String secureId);
	//
	// public List<Book> findAll();
	//
	// public void save(Book book);
	//
	// public void update(Book book);
	//
	// public void delete(Long bookId);

}
