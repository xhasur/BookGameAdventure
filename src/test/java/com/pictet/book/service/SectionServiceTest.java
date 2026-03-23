package com.pictet.book.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.domain.repository.SectionRepository;
import com.pictet.book.domain.service.SectionService;
import com.pictet.book.persistence.entity.Section;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

  @InjectMocks private SectionService sectionService;
  
  @Mock private SectionRepository sectionRepository;

  @Test
  @DisplayName("Test Should work correctly the method getBookBySectionIdAndBookId when the parameters are correct")
  public void testGetBookBySectionIdAndBookId() {

    SectionDto section = new SectionDto();
    section.setId(1L);
    section.setText("You reach a vast underground lake glowing with bioluminescent crystals");
    section.setType("NODE");

    when(sectionRepository.findByIdSectionAndBookId(1 , 2)).thenReturn(section);
    SectionDto response = sectionService.getBookBySectionIdAndBookId(1 , 2);

    assertEquals(response.getId(), 1);
    assertEquals(response.getType(), "NODE");
    assertNotNull(response);
  }

  @Test
  @DisplayName("Test Should work correctly the method getBookBySectionIdAndBookId when the method return error o null")
  public void testGetBookBySectionIdAndBookIdError() {
    when(sectionRepository.findByIdSectionAndBookId(1 , 2)).thenReturn(null);
    SectionDto exception =  sectionService.getBookBySectionIdAndBookId(1 , 2);
    assertNull(exception);
  }

  @Test
  @DisplayName("Test Should work correctly the method getBookBySectionIdAndBookIdEntity when the parameters are correct")
  public void testGetBookBySectionIdAndBookIdEntity() {

    Section section = new Section();
    section.setId(2L);
    section.setText("...bioluminescent crystals");
    section.setType("NODE");

    when(sectionRepository.findByIdSectionAndBookIdEntity(1 , 2)).thenReturn(section);
    Section response = sectionService.getBookBySectionIdAndBookIdEntity(1 , 2);

    assertEquals(response.getId(), 2);
    assertEquals(response.getType(), "NODE");
    assertNotNull(response);
  }


  @Test
  @DisplayName("Test Should work correctly the method getBookBySectionIdAndBookIdEntity when the method return error o null")
  public void testFindByIdSectionAndBookIdEntityError() {
    when(sectionRepository.findByIdSectionAndBookIdEntity(1 , 2)).thenReturn(null);
    Section exception =  sectionService.getBookBySectionIdAndBookIdEntity(1 , 2);
    assertNull(exception);
  }

}
