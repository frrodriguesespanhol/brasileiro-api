package com.example.fabio.brasileiroapi.rest.paises;

import com.example.fabio.brasileiroapi.model.Paises;

public class PaisesFormRequest {

	private Long pai_id;
	private String pai_nome;
	
	
	public PaisesFormRequest() {
		super();
	}

	/**
	 * @param pai_id
	 * @param pai_nome
	
	 */
	public PaisesFormRequest(Long id, String nome) {
		super();
		this.pai_id = id;
		this.pai_nome = nome;
		
	}

	public Long getId() {
		return pai_id;
	}
	public void setId(Long id) {
		this.pai_id = id;
	}
	public String getNome() {
		return pai_nome;
	}
	public void setNome(String nome) {
		this.pai_nome = nome;
	}
		
	public Paises toModel() {
		return new Paises(pai_id, pai_nome);
	}

	public static PaisesFormRequest fromModel(Paises paises) {
		return new PaisesFormRequest(paises.getId(), paises.getNome());
	}

}