package com.pictet.book.domain.repository;

import com.pictet.book.domain.Difficulty;
import com.pictet.book.domain.dto.BookDto;

import java.util.List;

public interface BookRepository {
    List<BookDto> getAll();

    BookDto save(BookDto book);

    List<BookDto> getBooksByConditions(String title, String author, String difficulty, String category);
}
