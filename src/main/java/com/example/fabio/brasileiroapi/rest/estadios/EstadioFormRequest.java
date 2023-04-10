package com.example.fabio.brasileiroapi.rest.estadios;

import com.example.fabio.brasileiroapi.model.Cidade;
import com.example.fabio.brasileiroapi.model.Estadio;

public class EstadioFormRequest {

	private Long id;
	private String nome;
	private Cidade idCidade;
	
	public EstadioFormRequest() {
		super();
	}
		
	public EstadioFormRequest(Long id, String nome, Cidade idCidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.idCidade= idCidade;
		//System.out.println(this.idCidada);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cidade getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Cidade idCidade) {
		this.idCidade = idCidade;
	}
	
	public Estadio toModel() {
		return new Estadio(id, nome, idCidade);
	}
	
	public static EstadioFormRequest fromModel(Estadio estadio) {
		//System.out.println(estadio);
		return new EstadioFormRequest(estadio.getId(), estadio.getNome(), estadio.getIdCidade());
	}

}