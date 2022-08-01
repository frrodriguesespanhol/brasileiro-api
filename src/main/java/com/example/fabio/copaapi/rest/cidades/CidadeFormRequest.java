package com.example.fabio.copaapi.rest.cidades;

import java.time.LocalDate;

import com.example.fabio.copaapi.model.Cidade;
import com.example.fabio.copaapi.model.Copa;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CidadeFormRequest {

	private Long id;
	private String nome;
	private Copa idCopa;
	
	public CidadeFormRequest() {
		super();
	}

	/**
	 * @param cid_id
	 * @param cid_nome
	 * @param cop_id
	 */
	
	public CidadeFormRequest(Long id, String nome, Copa idCopa) {
		super();
		this.id = id;
		this.nome = nome;
		this.idCopa= idCopa;
		//System.out.println(this.idCopa);
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
	public Copa getIdCopa() {
		return idCopa;
	}
	public void setIdCopa(Copa idCopa) {
		this.idCopa = idCopa;
	}
	
	public Cidade toModel() {
		return new Cidade(id, nome, idCopa);
	}
	
	public static CidadeFormRequest fromModel(Cidade cidade) {
		//System.out.println(cidade);
		return new CidadeFormRequest(cidade.getId(), cidade.getNome(), cidade.getIdCopa());
	}

}