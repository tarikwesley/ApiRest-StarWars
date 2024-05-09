package com.starwars.api.service;

import com.starwars.api.dto.PlanetDTO;
import com.starwars.api.dto.PlanetStarWarsApiDTO;
import com.starwars.api.dto.PlanetStarWarsApiFilmsDTO;
import com.starwars.api.repository.PlanetRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.starwars.api.model.Planet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @Mock
    private ConsumerApiStarWarsService consumerApiStarWarsService;

    @InjectMocks
    private PlanetService planetService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void souldCreatePlanetSuccessfully() {
        PlanetDTO planet = new PlanetDTO();
        planet.setName("Tatooine");
        planet.setClimate("arid");
        planet.setTerrain("desert");

        when(consumerApiStarWarsService.getByName(planet.getName())).thenReturn(planetStarWarsApi());

        Planet result = planetService.createPlanet(planet);

        Assert.assertNotNull(result);
        Assert.assertEquals(planet.getName(), result.getName());
        Assert.assertEquals(planet.getClimate(), result.getClimate());
        Assert.assertEquals(planet.getTerrain(), result.getTerrain());
    }

    @Test
    public void shouldReturnListWithAllPlanets() {

        when(planetService.getAllPlanets()).thenReturn(planets());

        List<Planet> planets = planetService.getAllPlanets();

        Assert.assertNotNull(planets);
        Assert.assertEquals(3, planets.size());
    }

    @Test
    public void shouldReturnPlanetWithMatchingName() {
        String name = "Tatooine";

        when(planetRepository.findByName(name)).thenReturn(planets().get(0));

        Planet planet = planetService.getByName(name);

        Assert.assertNotNull(planet);
        Assert.assertEquals(planets().get(0).getId(), planet.getId());
        Assert.assertEquals(planets().get(0).getName(), planet.getName());
        Assert.assertEquals(planets().get(0).getClimate(), planet.getClimate());
        Assert.assertEquals(planets().get(0).getTerrain(), planet.getTerrain());
        Assert.assertEquals(planets().get(0).getFilmsAppearances(), planet.getFilmsAppearances());
    }

    @Test
    public void shouldReturnPlanetWithMatchingId() {
        Planet planet = planets().get(2);

        when(planetRepository.findById(planet.getId())).thenReturn(Optional.of(planets().get(2)));

        Planet result = planetService.getById(planet.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(planet.getId(), result.getId());
        Assert.assertEquals(planet.getName(), result.getName());
        Assert.assertEquals(planet.getClimate(), result.getClimate());
        Assert.assertEquals(planet.getTerrain(), result.getTerrain());
        Assert.assertEquals(planet.getFilmsAppearances(), result.getFilmsAppearances());
    }

    @Test
    public void shouldExcludeThePlanetWithTheGivenId() {
        String id = planets().get(1).getId();
        planetService.deletePlanet(id);

        verify(planetRepository).deleteById(id);
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenIdIsNullOrEmpty() {
        String idNull = null;
        String idEmpty = "";

        Assert.assertThrows(IllegalArgumentException.class, () -> planetService.deletePlanet(idNull));
        Assert.assertThrows(IllegalArgumentException.class, () -> planetService.deletePlanet(idEmpty));
    }


    private List<Planet> planets() {
        Planet planet1 = new Planet();
        planet1.setId("c2f2dd89-eec7-4ef8-943b-087d0792bb8e");
        planet1.setName("Tatooine");
        planet1.setClimate("arid");
        planet1.setTerrain("desert");
        planet1.setFilmsAppearances(5);

        Planet planet2 = new Planet();
        planet2.setId("c915489e-f228-4a25-a350-47aeee777d71");
        planet2.setName("Coruscant");
        planet2.setClimate("temperate");
        planet2.setTerrain("cityscape, mountains");
        planet2.setFilmsAppearances(4);

        Planet planet3 = new Planet();
        planet3.setId("c115489e-f258-4a25-a354-47aeee777d75");
        planet3.setName("Alderaan");
        planet3.setClimate("temperate");
        planet3.setTerrain("grasslands, mountains");
        planet3.setFilmsAppearances(2);

        return Arrays.asList(planet1, planet2, planet3);
    }

    private PlanetStarWarsApiFilmsDTO planetStarWarsApi() {
        PlanetStarWarsApiDTO planet1 = new PlanetStarWarsApiDTO();
        planet1.setName("Tatooine");
        planet1.setFilms(Arrays.asList("http://swapi.dev/api/films/1/",
                "http://swapi.dev/api/films/3/",
                "http://swapi.dev/api/films/4/",
                "http://swapi.dev/api/films/5/",
                "http://swapi.dev/api/films/6/"));

        PlanetStarWarsApiDTO planet2 = new PlanetStarWarsApiDTO();
        planet2.setName("Dagobah");
        planet2.setFilms(Arrays.asList("http://swapi.dev/api/films/2/",
                "http://swapi.dev/api/films/3/",
                "http://swapi.dev/api/films/6/"));

        List<PlanetStarWarsApiDTO> planets = new ArrayList<>();
        planets.add(planet1);
        planets.add(planet2);
        return new PlanetStarWarsApiFilmsDTO(planets);
    }
}
