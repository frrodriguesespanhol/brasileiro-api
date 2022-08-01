package com.example.fabio.copaapi.rest.copas;

import java.time.LocalDate;

import com.example.fabio.copaapi.model.Copa;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CopaFormRequest {

	private Long cop_id;
	private String cop_nome;
	private String cop_ano;
	
	public CopaFormRequest() {
		super();
	}

	/**
	 * @param cop_id
	 * @param cop_nome
	 * @param cop_ano
	 */
	public CopaFormRequest(Long id, String nome, String ano) {
		super();
		this.cop_id = id;
		this.cop_nome = nome;
		this.cop_ano = ano;
	}

	public Long getId() {
		return cop_id;
	}
	public void setId(Long id) {
		this.cop_id = id;
	}
	public String getNome() {
		return cop_nome;
	}
	public void setNome(String nome) {
		this.cop_nome = nome;
	}
	public String getAno() {
		return cop_ano;
	}
	public void setAno(String ano) {
		this.cop_ano = ano;
	}
	
	public Copa toModel() {
		return new Copa(cop_id, cop_nome, cop_ano);
	}

	public static CopaFormRequest fromModel(Copa copa) {
		return new CopaFormRequest(copa.getId(), copa.getNome(), copa.getAno());
	}

}