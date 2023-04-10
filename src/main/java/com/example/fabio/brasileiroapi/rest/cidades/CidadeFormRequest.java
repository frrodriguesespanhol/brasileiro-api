package com.example.fabio.brasileiroapi.rest.cidades;

import java.time.LocalDate;

import com.example.fabio.brasileiroapi.model.Cidade;
import com.example.fabio.brasileiroapi.model.Campeonato;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CidadeFormRequest {

	private Long id;
	private String nome;
	private Campeonato idCampeonato;
	
	public CidadeFormRequest() {
		super();
	}

	/**
	 * @param cid_id
	 * @param cid_nome
	 * @param cam_id
	 */
	
	public CidadeFormRequest(Long id, String nome, Campeonato idCampeonato) {
		super();
		this.id = id;
		this.nome = nome;
		this.idCampeonato= idCampeonato;
		//System.out.println(this.idCampeonato);
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
	public Campeonato getIdCampeonato() {
		return idCampeonato;
	}
	public void setIdCampeonato(Campeonato idCampeonato) {
		this.idCampeonato = idCampeonato;
	}
	
	public Cidade toModel() {
		return new Cidade(id, nome, idCampeonato);
	}
	
	public static CidadeFormRequest fromModel(Cidade cidade) {
		//System.out.println(cidade);
		return new CidadeFormRequest(cidade.getId(), cidade.getNome(), cidade.getIdCampeonato());
	}

}