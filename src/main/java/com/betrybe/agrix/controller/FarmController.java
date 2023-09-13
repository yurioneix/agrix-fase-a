package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   * Rota POST de /farms.
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    Farm farmDtoToModel = ModelDtoConverter.dtoToModel(farmCreationDto);

    Farm newFarm = farmService.createFarm(farmDtoToModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

}
