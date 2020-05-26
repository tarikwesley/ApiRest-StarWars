package com.starwars.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.starwars.api.responses.Response;
import com.starwars.api.services.PlanetaService;

@RestController
@RequestMapping(path ="/api/planetas")
public class PlanetaController {	
	
	@Autowired
	private PlanetaService service;
	
	@PostMapping
	public ResponseEntity<Response<Planeta>> adicionarPlaneta(@Valid @RequestBody Planeta planeta, BindingResult result){
		if(result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Planeta>(erros));
		}
		return ResponseEntity.ok(new Response<Planeta>(this.service.adicionarPlaneta(planeta)));
	}

	@GetMapping
	public ResponseEntity<Response<List<Planeta>>> listarPlaneta(){
		return ResponseEntity.ok(new Response<List<Planeta>>(this.service.listarPlanetas()));
	}

	@GetMapping(path = "/")
	public ResponseEntity<Response<Planeta>> listarPorNome(@RequestParam(name = "nome") String nome){
		return ResponseEntity.ok(new Response<Planeta>(this.service.listarPorNome(nome)));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Planeta>> listarPorId(@PathVariable(name = "id")String id){
		return ResponseEntity.ok(new Response<Planeta>(this.service.listarPorId(id)));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<String>> removerPlaneta(@PathVariable(name = "id")String id){
		this.service.removerPlaneta(id);
		return ResponseEntity.ok(new Response<String>("Removido com sucesso!"));
	}
		
}
