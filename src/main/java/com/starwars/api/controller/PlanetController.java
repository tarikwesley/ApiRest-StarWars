package com.starwars.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.starwars.api.dto.PlanetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.api.model.Planet;
import com.starwars.api.dto.ResponseDTO;
import com.starwars.api.service.PlanetService;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    private final PlanetService service;

    public PlanetController(PlanetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Planet>> createPlanet(@Valid @RequestBody PlanetDTO planetDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new ResponseDTO<>(errors));
        }
        return new ResponseEntity<>(new ResponseDTO<>(this.service.createPlanet(planetDTO)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Planet>>> getAllPlanets() {
        return new ResponseEntity<>(new ResponseDTO<>(this.service.getAllPlanets()), HttpStatus.OK);
    }

    @GetMapping(path = "/name")
    public ResponseEntity<ResponseDTO<Planet>> getByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(new ResponseDTO<>(this.service.getByName(name)), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<Planet>> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(new ResponseDTO<>(this.service.getById(id)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<String>> deletePlanet(@PathVariable(name = "id") String id) {
        this.service.deletePlanet(id);
        return new ResponseEntity<>(new ResponseDTO<>("Successfully removed!"), HttpStatus.NO_CONTENT);
    }
}
