package com.pictet.book.persistence.crud;

import com.pictet.book.persistence.entity.Section;
import org.springframework.data.repository.CrudRepository;

public interface CrudSectionEntity extends CrudRepository<Section, Long> {

  Section findByIdSectionAndBookId(Long sectionId, Long bookId);
}
