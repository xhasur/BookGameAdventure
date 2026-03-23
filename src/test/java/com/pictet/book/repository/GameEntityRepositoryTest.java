package com.pictet.book.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.pictet.book.domain.dto.BookDto;
import com.pictet.book.domain.dto.game.GameDto;
import com.pictet.book.persistence.BookEntityRepository;
import com.pictet.book.persistence.GameEntityRepository;
import com.pictet.book.persistence.crud.CrudBookEntity;
import com.pictet.book.persistence.crud.CrudGameEntity;
import com.pictet.book.persistence.entity.Book;
import com.pictet.book.persistence.entity.Game;
import com.pictet.book.persistence.mapper.BookMapper;
import java.util.List;
import java.util.Optional;

import com.pictet.book.persistence.mapper.GameMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameEntityRepositoryTest {

    @InjectMocks
    private GameEntityRepository gameEntityRepository;

    @Mock
    private CrudGameEntity crudGameEntity;
    @Mock
    private GameMapper gameMapper;


    @Test
    @DisplayName("Test Should work correctly the method getGame when the parameters are correct")
    public void testGetGame() {

        Game game = new Game();
        game.setId(1L);
        game.setPlayerName("Andres");

        when(crudGameEntity.findById(any())).thenReturn(Optional.of(game));
        Game response = gameEntityRepository.getGame(1L);

        assertEquals(response.getPlayerName() , "Andres");
        assertNotNull(response);
    }

    @Test
    @DisplayName("Test Should work correctly the method findById when the parameters are correct")
    public void testFindById() {

        Game game = new Game();
        game.setId(1L);
        game.setPlayerName("Andres");

        GameDto GameDto = new GameDto();
        game.setId(1L);
        game.setPlayerName("Andres");

        when(gameMapper.toDto(any(Game.class))).thenReturn(null);
        when(crudGameEntity.findById(any())).thenReturn(Optional.of(game));
        GameDto response = gameEntityRepository.findById(1L);

        assertNull(response);
    }
}
