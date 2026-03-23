package com.pictet.book.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Book book;

  @Column(nullable = false, length = 50)
  private String playerName;

  @Column(nullable = false, length = 3)
  private Integer idSection;

  @Column(nullable = false, length = 3)
  private Integer health;

  @Column(nullable = false, length = 15)
  private String gameStatus;
}
