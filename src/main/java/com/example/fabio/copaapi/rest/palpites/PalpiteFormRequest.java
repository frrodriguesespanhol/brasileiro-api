package com.example.fabio.copaapi.rest.palpites;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.fabio.copaapi.model.Jogo;
import com.example.fabio.copaapi.model.Palpite;
import com.example.fabio.copaapi.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PalpiteFormRequest {

	private Long id;
	private Jogo jogo;
	private Usuario usuario;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_hora;
	private Integer gols_sel1;
	private Integer gols_sel2;
		
	public PalpiteFormRequest() {
		super();
	}
	
	public PalpiteFormRequest(Long id, Jogo jogo, Usuario usuario, Date data_hora,
							  Integer gols_sel1, Integer gols_sel2) {
		super();
		this.id = id;
		this.jogo = jogo;
		this.usuario = usuario;
		this.data_hora = data_hora;
		this.gols_sel1 = gols_sel1;
		this.gols_sel2 = gols_sel2;
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
	
	public Integer getGols_sel1() {
		return gols_sel1;
	}

	public void setGols_sel1(Integer gols_sel1) {
		this.gols_sel1 = gols_sel1;
	}

	public Integer getGols_sel2() {
		return gols_sel2;
	}

	public void setGols_sel2(Integer gols_sel2) {
		this.gols_sel2 = gols_sel2;
	}
	
	public Palpite toModel() {
		return new Palpite(id, jogo, usuario, data_hora, gols_sel1, gols_sel2);
	}
	
	public static PalpiteFormRequest fromModel(Palpite palpite) {
		//System.out.println(palpite);
		return new PalpiteFormRequest(palpite.getId(), palpite.getJogo(), palpite.getUsuario(),
				palpite.getData_hora(), palpite.getGols_sel1(), palpite.getGols_sel2());
	}
}