package com.pictet.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    private Long id;
    private String text;
    private String type;
    private List<OptionDto> options;
}
