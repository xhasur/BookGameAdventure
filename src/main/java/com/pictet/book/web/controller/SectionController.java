package com.pictet.book.web.controller;

import com.pictet.book.domain.dto.SectionDto;
import com.pictet.book.domain.service.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/section")
public class SectionController {

    private static final Logger LOGGER = LogManager.getLogger(SectionController.class);
    private final SectionService SectionService;

    public SectionController(SectionService sectionService) {
        SectionService = sectionService;
    }


    @GetMapping("/{id}/section/{sectionId}")
    @Operation(summary = "Get book by IdSection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the section and the bookId", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<SectionDto> getBooksByIdSection(@Parameter(description = "bookId", example = "1")
                                                          @PathVariable(value = "id") long id,
                                                          @Parameter(description = "sectionId", example = "1")
                                                          @PathVariable(value = "sectionId") long sectionId) {
        LOGGER.info("SectionController::getBooksByIdSection id: {} and sectionId: {}", id, sectionId);
        return new ResponseEntity<>(SectionService.getBookBySectionIdAndBookId(id, sectionId), HttpStatus.OK);
    }
}
