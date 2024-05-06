package com.starwars.api;

import java.util.Arrays;
import java.util.List;

import com.starwars.api.dto.PlanetaDTO;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.starwars.api.repository.PlanetaRepository;
import com.starwars.api.service.PlanetaService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarWarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StarWarsApplicationTests {

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private PlanetaService planetaService;

    @LocalServerPort
    public int porta;

    @Before
    public void configuracao() {
        planetaRepository.deleteAll();

        PlanetaDTO planeta1 = new PlanetaDTO();
        planeta1.setNome("Tatooine");
        planeta1.setClima("arid");
        planeta1.setTerreno("desert");

        PlanetaDTO planeta2 = new PlanetaDTO();
        planeta2.setNome("Coruscant");
        planeta2.setClima("temperate");
        planeta2.setTerreno("cityscape, mountains");

        PlanetaDTO planeta3 = new PlanetaDTO();
        planeta3.setNome("Alderaan");
        planeta3.setClima("temperate");
        planeta3.setTerreno("grasslands, mountains");

        List<PlanetaDTO> planetas = Arrays.asList(planeta1, planeta2, planeta3);

        planetas.forEach(pl -> {
            planetaService.adicionarPlaneta(pl);
        });
    }
}
