package com.example.fabio.brasileiroapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "campeonatos")
public class Campeonato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cam_id")
	private Long id;
	@Column(name = "cam_nome")
	private String nome;
	@Column(name = "cam_ano")
	private String ano;

	public Campeonato() {
		super();
	}

	public Campeonato(Long id, String nome, String ano) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
	}

	/**
	 * @param nome
	 * @param ano
	 */
	public Campeonato(String nome, String ano) {
		super();
		this.nome = nome;
		this.ano = ano;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	
	@Override
	public String toString() {
		return "Campeonato [id=" + id + ", nome=" + nome + ", ano=" + ano + " ]";
	}

}