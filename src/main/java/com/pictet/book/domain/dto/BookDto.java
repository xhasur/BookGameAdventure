package com.pictet.book.domain.dto;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
  private String title;
  private String author;
  private String difficulty;
  private List<SectionDto> sections;
  private Set<String> categories;
}
