package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropResponseDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.util.ModelDtoConverter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe controller de Farm.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Rota GET /farms.
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarm = farmService.getAllFarm();
    return allFarm.stream()
        .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * Rota GET /farms/id.
   */
  @GetMapping("/{farmId}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return ResponseEntity.ok().body(optionalFarm.get());
  }

  /**
   * Rota POST de /farms.
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    Farm farmDtoToModel = ModelDtoConverter.dtoToModel(farmCreationDto);

    Farm newFarm = farmService.createFarm(farmDtoToModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Rota POST /{farmId}/crops para criar uma plantação a partir de uma fazenda.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropResponseDto> createCropByFarmId(
      @PathVariable Long farmId,
      @RequestBody Crop crop
  ) {

    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    farmService.createCropByFarmId(farmId, crop);

    return ResponseEntity.status(HttpStatus.CREATED).body(new CropResponseDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId()
    ));
  }

}
