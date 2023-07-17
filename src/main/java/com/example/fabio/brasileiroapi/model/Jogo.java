package com.example.fabio.brasileiroapi.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "jogos")
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jog_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "equ_id1")
	private Equipe equ1;
	
	@ManyToOne
	@JoinColumn(name = "equ_id2")
	private Equipe equ2;
	
	@Column(name = "jog_gols_equ1")
	private Long gols1;
	
	@Column(name = "jog_gols_equ2")
	private Long gols2;

	@ManyToOne
	@JoinColumn(name = "est_id")
	private Estadio estadio;

	@Column(name = "jog_data_hora")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_hora;
	
	@ManyToOne
	@JoinColumn(name = "fas_id")
	private Fase fase;
	
	@ManyToOne
	@JoinColumn(name = "cam_id")
	private Campeonato campeonato;

	public Jogo() {
		super();
	}

	public Jogo(Long id, Equipe equ1, Equipe equ2, Long gols1, Long gols2, Estadio estadio,
				Date data_hora, Fase fase, Campeonato campeonato) {
		
		super();
		System.out.println("equ1 ------>>> " + equ1);
		this.id = id;
		this.equ1 = equ1;
		this.equ2 = equ2;
		this.gols1 = gols1;
		this.gols2 = gols2;
		this.estadio = estadio;
		this.data_hora = data_hora;
		this.fase = fase;
		this.campeonato = campeonato;
		System.out.println(id + " " + equ1 + " " + equ2 + " " + gols1 + " " + gols2
				+ " " + estadio + " " + data_hora + " " + fase + " " + campeonato);
	}
	
	public Jogo(Equipe equ1, Equipe equ2, Long gols1, Long gols2, Estadio estadio,
			Date data_hora, Fase fase, Campeonato campeonato) {
		super();
		this.equ1 = equ1;
		this.equ2 = equ2;
		this.gols1 = gols1;
		this.gols2 = gols2;
		this.estadio = estadio;
		this.data_hora = data_hora;
		this.fase = fase;
		this.campeonato = campeonato;
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
		this.equ1 = equ1;
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

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	@Override
	public String toString() {
		return "Jogo [id=" + id + ", equ1=" + equ1 + ", equ2=" + equ2 + ", gols1=" + gols1 + ", gols2=" + gols2
				+ ", estadio=" + estadio + ", data_hora=" + data_hora + ", fase=" + fase + ", campeonato=" + campeonato + "]";
	}
	

}