package com.pictet.book.persistence.crud;

import com.pictet.book.persistence.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CrudBookEntity extends CrudRepository<Book, Long> {

  @Query(
"""
    SELECT DISTINCT b FROM Book b
    WHERE (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', CAST(:author AS text), '%')))
      AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', CAST(:title AS text), '%')))
      AND (:difficulty IS NULL OR b.difficulty = :difficulty)
      AND (:category IS NULL OR :category MEMBER OF b.categories)
""")
  List<Book> searchBooks(
      @Param("title") String title,
      @Param("author") String author,
      @Param("difficulty") String difficulty,
      @Param("category") String category);

  Book findFirstByTitle(String title);
}
