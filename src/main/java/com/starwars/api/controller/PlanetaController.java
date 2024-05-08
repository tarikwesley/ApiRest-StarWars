package com.starwars.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.starwars.api.dto.PlanetaDTO;
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

import com.starwars.api.model.Planeta;
import com.starwars.api.dto.ResponseDTO;
import com.starwars.api.service.PlanetaService;

@RestController
@RequestMapping("/api/planetas")
public class PlanetaController {

    private final PlanetaService service;

    public PlanetaController(PlanetaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Planeta>> adicionarPlaneta(@Valid @RequestBody PlanetaDTO planetaDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new ResponseDTO<>(erros));
        }
        return new ResponseEntity<>(new ResponseDTO<>(this.service.adicionarPlaneta(planetaDTO)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Planeta>>> listarPlaneta() {
        return new ResponseEntity<>(new ResponseDTO<>(this.service.listarPlanetas()), HttpStatus.OK);
    }

    @GetMapping(path = "/nome")
    public ResponseEntity<ResponseDTO<Planeta>> listarPorNome(@RequestParam(name = "nome") String nome) {
        return new ResponseEntity<>(new ResponseDTO<>(this.service.listarPorNome(nome)), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<Planeta>> listarPorId(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(new ResponseDTO<>(this.service.listarPorId(id)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO<String>> removerPlaneta(@PathVariable(name = "id") String id) {
        this.service.removerPlaneta(id);
        return new ResponseEntity<>(new ResponseDTO<>("Removido com sucesso!"), HttpStatus.NO_CONTENT);
    }
}
