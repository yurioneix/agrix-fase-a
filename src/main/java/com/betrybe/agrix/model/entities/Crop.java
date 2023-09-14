package com.betrybe.agrix.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe Crop.
 */
@Entity
@Table(name = "crop")
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double plantedArea;

  /**
   * Construtor da classe Crop que recebe id, name e plantedArea.
   */
  public Crop(Long id, String name, double plantedArea) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
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

  public double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(double plantedArea) {
    this.plantedArea = plantedArea;
  }
}
