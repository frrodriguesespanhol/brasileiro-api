package com.example.fabio.brasileiroapi.rest.campeonatos;

import java.time.LocalDate;

import com.example.fabio.brasileiroapi.model.Campeonato;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CampeonatoFormRequest {

	private Long cam_id;
	private String cam_nome;
	private String cam_ano;
	
	public CampeonatoFormRequest() {
		super();
	}

	/**
	 * @param cam_id
	 * @param cam_nome
	 * @param cam_ano
	 */
	public CampeonatoFormRequest(Long id, String nome, String ano) {
		super();
		this.cam_id = id;
		this.cam_nome = nome;
		this.cam_ano = ano;
	}

	public Long getId() {
		return cam_id;
	}
	public void setId(Long id) {
		this.cam_id = id;
	}
	public String getNome() {
		return cam_nome;
	}
	public void setNome(String nome) {
		this.cam_nome = nome;
	}
	public String getAno() {
		return cam_ano;
	}
	public void setAno(String ano) {
		this.cam_ano = ano;
	}
	
	public Campeonato toModel() {
		return new Campeonato(cam_id, cam_nome, cam_ano);
	}

	public static CampeonatoFormRequest fromModel(Campeonato campeonato) {
		return new CampeonatoFormRequest(campeonato.getId(), campeonato.getNome(), campeonato.getAno());
	}

}