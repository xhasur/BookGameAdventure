package com.pictet.book.persistence.mapper;

import com.pictet.book.domain.Difficulty;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class DifficultMapper {

    @Named("stringToDifficult")
    public static Difficulty stringToDifficult(String difficult) {
        if (difficult == null) return null;

        return switch (difficult.toUpperCase()) {
            case "EASY" -> Difficulty.EASY;
            case "HARD" -> Difficulty.HARD;
            case "MEDIUM" -> Difficulty.MEDIUM;
            default -> null;
        };
    }


    @Named("difficultToString")
    public static String difficultToString(Difficulty difficult) {
        if (difficult == null) return null;

        return switch (difficult) {
            case EASY -> "EASY";
            case HARD -> "HARD";
            case MEDIUM -> "MEDIUM";
            default -> null;
        };

    }
}
