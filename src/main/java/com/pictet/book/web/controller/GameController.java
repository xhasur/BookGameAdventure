package com.pictet.book.web.controller;

import com.pictet.book.domain.dto.GameDto;
import com.pictet.book.domain.dto.GameRequest;
import com.pictet.book.domain.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private static final Logger LOGGER = LogManager.getLogger(GameController.class);
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping("/start")
    @Operation(summary = "Start the game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game started", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<GameDto> startGame(@Parameter(description = "GameRequest", example = "1") @RequestBody GameRequest gameRequest) {
        LOGGER.info("GameController::startGame gameRequest: {} ", gameRequest);
        return new ResponseEntity<>(gameService.startGame(gameRequest.getGameId()), HttpStatus.OK);
    }
}
