package com.pictet.book.persistence.crud;

import com.pictet.book.persistence.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface CrudGameEntity extends CrudRepository<Game, Long> {}
