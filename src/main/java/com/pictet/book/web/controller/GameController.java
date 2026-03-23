package com.pictet.book.web.controller;


import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.domain.dto.game.GameRequest;
import com.pictet.book.domain.dto.game.ResponseGameDto;
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
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GameDto> startGame(@Parameter(description = "GameRequest", example = "1")
                                             @RequestBody GameRequest gameRequest) {
    LOGGER.info("GameController::startGame gameRequest: {} ", gameRequest);
    return new ResponseEntity<>(
        gameService.startGame(gameRequest.getBookId(), gameRequest.getPlayerId()), HttpStatus.OK);
  }

    @GetMapping("/{id}")
    @Operation(summary = "Get Game by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Game", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<ResponseGameDto> getGameById(@Parameter(description = "id", example = "1")
                                                       @PathVariable(value = "id") long id) {
    LOGGER.info("GameController::getGameById id: {} ", id);
    return new ResponseEntity<>(gameService.findById(id), HttpStatus.OK);
  }

    @PostMapping("/{gameId}/choose/{optionId}")
    @Operation(summary = "Choose the option game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Option chose", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<ResponseGameDto> chooseOption(@Parameter(description = "GameRequest", example = "1")
                                                        @PathVariable(value = "gameId") long gameId,
                                                        @Parameter(description = "GameRequest", example = "1")
                                                        @PathVariable(value = "optionId") long optionId) {
    LOGGER.info("GameController::chooseOption gameId: {}, optionId: {} ", gameId, optionId);
    return new ResponseEntity<>(
        gameService.chooseOptionGame(gameId, optionId), HttpStatus.OK);
  }
}
