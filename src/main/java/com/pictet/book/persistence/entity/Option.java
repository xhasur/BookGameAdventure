package com.pictet.book.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Option {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 250)
  private String description;

  @Column(nullable = false, precision = 3)
  private Integer gotoId;

  @OneToOne(cascade = CascadeType.ALL)
  private Consequence consequence;

  @ManyToOne private Section section;
}
