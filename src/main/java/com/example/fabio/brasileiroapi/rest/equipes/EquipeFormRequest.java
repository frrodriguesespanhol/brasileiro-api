package com.example.fabio.brasileiroapi.rest.equipes;

import java.time.LocalDate;

import com.example.fabio.brasileiroapi.model.Equipe;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EquipeFormRequest {

	private Long equ_id;
	private String equ_nome;
	
	
	public EquipeFormRequest() {
		super();
	}

	/**
	 * @param equ_id
	 * @param equ_nome
	
	 */
	public EquipeFormRequest(Long id, String nome) {
		super();
		this.equ_id = id;
		this.equ_nome = nome;
		
	}

	public Long getId() {
		return equ_id;
	}
	public void setId(Long id) {
		this.equ_id = id;
	}
	public String getNome() {
		return equ_nome;
	}
	public void setNome(String nome) {
		this.equ_nome = nome;
	}
		
	public Equipe toModel() {
		return new Equipe(equ_id, equ_nome);
	}

	public static EquipeFormRequest fromModel(Equipe equipe) {
		return new EquipeFormRequest(equipe.getId(), equipe.getNome());
	}

}