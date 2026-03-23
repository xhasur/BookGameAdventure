package com.pictet.book.persistence;

import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.domain.repository.SectionRepository;
import com.pictet.book.persistence.crud.CrudSectionEntity;
import com.pictet.book.persistence.entity.Section;
import com.pictet.book.persistence.mapper.SectionMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SectionEntityRepository implements SectionRepository {
    private final CrudSectionEntity crudSectionEntity;
    private final SectionMapper sectionMapper;

    public SectionEntityRepository(CrudSectionEntity crudSectionEntity, SectionMapper bookMapper) {
        this.crudSectionEntity = crudSectionEntity;
        this.sectionMapper = bookMapper;
    }

    @Override
    public SectionDto findByIdSectionAndBookId(long bookId, long sectionId) {
        return sectionMapper.toDto(crudSectionEntity.findByIdSectionAndBookId(sectionId, bookId));
    }

    public Section findByIdSectionAndBookIdEntity(long sectionId, long bookId ) {
        return crudSectionEntity.findByIdSectionAndBookId(sectionId, bookId);
    }
}
