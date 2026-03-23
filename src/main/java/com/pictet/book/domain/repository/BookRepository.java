package com.pictet.book.domain.repository;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.persistence.entity.Book;
import java.util.List;

public interface BookRepository {
  List<BookDto> getAll();

  BookDto findById(long id);

  BookDto save(BookDto book);

  List<BookDto> getBooksByConditions(
      String title, String author, String difficulty, String category);

  BookDto saveBook(Book book);

  Book getBook(long id);
}
