package com.example.fabio.copaapi.rest.configuracao;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.fabio.copaapi.model.Configuracao;
import com.example.fabio.copaapi.model.Copa;
import com.example.fabio.copaapi.model.Empresa;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ConfiguracaoFormRequest {

	private Long id;
	private Empresa empresa;
	private Copa copa;
	private Integer pontos_cravada;
	private Integer pontos_acerto;
	private Integer pontos_erro;
	
		
	public ConfiguracaoFormRequest() {
		super();
	}
	
	public ConfiguracaoFormRequest(Long id, Empresa empresa, Copa copa, Integer pontos_cravada,
			Integer pontos_acerto, Integer pontos_erro)
	{
		super();
		this.id = id;
		this.empresa = empresa;
		this.copa = copa;
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

	public Copa getCopa() {
		return copa;
	}

	public void setCopa(Copa copa) {
		this.copa = copa;
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
		return new Configuracao(id, empresa, copa, pontos_cravada, pontos_acerto, pontos_erro);
	}
	
	public static ConfiguracaoFormRequest fromModel(Configuracao configuracao) {
		//System.out.println(configuracao);
		return new ConfiguracaoFormRequest(configuracao.getId(), configuracao.getEmpresa(),
				configuracao.getCopa(),	configuracao.getPontos_cravada(),
				configuracao.getPontos_acerto(),configuracao.getPontos_erro());
	}

}