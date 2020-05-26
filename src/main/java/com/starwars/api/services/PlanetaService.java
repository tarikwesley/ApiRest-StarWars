package com.starwars.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.api.model.Planeta;
import com.starwars.api.model.PlanetaSwApiFilms;
import com.starwars.api.repositories.PlanetaRepository;
import com.starwars.api.responses.PlanetaNaoEncontradoException;

@Service
public class PlanetaService {
	
	@Autowired
	private ConsumerApiSwService consumerApiService;
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	public Planeta adicionarPlaneta(Planeta planeta) {
		PlanetaSwApiFilms planetaSwApiFilms = this.consumerApiService.listarPorNome(planeta.getNome());
		if (planetaSwApiFilms.getResults().isEmpty()) {
			planeta.setAparicoesFilmes(0);
		} else {
			planeta.setAparicoesFilmes(planetaSwApiFilms.getResults()
					.get(0).getFilms().size());
		}		
		return this.planetaRepository.save(planeta);
	}

	
	public List<Planeta> listarPlanetas() {
		return this.planetaRepository.findAll(); 
	}

	
	public Planeta listarPorNome(String nome) throws PlanetaNaoEncontradoException { 
		Planeta buscarPlaneta = this.planetaRepository.findByNome(nome);
		if (buscarPlaneta == null) {
			throw new PlanetaNaoEncontradoException("Não existe planeta com esse nome.");
		}
		return this.planetaRepository.findByNome(nome);
	}

	
	public Planeta listarPorId(String id) throws PlanetaNaoEncontradoException{
		return this.planetaRepository.findById(id).orElseThrow(
				()-> new PlanetaNaoEncontradoException("Não existe planeta com esse id."));
	}
	
	
	public void removerPlaneta(String id) {
		this.planetaRepository.deleteById(id);
	}
}
