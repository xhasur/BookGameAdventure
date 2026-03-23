package com.pictet.book.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.persistence.BookEntityRepository;
import com.pictet.book.persistence.crud.CrudBookEntity;
import com.pictet.book.persistence.entity.Book;
import com.pictet.book.persistence.mapper.BookMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookEntityRepositoryTest {

    @InjectMocks
    private BookEntityRepository bookEntityRepository;

    @Mock
    private CrudBookEntity crudBookEntity;
    @Mock
    private BookMapper bookMapper;


    @Test
    @DisplayName("Test Should work correctly the method getBooksByConditions when the parameters are correct")
    public void testGetBooksByConditions() {

        Book book1 = new Book();
        book1.setAuthor("Evelyn Stormrider");
        book1.setTitle("The Crystal Caverns");

        List<Book> books = List.of(book1);
        when(crudBookEntity.searchBooks(any() , any() , any() , any())).thenReturn(books);
        List<BookDto> response = bookEntityRepository.getBooksByConditions("The Crystal Caverns","Evelyn Stormrider",
        "",""
        );

        assertNotNull(response);
    }
}
