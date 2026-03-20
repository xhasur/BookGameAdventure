package com.pictet.book.persistence.entity;

import com.pictet.book.domain.Difficulty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 10)
    private String difficulty;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Section> sections;

}
