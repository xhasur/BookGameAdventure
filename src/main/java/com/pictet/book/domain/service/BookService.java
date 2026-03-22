package com.pictet.book.domain.service;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.dto.CategoryRequest;
import com.pictet.book.domain.repository.BookRepository;
import com.pictet.book.persistence.entity.Book;
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

    public List<BookDto> getBooksByConditions(String title, String author, String difficulty, String category) {
        return this.bookRepository.getBooksByConditions(title, author, difficulty, category);
    }

    public BookDto add(BookDto book) {
        return this.bookRepository.save(book);
    }

    public BookDto findById(long id) {
        return this.bookRepository.findById(id);
    }

    public BookDto addCategory(long id, CategoryRequest categoryRequest) {
        String category = categoryRequest.getCategory();
        Book book = this.bookRepository.getBook(id);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }
        if (category == null || category.isBlank()) {
            throw new RuntimeException("category cannot be empty");
        }

        boolean added = book.getCategories().add(category);
        if (!added) {
            throw new RuntimeException("Category already exists");
        }

        bookRepository.saveBook(book);
        return this.findById(id);
    }

    public BookDto deleteCategory(long id, String category) {
        Book book = this.bookRepository.getBook(id);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }
        if (category == null || category.isBlank()) {
            throw new RuntimeException("category cannot be empty");
        }

        boolean added = book.getCategories().remove(category);
        if (!added) {
            throw new RuntimeException("Category already exists");
        }
        bookRepository.saveBook(book);
        return this.findById(id);
        
    }
}
