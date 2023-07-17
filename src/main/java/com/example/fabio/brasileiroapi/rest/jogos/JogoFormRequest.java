package com.example.fabio.brasileiroapi.rest.jogos;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.fabio.brasileiroapi.model.Campeonato;
import com.example.fabio.brasileiroapi.model.Cidade;
import com.example.fabio.brasileiroapi.model.Estadio;
import com.example.fabio.brasileiroapi.model.Fase;
import com.example.fabio.brasileiroapi.model.Jogo;
import com.example.fabio.brasileiroapi.model.Equipe;
import com.fasterxml.jackson.annotation.JsonFormat;

public class JogoFormRequest {

	private Long id;
	private Equipe equ1;
	private Equipe equ2;
	private Long gols1;
	private Long gols2;
	private Estadio estadio;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_hora;
	private Fase fase;
	private Campeonato campeonato;
		
	public JogoFormRequest() {
		super();
	}
	
	public JogoFormRequest(Long id, Equipe equ1, Equipe equ2, Long gols1, Long gols2,
			Estadio estadio, Date data_hora, Fase fase, Campeonato campeonato) {
		super();
		this.id = id;
		this.equ1 = equ1;
		this.equ2 = equ2;
		this.gols1 = gols1;
		this.gols2 = gols2;
		this.estadio = estadio;
		this.data_hora = data_hora;
		this.fase = fase;
		this.campeonato = campeonato;
		//System.out.println(this.equ1);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Equipe getEqu1() {
		return equ1;
	}

	public void setEqu1(Equipe equ1) {
		this.equ1= equ1;
	}

	public Equipe getEqu2() {
		return equ2;
	}

	public void setEqu2(Equipe equ2) {
		this.equ2 = equ2;
	}

	public Long getGols1() {
		return gols1;
	}

	public void setGols1(Long gols1) {
		this.gols1 = gols1;
	}

	public Long getGols2() {
		return gols2;
	}

	public void setGols2(Long gols2) {
		this.gols2 = gols2;
	}

	public Estadio getEstadio() {
		return estadio;
	}

	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

	public Date getData_hora() {
		return data_hora;
	}

	public void setData_hora(Date data_hora) {
		this.data_hora = data_hora;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}
	
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCAmpeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	public Jogo toModel() {
		return new Jogo(id, equ1, equ2, gols1, gols2, estadio, data_hora, fase, campeonato);
	}
	
	public static JogoFormRequest fromModel(Jogo jogo) {
		//System.out.println(jogo);
		return new JogoFormRequest(jogo.getId(), jogo.getEqu1(), jogo.getEqu2(),
				jogo.getGols1(), jogo.getGols2(), jogo.getEstadio(), jogo.getData_hora(),
				jogo.getFase(), jogo.getCampeonato());
	}

}