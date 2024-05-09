package com.starwars.api.service;

import java.util.List;

import com.starwars.api.dto.PlanetDTO;
import org.springframework.stereotype.Service;

import com.starwars.api.model.Planet;
import com.starwars.api.dto.PlanetStarWarsApiFilmsDTO;
import com.starwars.api.repository.PlanetRepository;
import com.starwars.api.exception.StarWarsException;

import static java.util.Objects.isNull;

@Service
public class PlanetService {

    private final ConsumerApiStarWarsService consumerApiService;

    private final PlanetRepository planetRepository;

    public PlanetService(ConsumerApiStarWarsService consumerApiService, PlanetRepository planetRepository) {
        this.consumerApiService = consumerApiService;
        this.planetRepository = planetRepository;
    }

    public Planet createPlanet(PlanetDTO planet) {
        Planet newPlanet = new Planet(planet);
        PlanetStarWarsApiFilmsDTO planetStarWarsApiFilmsDTO = this.consumerApiService.getByName(planet.getName());
        if (planetStarWarsApiFilmsDTO.getResults().isEmpty()) {
            newPlanet.setFilmsAppearances(0);
        } else {
            newPlanet.setFilmsAppearances(planetStarWarsApiFilmsDTO.getResults().get(0).getFilms().size());
        }
        this.planetRepository.save(newPlanet);
        return newPlanet;
    }

    public List<Planet> getAllPlanets() {
        return this.planetRepository.findAll();
    }

    public Planet getByName(String name) {
        Planet planet = this.planetRepository.findByName(name);
        if (isNull(planet)) {
            throw new StarWarsException("No exists planet with this name.");
        }
        return this.planetRepository.findByName(name);
    }

    public Planet getById(String id) {
        return this.planetRepository.findById(id).orElseThrow(() -> new StarWarsException("No exists planet with this id."));
    }

    public void deletePlanet(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("This ID cannot null or empty.");
        }
        this.planetRepository.deleteById(id);
    }
}
