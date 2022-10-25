package com.example.fabio.copaapi.rest.fases;

import java.time.LocalDate;

import com.example.fabio.copaapi.model.Fase;
import com.example.fabio.copaapi.model.Selecao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FaseFormRequest {

	private Long fas_id;
	private String fas_nome;
	
	
	public FaseFormRequest() {
		super();
	}


	public FaseFormRequest(Long id, String nome) {
		super();
		this.fas_id = id;
		this.fas_nome = nome;
		
	}

	public Long getId() {
		return fas_id;
	}
	public void setId(Long id) {
		this.fas_id = id;
	}
	public String getNome() {
		return fas_nome;
	}
	public void setNome(String nome) {
		this.fas_nome = nome;
	}
		
	public Fase toModel() {
		return new Fase(fas_id, fas_nome);
	}

	public static FaseFormRequest fromModel(Fase fase) {
		return new FaseFormRequest(fase.getId(), fase.getNome());
	}

}