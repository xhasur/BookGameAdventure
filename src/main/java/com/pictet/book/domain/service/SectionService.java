package com.pictet.book.domain.service;

import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.domain.repository.SectionRepository;
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
}
