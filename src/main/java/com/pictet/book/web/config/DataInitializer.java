package com.pictet.book.web.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.pictet.book.domain.dto.BookDto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    public DataInitializer() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public void run(String... args) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/books/*.json");

        logger.info("Total JSON files found: " + resources.length);

        for (Resource resource : resources) {
            if (resource == null || !resource.exists()) {
                logger.warn("Skipping null or non-existent resource");
                continue;
            }

            try (InputStream is = resource.getInputStream()) {
                String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                if (json.isBlank()) {
                    logger.warn("File {} is empty, skipping", resource.getFilename());
                    continue;
                }

                BookDto book = mapper.readValue(json, BookDto.class);
                logger.debug("Book '{}' loaded by Author '{}'", book.getTitle(), book.getAuthor());


            } catch (MismatchedInputException e) {
                logger.error("Error parsing JSON in file '{}': {}", resource.getFilename(), e.getOriginalMessage());
            } catch (IOException e) {
                logger.error("I/O error reading file '{}': {}", resource.getFilename(), e.getMessage());
            } catch (Exception e) {
                logger.error("Unexpected error reading file '{}': {}", resource.getFilename(), e.getMessage(), e);
            }
        }
    }
}
