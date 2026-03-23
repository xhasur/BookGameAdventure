package com.pictet.book.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.dto.CategoryRequest;
import com.pictet.book.domain.repository.BookRepository;
import com.pictet.book.domain.service.BookService;
import com.pictet.book.persistence.entity.Book;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  @InjectMocks private BookService bookService;

  @Mock private BookRepository bookRepository;

  @Test
  @DisplayName("Test Should work correctly the method getAll when the parameters are correct")
  public void testGetBooks() {

    BookDto book1 = new BookDto();
    book1.setAuthor("Evelyn Stormrider");
    book1.setTitle("The Crystal Caverns");

    List<BookDto> books = new ArrayList<>();
    books.add(book1);

    when(bookRepository.getAll()).thenReturn(books);
    List<BookDto> response = bookService.getAll();

    assertEquals(response.size(), books.size());
    assertNotNull(response);
  }

  @Test
  @DisplayName("Test Should work correctly the method getAll when the parameters are correct")
  public void testGetBooksByConditions() {

    BookDto book1 = new BookDto();
    book1.setAuthor("Evelyn Stormrider");
    book1.setTitle("The Crystal Caverns");

    List<BookDto> books = new ArrayList<>();
    books.add(book1);

    when(bookRepository.getBooksByConditions(any(), any(), any(), any())).thenReturn(books);
    List<BookDto> response =
        bookService.getBooksByConditions("The Crystal Caverns",
                "Evelyn Stormrider", "", "");

    assertEquals(response.size(), books.size());
    assertNotNull(response);
  }

  @Test
  @DisplayName("Test Should work correctly the method findById when the parameters are correct")
  public void testFindById() {

    BookDto book1 = new BookDto();
    book1.setAuthor("Evelyn Stormrider");
    book1.setTitle("The Crystal Caverns");

    when(bookRepository.findById(1)).thenReturn(book1);
    BookDto response = bookService.findById(1);

    assertEquals(response.getTitle(), "The Crystal Caverns");
    assertEquals(response.getAuthor(), "Evelyn Stormrider");
    assertNotNull(response);
  }

  @Test
  @DisplayName("Test Should work correctly the method getBookById when the parameters are correct")
  public void testGetBookById() {

    Book book = new Book();
    book.setAuthor("Evelyn Stormrider");
    book.setTitle("The Crystal Caverns");

    when(bookRepository.getBook(1)).thenReturn(book);
    Book response = bookService.getBookById(1);

    assertEquals(response.getTitle(), "The Crystal Caverns");
    assertEquals(response.getAuthor(), "Evelyn Stormrider");
    assertNotNull(response);
  }

  @Test
  @DisplayName("Test Should work correctly the method getBookById when the response is null handling the exception")
  public void testGetBookByIdError() {
    when(bookRepository.getBook(1)).thenReturn(null);
    Exception exception = assertThrows(Exception.class, () -> bookService.getBookById(1));
    assertEquals("Book does not exist", exception.getMessage());

  }

  @Test
  @DisplayName("Test Should work correctly the method addCategory")
  public void testAddCategory() {
    CategoryRequest request = new CategoryRequest();
    request.setCategory("TERROR");

    when(bookRepository.getBook(1)).thenReturn(null);
    Exception exception = assertThrows(Exception.class, () -> bookService.addCategory(1, request));
    assertEquals("Book does not exist", exception.getMessage());
  }
}
