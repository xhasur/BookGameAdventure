package com.pictet.book.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table
public class Section {

    @Id
    private Long id;

    @Column(nullable = false, length = 250)
    private String text;

    @Column(nullable = false, length = 20)
    private String type;

    @ManyToOne
    private Book book;

    @OneToMany(mappedBy="section", cascade=CascadeType.ALL)
    private List<Option> options;

}
