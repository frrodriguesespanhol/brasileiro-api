package com.example.fabio.brasileiroapi.rest.configuracao;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.fabio.brasileiroapi.model.Configuracao;
import com.example.fabio.brasileiroapi.model.Campeonato;
import com.example.fabio.brasileiroapi.model.Empresa;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ConfiguracaoFormRequest {

	private Long id;
	private Empresa empresa;
	private Campeonato campeonato;
	private Integer pontos_cravada;
	private Integer pontos_acerto;
	private Integer pontos_erro;
	
		
	public ConfiguracaoFormRequest() {
		super();
	}
	
	public ConfiguracaoFormRequest(Long id, Empresa empresa, Campeonato campeonato, Integer pontos_cravada,
			Integer pontos_acerto, Integer pontos_erro)
	{
		super();
		this.id = id;
		this.empresa = empresa;
		this.campeonato = campeonato;
		this.pontos_cravada = pontos_cravada;
		this.pontos_acerto = pontos_acerto;
		this.pontos_erro = pontos_erro;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa= empresa;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Integer getPontos_cravada() {
		return pontos_cravada;
	}

	public void setPontos_cravada(Integer pontos_cravada) {
		this.pontos_cravada = pontos_cravada;
	}

	public Integer getPontos_acerto() {
		return pontos_acerto;
	}

	public void setPontos_acerto(Integer pontos_acerto) {
		this.pontos_acerto = pontos_acerto;
	}

	public Integer getPontos_erro() {
		return pontos_erro;
	}

	public void setPontos_erro(Integer pontos_erro) {
		this.pontos_erro = pontos_erro;
	}
		
	public Configuracao toModel() {
		return new Configuracao(id, empresa, campeonato, pontos_cravada, pontos_acerto, pontos_erro);
	}
	
	public static ConfiguracaoFormRequest fromModel(Configuracao configuracao) {
		//System.out.println(configuracao);
		return new ConfiguracaoFormRequest(configuracao.getId(), configuracao.getEmpresa(),
				configuracao.getCampeonato(),	configuracao.getPontos_cravada(),
				configuracao.getPontos_acerto(),configuracao.getPontos_erro());
	}

}