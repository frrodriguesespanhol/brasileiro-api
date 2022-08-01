package com.example.fabio.copaapi.rest.selecoes;

import java.time.LocalDate;

import com.example.fabio.copaapi.model.Selecao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SelecaoFormRequest {

	private Long sel_id;
	private String sel_nome;
	
	
	public SelecaoFormRequest() {
		super();
	}

	/**
	 * @param sel_id
	 * @param sel_nome
	
	 */
	public SelecaoFormRequest(Long id, String nome) {
		super();
		this.sel_id = id;
		this.sel_nome = nome;
		
	}

	public Long getId() {
		return sel_id;
	}
	public void setId(Long id) {
		this.sel_id = id;
	}
	public String getNome() {
		return sel_nome;
	}
	public void setNome(String nome) {
		this.sel_nome = nome;
	}
		
	public Selecao toModel() {
		return new Selecao(sel_id, sel_nome);
	}

	public static SelecaoFormRequest fromModel(Selecao selecao) {
		return new SelecaoFormRequest(selecao.getId(), selecao.getNome());
	}

}