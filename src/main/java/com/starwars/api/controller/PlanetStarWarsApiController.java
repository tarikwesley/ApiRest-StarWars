package com.starwars.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.api.dto.PlanetStarWarsApiDTO;
import com.starwars.api.dto.PlanetStarWarsApiFilmsDTO;
import com.starwars.api.dto.ResponseDTO;
import com.starwars.api.service.ConsumerApiStarWarsService;

@RestController
@RequestMapping(path = "/starwars-api/planets")
public class PlanetStarWarsApiController {

    private final ConsumerApiStarWarsService service;

    public PlanetStarWarsApiController(ConsumerApiStarWarsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PlanetStarWarsApiFilmsDTO>> getAllPlanets() {
        return ResponseEntity.ok(new ResponseDTO<>(this.service.getAllPlanets()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<PlanetStarWarsApiDTO>> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(this.service.getById(id)));
    }

    @GetMapping("/name")
    public ResponseEntity<ResponseDTO<PlanetStarWarsApiFilmsDTO>> getByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(new ResponseDTO<>(this.service.getByName(name)));
    }
}
