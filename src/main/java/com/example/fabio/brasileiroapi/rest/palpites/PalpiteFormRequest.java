package com.example.fabio.brasileiroapi.rest.palpites;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.fabio.brasileiroapi.model.Jogo;
import com.example.fabio.brasileiroapi.model.Palpite;
import com.example.fabio.brasileiroapi.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PalpiteFormRequest {

	private Long id;
	private Jogo jogo;
	private Usuario usuario;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_hora;
	private Integer gols_equ1;
	private Integer gols_equ2;
			
	public PalpiteFormRequest() {
		super();
	}
	
	public PalpiteFormRequest(Long id, Jogo jogo, Usuario usuario, Date data_hora,
							  Integer gols_equ1, Integer gols_equ2) {
		super();
		this.id = id;
		this.jogo = jogo;
		this.usuario = usuario;
		this.data_hora = data_hora;
		this.gols_equ1 = gols_equ1;
		this.gols_equ2 = gols_equ2;
		
		//System.out.println(this.jogo);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData_hora() {
		return data_hora;
	}

	public void setData_hora(Date data_hora) {
		this.data_hora = data_hora;
	}
	
	public Integer getGols_equ1() {
		return gols_equ1;
	}

	public void setGols_equ1(Integer gols_equ1) {
		this.gols_equ1 = gols_equ1;
	}

	public Integer getGols_equ2() {
		return gols_equ2;
	}

	public void setGols_equ2(Integer gols_equ2) {
		this.gols_equ2 = gols_equ2;
	}
	
	public Palpite toModel() {
		return new Palpite(id, jogo, usuario, data_hora, gols_equ1, gols_equ2);
	}
	
	public static PalpiteFormRequest fromModel(Palpite palpite) {
		//System.out.println(palpite);
		return new PalpiteFormRequest(palpite.getId(), palpite.getJogo(), palpite.getUsuario(),
				palpite.getData_hora(), palpite.getGols_equ1(), palpite.getGols_equ2());
	}
}