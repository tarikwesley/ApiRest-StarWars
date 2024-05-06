package com.starwars.api.service;

import java.util.List;

import com.starwars.api.dto.PlanetaDTO;
import org.springframework.stereotype.Service;

import com.starwars.api.model.Planeta;
import com.starwars.api.dto.PlanetaStarWarsApiFilmsDTO;
import com.starwars.api.repository.PlanetaRepository;
import com.starwars.api.exception.StarWarsException;

import static java.util.Objects.isNull;

@Service
public class PlanetaService {

    private final ConsumerApiStarWarsService consumerApiService;

    private final PlanetaRepository planetaRepository;

    public PlanetaService(ConsumerApiStarWarsService consumerApiService, PlanetaRepository planetaRepository) {
        this.consumerApiService = consumerApiService;
        this.planetaRepository = planetaRepository;
    }

    public Planeta adicionarPlaneta(PlanetaDTO planeta) {
        Planeta novoPlaneta = new Planeta(planeta);
        PlanetaStarWarsApiFilmsDTO planetaStarWarsApiFilmsDTO = this.consumerApiService.listarPorNome(planeta.getNome());
        if (planetaStarWarsApiFilmsDTO.getResults().isEmpty()) {
            novoPlaneta.setAparicoesEmFilmes(0);
        } else {
            novoPlaneta.setAparicoesEmFilmes(planetaStarWarsApiFilmsDTO.getResults().get(0).getFilms().size());
        }
        return this.planetaRepository.save(novoPlaneta);
    }

    public List<Planeta> listarPlanetas() {
        return this.planetaRepository.findAll();
    }

    public Planeta listarPorNome(String nome) {
        Planeta buscarPlaneta = this.planetaRepository.findByNome(nome);
        if (isNull(buscarPlaneta)) {
            throw new StarWarsException("Não existe planeta com esse nome.");
        }
        return this.planetaRepository.findByNome(nome);
    }

    public Planeta listarPorId(Long id) {
        return this.planetaRepository.findById(id).orElseThrow(() -> new StarWarsException("Não existe planeta com esse id."));
    }

    public void removerPlaneta(Long id) {
        this.planetaRepository.deleteById(id);
    }
}
