package com.example.fabio.copaapi.model;

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
	@JoinColumn(name = "sel_id1")
	private Selecao sel1;
	
	@ManyToOne
	@JoinColumn(name = "sel_id2")
	private Selecao sel2;
	
	@Column(name = "jog_gols_sel1")
	private Long gols1;
	
	@Column(name = "jog_gols_sel2")
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

	public Jogo() {
		super();
	}

	public Jogo(Long id, Selecao sel1, Selecao sel2, Long gols1, Long gols2, Estadio estadio,
				Date data_hora, Fase fase) {
		
		super();
		System.out.println("sel1 ------>>> " + sel1);
		this.id = id;
		this.sel1 = sel1;
		this.sel2 = sel2;
		this.gols1 = gols1;
		this.gols2 = gols2;
		this.estadio = estadio;
		this.data_hora = data_hora;
		this.fase = fase;
		System.out.println(id + " " + sel1 + " " + sel2 + " " + gols1 + " " + gols2
				+ " " + estadio + " " + data_hora + " " + fase);
	}
	
	public Jogo(Selecao sel1, Selecao sel2, Long gols1, Long gols2, Estadio estadio,
			Date data_hora, Fase fase) {
		super();
		this.sel1 = sel1;
		this.sel2 = sel2;
		this.gols1 = gols1;
		this.gols2 = gols2;
		this.estadio = estadio;
		this.data_hora = data_hora;
		this.fase = fase;
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
		this.sel1 = sel1;
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

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", sel1=" + sel1 + ", sel2=" + sel2 + ", gols1=" + gols1 + ", gols2=" + gols2
				+ ", estadio=" + estadio + ", data_hora=" + data_hora + ", fase=" + fase + "]";
	}
	

}