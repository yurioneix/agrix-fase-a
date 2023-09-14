package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service da classe Farm.
 */
@Service
public class FarmService {
  private FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Método que retorna todas as farms.
   */
  public List<Farm> getAllFarm() {
    return farmRepository.findAll();
  }

  /**
   * Método que retorna um Farm pelo id ou lança exceção caso não encontre.
   */
  public Optional<Farm> getFarmById(Long id) {

    if (farmRepository.findById(id).isEmpty()) {
      throw new FarmNotFoundException();
    }
    return farmRepository.findById(id);
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }
}
