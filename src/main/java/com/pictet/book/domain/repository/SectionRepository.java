package com.pictet.book.domain.repository;

import com.pictet.book.domain.dto.SectionDto;

public interface SectionRepository {
  SectionDto findByIdSectionAndBookId(long id, long sectionId);
}
