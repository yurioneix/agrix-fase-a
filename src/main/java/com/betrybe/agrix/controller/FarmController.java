package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.util.ModelDtoConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarm = farmService.getAllFarm();
    return allFarm.stream()
        .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
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

}
