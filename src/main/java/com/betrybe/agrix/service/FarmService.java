package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropRepository;
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
  private CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
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

  /**
   * Método que cria um crop a partir do id de um farm.
   */
  public Farm createCropByFarmId(Long id, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    cropRepository.save(crop);

    return optionalFarm.get();
  }
}
