package com.starwars.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.api.dto.PlanetaStarWarsApiDTO;
import com.starwars.api.dto.PlanetaStarWarsApiFilmsDTO;
import com.starwars.api.dto.ResponseDTO;
import com.starwars.api.service.ConsumerApiStarWarsService;

@RestController
@RequestMapping(path = "/starwars-api/planetas")
public class PlanetaStarWarsApiController {

    private final ConsumerApiStarWarsService service;

    public PlanetaStarWarsApiController(ConsumerApiStarWarsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PlanetaStarWarsApiFilmsDTO>> listarPlanetas() {
        return ResponseEntity.ok(new ResponseDTO<>(this.service.listarPlanetas()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<PlanetaStarWarsApiDTO>> listarPorId(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(new ResponseDTO<>(this.service.listarPorId(id)));
    }

    @GetMapping("/nome")
    public ResponseEntity<ResponseDTO<PlanetaStarWarsApiFilmsDTO>> listarPorNome(@RequestParam(name = "nome") String nome) {
        return ResponseEntity.ok(new ResponseDTO<>(this.service.listarPorNome(nome)));
    }
}
