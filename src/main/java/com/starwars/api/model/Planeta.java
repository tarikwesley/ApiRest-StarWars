package com.starwars.api.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planeta {
	
	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private Integer aparicoesEmFilmes;
	
	public Planeta() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotBlank(message = "Nome não pode ser vazio!")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotBlank(message = "Clima não pode ser vazio!")
	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}
	
	@NotBlank(message = "Terreno não pode ser vazio!")
	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public Integer getAparicoesFilmes() {
		return aparicoesEmFilmes;
	}

	public void setAparicoesFilmes(Integer aparicoesFilmes) {
		this.aparicoesEmFilmes = aparicoesFilmes;
	}
	
	
	
	

}
