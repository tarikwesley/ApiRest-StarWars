package com.starwars.api;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.starwars.api.model.Planeta;
import com.starwars.api.repositories.PlanetaRepository;
import com.starwars.api.services.PlanetaService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarWarsApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
		
		Planeta planeta1 = new Planeta();
		planeta1.setNome("Tatooine");
		planeta1.setClima("arid");
		planeta1.setTerreno("desert");
		
		Planeta planeta2 = new Planeta();
		planeta2.setNome("Coruscant");
		planeta2.setClima("temperate");
		planeta2.setTerreno("cityscape, mountains");
		
		Planeta planeta3 = new Planeta();
		planeta3.setNome("Alderaan");
		planeta3.setClima("temperate");
		planeta3.setTerreno("grasslands, mountains");
		
		List<Planeta> planetas = Arrays.asList(planeta1, planeta2, planeta3);
		
		planetas.forEach(pl -> {
			planetaService.adicionarPlaneta(pl);
		});
	}
	
	@Test
	void contextLoads() {
	}

}
