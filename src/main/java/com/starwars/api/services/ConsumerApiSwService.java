package com.starwars.api.services;

import java.util.Arrays;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.starwars.api.model.PlanetaSwApi;
import com.starwars.api.model.PlanetaSwApiFilms;

@Service
public class ConsumerApiSwService {

	private CloseableHttpClient httpClient = HttpClients.custom()
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .build();
	private HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();	
	
	public ConsumerApiSwService() {
		this.requestFactory.setHttpClient(httpClient);
		this.restTemplate = new RestTemplate(requestFactory);
		this.headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
	}
	
	public PlanetaSwApiFilms listarPorPagina(int pagina) {
		 UriComponents swApiUrl = UriComponentsBuilder.newInstance()
			      .scheme("https").host("swapi.dev")
			      .path("/api/planets/").query("page={pagina}").buildAndExpand(pagina);

		PlanetaSwApiFilms response = restTemplate.getForObject(swApiUrl.toString(), PlanetaSwApiFilms.class);
		
		return response;
	}
	
	public PlanetaSwApi listarPorId(int id) {
		UriComponents swApiUrl = UriComponentsBuilder.newInstance()
			.scheme("https").host("swapi.dev")
			.path("/api/planets/{id}/").buildAndExpand(id);
			
		PlanetaSwApi response = restTemplate.getForObject(swApiUrl.toString(), PlanetaSwApi.class);
		
		return response;
	}
	
	public PlanetaSwApiFilms listarPorNome(String nome) {
		UriComponents swApiUrl = UriComponentsBuilder.newInstance()
			      .scheme("https").host("swapi.dev")
			      .path("/api/planets/").query("search={nome}").buildAndExpand(nome);
	
		PlanetaSwApiFilms response = restTemplate.getForObject(swApiUrl.toString(), PlanetaSwApiFilms.class);
		
		return response;

	}
}
