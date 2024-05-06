package com.starwars.api.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.starwars.api.StarWarsApplicationTests;
import com.starwars.api.model.Planeta;

public class ApiControllerTests extends StarWarsApplicationTests {

    @Test
    public void testeInserirDados() {
        final String urlBaseApi = "http://localhost:" + porta + "/api/planetas";

        Planeta planeta = new Planeta();
        planeta.setNome("Endor");
        planeta.setClima("temperate");
        planeta.setTerreno("forests, mountains, lakes");

        ResponseEntity<Planeta> postPlaneta = restTemplate.postForEntity(urlBaseApi, planeta, Planeta.class);

        Assert.assertEquals(HttpStatus.OK, postPlaneta.getStatusCode());
        Assert.assertNotNull(postPlaneta.getBody());
    }

    @Test
    public void testeListarTodos() {
        final String urlBaseApi = "http://localhost:" + porta + "/api/planetas";

        ResponseEntity<String> getAllPlanetas = restTemplate.exchange(urlBaseApi,
                HttpMethod.GET, null, String.class);

        Assert.assertEquals(HttpStatus.OK, getAllPlanetas.getStatusCode());
        Assert.assertNotNull(getAllPlanetas.getBody());
    }

    @Test
    public void testeListarPorNome() {
        UriComponents url = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:" + porta)
                .path("/api/planetas/").query("nome={nome}").buildAndExpand("Tatooine");

        ResponseEntity<Planeta> getPlaneta = restTemplate.getForEntity(
                url.toString(), Planeta.class);

        Assert.assertEquals(HttpStatus.OK, getPlaneta.getStatusCode());
        Assert.assertNotNull(getPlaneta.getBody());
    }

    @Test
    public void testeListarPorId() {
        final String urlBaseApi = "http://localhost:" + porta + "/api/planetas";
        UriComponents url = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:" + porta)
                .path("/api/planetas/").query("nome={nome}").buildAndExpand("Tatooine");

        Planeta getPlanetaPorNome = restTemplate.getForObject(url.toString(), Planeta.class);

        ResponseEntity<Planeta> getPlanetaPorId = restTemplate.getForEntity(
                urlBaseApi + "/" + getPlanetaPorNome.getId(), Planeta.class);

        Assert.assertEquals(HttpStatus.OK, getPlanetaPorId.getStatusCode());
        Assert.assertNotNull(getPlanetaPorId.getBody());
    }

    @Test
    public void testeDeletarDados() {
        final String urlBaseApi = "http://localhost:" + porta + "/api/planetas";
        UriComponents url = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:" + porta)
                .path("/api/planetas/").query("nome={nome}").buildAndExpand("Alderaan");

        ResponseEntity<Planeta> getPlaneta = restTemplate.getForEntity(
                url.toString(), Planeta.class);

        restTemplate.delete(urlBaseApi + "/" + getPlaneta.getBody().getId());

        ResponseEntity<Planeta> planetaDeletado = restTemplate.getForEntity(
                urlBaseApi + "/" + getPlaneta.getBody().getId(), Planeta.class);

        Assert.assertEquals(HttpStatus.NOT_FOUND, planetaDeletado.getStatusCode());
    }
}
