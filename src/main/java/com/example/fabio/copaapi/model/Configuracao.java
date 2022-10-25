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
@Table(name = "configuracoes")
public class Configuracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "cop_id")
	private Copa copa;
	
	@Column(name = "con_pontos_cravada")
	private Integer pontos_cravada;
	
	@Column(name = "con_pontos_acerto")
	private Integer pontos_acerto;

	@Column(name = "con_pontos_erro")
	private Integer pontos_erro;

	public Configuracao() {
		super();
	}

	public Configuracao(Long id, Empresa empresa, Copa copa, Integer pontos_cravada,
						Integer pontos_acerto, Integer pontos_erro)
	{
		
		super();
		System.out.println("empresa ------>>> " + empresa);
		this.id = id;
		this.empresa = empresa;
		this.copa = copa;
		this.pontos_cravada = pontos_cravada;
		this.pontos_acerto = pontos_acerto;
		this.pontos_erro = pontos_erro;
		
		System.out.println(id + " " + empresa + " " + copa + " " + pontos_cravada + " " +
				pontos_acerto + " " + pontos_erro);
	}
	
	public Configuracao(Empresa empresa, Copa copa, Integer pontos_cravada,
						Integer pontos_acerto, Integer pontos_erro)
	{
		super();
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
		this.empresa = empresa;
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

	
	@Override
	public String toString() {
		return "Configuracao [id=" + id + ", empresa=" + empresa + ", copa=" + copa + ","
				+ "pontos_cravada=" + pontos_cravada + ", pontos_acerto=" + pontos_acerto
				+ ", pontos_erro=" + pontos_erro + "]";
	}

}