package com.pictet.book.persistence.crud;

import com.pictet.book.persistence.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface CrudBookEntity extends CrudRepository<Book, Long> {



}
