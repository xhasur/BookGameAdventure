package com.pictet.book.domain.service;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAll() {
        return this.bookRepository.getAll();
    }
}
