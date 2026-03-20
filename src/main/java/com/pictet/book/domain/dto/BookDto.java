package com.pictet.book.domain.dto;

import com.pictet.book.domain.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto
{
    private String title;
    private String author;
    private String difficulty;
    private List<SectionDto> sections;
}

