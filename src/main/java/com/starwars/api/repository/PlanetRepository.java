package com.starwars.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.starwars.api.model.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {
    Planet findByName(String nome);
}
