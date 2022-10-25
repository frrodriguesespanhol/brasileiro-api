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
@Table(name = "palpites")
public class Palpite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pal_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "jog_id")
	private Jogo jogo;
	
	@ManyToOne
	@JoinColumn(name = "usu_id")
	private Usuario usuario;

	@Column(name = "pal_data_hora")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_hora;
	
	@Column(name = "pal_gols_sel1")
	private Integer gols_sel1;
	
	@Column(name = "pal_gols_sel2")
	private Integer gols_sel2;

	public Palpite() {
		super();
	}

	public Palpite(Long id, Jogo jogo, Usuario usuario, Date data_hora,
				   Integer gols_sel1, Integer gols_sel2) {
		
		super();
		//System.out.println("jogo ------>>> " + jogo);
		this.id = id;
		this.jogo = jogo;
		this.usuario = usuario;
		this.data_hora = data_hora;
		this.gols_sel1 = gols_sel1;
		this.gols_sel2 = gols_sel2;
		
		//System.out.println(id + " " + jogo + " " + usuario + " " + data_hora + " " + gols_sel1
		//		+ " " + gols_sel2);
	}
	
	public Palpite(Jogo jogo, Usuario usuario, Date data_hora, Integer gols_sel1,
			 		Integer gols_sel2) {
		super();
		this.jogo = jogo;
		this.usuario = usuario;
		this.data_hora = data_hora;
		this.gols_sel1 = gols_sel1;
		this.gols_sel2 = gols_sel2;
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

	@Override
	public String toString() {
		return "Palpite [id=" + id + ", jogo=" + jogo + ", usuario=" + usuario
				+ ", data_hora=" + data_hora + ", gols_sel1=" + gols_sel1
				+ ", gols_sel2=" + gols_sel2 + "]";
	}
	
}