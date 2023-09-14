package com.tentang.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tentang.tech.domain.Author;
import com.tentang.tech.domain.Book;
import com.tentang.tech.domain.Category;
import com.tentang.tech.domain.Publisher;
import com.tentang.tech.dto.BookCreateRequestDTO;
import com.tentang.tech.dto.BookDetailResponseDTO;
import com.tentang.tech.dto.BookUpdateRequestDTO;
import com.tentang.tech.exception.BadRequestException;
import com.tentang.tech.repository.BookRepository;
import com.tentang.tech.service.AuthorService;
import com.tentang.tech.service.BookService;
import com.tentang.tech.service.CategoryService;
import com.tentang.tech.service.PublisherService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorService authorService;
  private final PublisherService publisherService;
  private final CategoryService categoryService;

  @Override
  public BookDetailResponseDTO findBookDetailById(String secureId) {
    Book book = bookRepository.findBySecureId(secureId)
        .orElseThrow(() -> new BadRequestException("book_id.invalid"));
    BookDetailResponseDTO dto = new BookDetailResponseDTO();
    dto.setBookId(book.getSecureId());
    dto.setCategories(categoryService.constructDTO(book.getCategories()));
    dto.setAuthors(authorService.constructDTO(book.getAuthors()));
    dto.setPublisher(publisherService.constructDTO(book.getPublisher()));
    dto.setBookTitle(book.getTitle());
    dto.setBookDescription(book.getDescription());
    return dto;
  }

  @Override
  public List<BookDetailResponseDTO> findBookListDetail() {
    List<Book> books = bookRepository.findAll();
    return books.stream().map((b) -> {
      BookDetailResponseDTO dto = new BookDetailResponseDTO();
      // dto.setAuthorName(b.getAuthor().getName());
      dto.setBookDescription(b.getDescription());
      // dto.setBookId(b.getId());
      dto.setBookTitle(b.getTitle());
      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  public void createNewBook(BookCreateRequestDTO dto) {
    List<Author> authors = authorService.findByAuthors(dto.getListAuthorId());
    List<Category> categories = categoryService.findCategories(dto.getListCategory());
    Publisher publisher = publisherService.findPublisher(dto.getPublisherId());
    Book book = new Book();
    book.setAuthors(authors);
    book.setPublisher(publisher);
    book.setCategories(categories);
    book.setTitle(dto.getBookTitle());
    book.setDescription(dto.getDescription());
    bookRepository.save(book);

  }

  @Override
  public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
    // get book from repository
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new BadRequestException("book_id.invalid"));
    // update
    book.setTitle(dto.getBookTitle());
    book.setDescription(dto.getDescription());
    // save
    bookRepository.save(book);

  }

  @Override
  public void deleteBook(Long bookId) {
    bookRepository.deleteById(bookId);

  }

  // public BookRepository getBookRepository() {
  // return bookRepository;
  // }
  //
  // public void setBookRepository(BookRepository bookRepository) {
  // this.bookRepository = bookRepository;
  // }

}
