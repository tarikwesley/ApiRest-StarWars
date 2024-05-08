package com.starwars.api.service;

import com.starwars.api.dto.PlanetaDTO;
import com.starwars.api.dto.PlanetaStarWarsApiDTO;
import com.starwars.api.dto.PlanetaStarWarsApiFilmsDTO;
import com.starwars.api.repository.PlanetaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.starwars.api.model.Planeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanetaServiceTest {

    @Mock
    private PlanetaRepository planetaRepository;

    @Mock
    private ConsumerApiStarWarsService consumerApiStarWarsService;

    @InjectMocks
    private PlanetaService planetaService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveAdicionarPlanetaComSucesso() {
        PlanetaDTO planeta = new PlanetaDTO();
        planeta.setNome("Tatooine");
        planeta.setClima("arid");
        planeta.setTerreno("desert");

        when(consumerApiStarWarsService.listarPorNome(planeta.getNome())).thenReturn(planetaStarWarsApi());

        Planeta result = planetaService.adicionarPlaneta(planeta);

        Assert.assertNotNull(result);
        Assert.assertEquals(planeta.getNome(), result.getNome());
        Assert.assertEquals(planeta.getClima(), result.getClima());
        Assert.assertEquals(planeta.getTerreno(), result.getTerreno());
    }

    @Test
    public void deveRetornarListaComTodosOsPLanetas() {

        when(planetaService.listarPlanetas()).thenReturn(planetas());

        List<Planeta> planetas = planetaService.listarPlanetas();

        Assert.assertNotNull(planetas);
        Assert.assertEquals(3, planetas.size());
    }

    @Test
    public void deveRetornarPlanetaComNomeCorrespondente() {
        String nome = "Tatooine";

        when(planetaRepository.findByNome(nome)).thenReturn(planetas().get(0));

        Planeta planeta = planetaService.listarPorNome(nome);

        Assert.assertNotNull(planeta);
        Assert.assertEquals(planetas().get(0).getId(), planeta.getId());
        Assert.assertEquals(planetas().get(0).getNome(), planeta.getNome());
        Assert.assertEquals(planetas().get(0).getClima(), planeta.getClima());
        Assert.assertEquals(planetas().get(0).getTerreno(), planeta.getTerreno());
        Assert.assertEquals(planetas().get(0).getAparicoesEmFilmes(), planeta.getAparicoesEmFilmes());
    }

    @Test
    public void deveRetornarPlanetaComIdCorrespondente() {
        Planeta planeta = planetas().get(2);

        when(planetaRepository.findById(planeta.getId())).thenReturn(Optional.of(planetas().get(2)));

        Planeta result = planetaService.listarPorId(planeta.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(planeta.getId(), result.getId());
        Assert.assertEquals(planeta.getNome(), result.getNome());
        Assert.assertEquals(planeta.getClima(), result.getClima());
        Assert.assertEquals(planeta.getTerreno(), result.getTerreno());
        Assert.assertEquals(planeta.getAparicoesEmFilmes(), result.getAparicoesEmFilmes());
    }

    @Test
    public void deveRemovePlanetaDeIdInformadoComSucesso() {
        String id = planetas().get(1).getId();
        planetaService.removerPlaneta(id);

        verify(planetaRepository).deleteById(id);
    }

    @Test
    public void deveLancarErrorQuandoIdForNuloOuVazio() {
        String idNulo = null;
        String idVazio = "";

        Assert.assertThrows(IllegalArgumentException.class, () -> planetaService.removerPlaneta(idNulo));
        Assert.assertThrows(IllegalArgumentException.class, () -> planetaService.removerPlaneta(idVazio));
    }


    private List<Planeta> planetas() {
        Planeta planeta1 = new Planeta();
        planeta1.setId("c2f2dd89-eec7-4ef8-943b-087d0792bb8e");
        planeta1.setNome("Tatooine");
        planeta1.setClima("arid");
        planeta1.setTerreno("desert");
        planeta1.setAparicoesEmFilmes(5);

        Planeta planeta2 = new Planeta();
        planeta2.setId("c915489e-f228-4a25-a350-47aeee777d71");
        planeta2.setNome("Coruscant");
        planeta2.setClima("temperate");
        planeta2.setTerreno("cityscape, mountains");
        planeta2.setAparicoesEmFilmes(4);

        Planeta planeta3 = new Planeta();
        planeta3.setId("c115489e-f258-4a25-a354-47aeee777d75");
        planeta3.setNome("Alderaan");
        planeta3.setClima("temperate");
        planeta3.setTerreno("grasslands, mountains");
        planeta3.setAparicoesEmFilmes(2);

        return Arrays.asList(planeta1, planeta2, planeta3);
    }

    private PlanetaStarWarsApiFilmsDTO planetaStarWarsApi() {
        PlanetaStarWarsApiDTO planeta1 = new PlanetaStarWarsApiDTO();
        planeta1.setName("Tatooine");
        planeta1.setFilms(Arrays.asList("http://swapi.dev/api/films/1/",
                "http://swapi.dev/api/films/3/",
                "http://swapi.dev/api/films/4/",
                "http://swapi.dev/api/films/5/",
                "http://swapi.dev/api/films/6/"));

        PlanetaStarWarsApiDTO planeta2 = new PlanetaStarWarsApiDTO();
        planeta2.setName("Dagobah");
        planeta2.setFilms(Arrays.asList("http://swapi.dev/api/films/2/",
                "http://swapi.dev/api/films/3/",
                "http://swapi.dev/api/films/6/"));

        List<PlanetaStarWarsApiDTO> planetas = new ArrayList<>();
        planetas.add(planeta1);
        planetas.add(planeta2);
        return new PlanetaStarWarsApiFilmsDTO(planetas);
    }
}
