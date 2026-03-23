package com.pictet.book.domain.service;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.dto.CategoryRequest;
import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.domain.exception.BookError;
import com.pictet.book.domain.exception.BookNotFound;
import com.pictet.book.domain.exception.CategoryAlreadyExistsException;
import com.pictet.book.domain.exception.CategoryNotFound;
import com.pictet.book.domain.repository.BookRepository;
import com.pictet.book.persistence.entity.Book;
import java.util.List;

import com.pictet.book.persistence.entity.Section;
import org.springframework.stereotype.Service;

import static com.pictet.book.web.util.Constants.*;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<BookDto> getAll() {
    return this.bookRepository.getAll();
  }

  public List<BookDto> getBooksByConditions(
      String title, String author, String difficulty, String category) {
    return this.bookRepository.getBooksByConditions(title, author, difficulty, category);
  }

  public BookDto add(BookDto book) {
    this.validate(book);
    return this.bookRepository.save(book);
  }

  public BookDto findById(long id) {
    return this.bookRepository.findById(id);
  }

  public Book getBookById(long id) {
    Book book = this.bookRepository.getBook(id);
    if (book == null) {
      throw new BookNotFound();
    }
    return book;
  }

  public BookDto addCategory(long id, CategoryRequest categoryRequest) {
    String category = categoryRequest.getCategory();
    Book book = this.getBookById(id);

    if (category == null || category.isBlank()) {
      throw new CategoryNotFound();
    }

    category = category.trim().toUpperCase();

    boolean added = book.getCategories().add(category);
    if (!added) {
      throw new CategoryAlreadyExistsException(category);
    }

    return bookRepository.saveBook(book);
  }

  public BookDto deleteCategory(long id, String category) {
    Book book = this.getBookById(id);

    if (category == null || category.isBlank()) {
      throw new CategoryNotFound();
    }
    category = category.trim().toUpperCase();

    boolean removed = book.getCategories().remove(category);
    if (!removed) {
      throw new CategoryNotFound();
    }
    bookRepository.saveBook(book);
    return this.findById(id);
  }

  private void validate(BookDto book) {
    try {
      List<SectionDto> sections = book.getSections();
      if (sections == null || sections.isEmpty()) {
        throw new BookError(BOOK_MUST_HAVE_SECTIONS);
      }
      long beginCount = sections.stream().filter(s -> s.getType().equals(SECTION_BEGIN)).count();
      if (beginCount != 1) {
        throw new RuntimeException(BOOK_MUST_HAVE_A_BEGIN_SECTION);
      }
      boolean hasEnd = sections.stream().anyMatch(s -> s.getType().equals(SECTION_END));
      if (!hasEnd) {
        throw new RuntimeException(BOOK_MUST_HAVE_ONE_END_SECTION);
      }
    } catch (Exception exception) {
      throw new BookError(BOOK_MUST_BE_VALID);
    }
  }
}
