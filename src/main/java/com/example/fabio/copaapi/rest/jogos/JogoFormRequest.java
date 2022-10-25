package com.example.fabio.copaapi.rest.jogos;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.fabio.copaapi.model.Cidade;
import com.example.fabio.copaapi.model.Estadio;
import com.example.fabio.copaapi.model.Fase;
import com.example.fabio.copaapi.model.Jogo;
import com.example.fabio.copaapi.model.Selecao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class JogoFormRequest {

	private Long id;
	private Selecao sel1;
	private Selecao sel2;
	private Long gols1;
	private Long gols2;
	private Estadio estadio;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_hora;
	private Fase fase;
		
	public JogoFormRequest() {
		super();
	}
	
	public JogoFormRequest(Long id, Selecao sel1, Selecao sel2, Long gols1, Long gols2,
			Estadio estadio, Date data_hora, Fase fase) {
		super();
		this.id = id;
		this.sel1 = sel1;
		this.sel2 = sel2;
		this.gols1 = gols1;
		this.gols2 = gols2;
		this.estadio = estadio;
		this.data_hora = data_hora;
		this.fase = fase;
		//System.out.println(this.sel1);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Selecao getSel1() {
		return sel1;
	}

	public void setSel1(Selecao sel1) {
		this.sel1= sel1;
	}

	public Selecao getSel2() {
		return sel2;
	}

	public void setSel2(Selecao sel2) {
		this.sel2 = sel2;
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
	
	public Jogo toModel() {
		return new Jogo(id, sel1, sel2, gols1, gols2, estadio, data_hora, fase);
	}
	
	public static JogoFormRequest fromModel(Jogo jogo) {
		//System.out.println(jogo);
		return new JogoFormRequest(jogo.getId(), jogo.getSel1(), jogo.getSel2(),
				jogo.getGols1(), jogo.getGols2(), jogo.getEstadio(), jogo.getData_hora(),
				jogo.getFase());
	}

}