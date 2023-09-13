package com.betrybe.agrix.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe Farm.
 */
@Entity
@Table(name = "farm")
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double size;

  /**
   * Construtor da classe Farm que recebe id, name e size.
   */
  public Farm(Long id, String name, double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  public Farm() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }
}
