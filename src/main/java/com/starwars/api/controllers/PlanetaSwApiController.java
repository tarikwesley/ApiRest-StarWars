package com.starwars.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.api.model.PlanetaSwApi;
import com.starwars.api.model.PlanetaSwApiFilms;
import com.starwars.api.responses.Response;
import com.starwars.api.services.ConsumerApiSwService;
@RestController
@RequestMapping(path = "/swapi/planetas")
public class PlanetaSwApiController {
		
		@Autowired
		private ConsumerApiSwService service;
		
		@GetMapping(path = "/")
		public ResponseEntity<Response<PlanetaSwApiFilms>> listarPorPagina(@RequestParam(name = "pagina") int pagina) {	
			return ResponseEntity.ok(new Response<PlanetaSwApiFilms>(this.service.listarPorPagina(pagina)));
		}
		
		@GetMapping(path = "/{id}")
		public ResponseEntity<Response<PlanetaSwApi>> listarPorId(@PathVariable(name = "id") int id) {
			return ResponseEntity.ok(new Response<PlanetaSwApi>(this.service.listarPorId(id)));
		}
		
		@GetMapping(path = "")
		public ResponseEntity<Response<PlanetaSwApiFilms>> listarPorNome(@RequestParam(name = "nome") String nome){
			return ResponseEntity.ok(new Response<PlanetaSwApiFilms>(this.service.listarPorNome(nome)));
		}
}

