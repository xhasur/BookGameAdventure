package com.pictet.book.domain.repository;

import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.persistence.entity.Section;

public interface SectionRepository {
  SectionDto findByIdSectionAndBookId(long id, long sectionId);
  Section findByIdSectionAndBookIdEntity(long sectionId, long bookId);
  SectionDto findBy(long sectionId);
  Section getBySectionId(long sectionId);
}
