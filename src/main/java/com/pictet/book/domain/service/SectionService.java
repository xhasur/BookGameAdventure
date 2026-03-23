package com.pictet.book.domain.service;

import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.domain.repository.SectionRepository;
import com.pictet.book.persistence.entity.Section;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

  private final SectionRepository sectionRepository;

  public SectionService(SectionRepository sectionRepository) {
    this.sectionRepository = sectionRepository;
  }

  public SectionDto getBookBySectionIdAndBookId(long id, long sectionId) {
    return this.sectionRepository.findByIdSectionAndBookId(id, sectionId);
  }

  public Section getBookBySectionIdAndBookIdEntity(long sectionId , long bookId) {
    return this.sectionRepository.findByIdSectionAndBookIdEntity(sectionId , bookId);
  }

  public SectionDto findBy(long sectionId) {
    return this.sectionRepository.findBy(sectionId);
  }

  public Section getBySectionId(long sectionId) {
    return this.sectionRepository.getBySectionId(sectionId);
  }

}
