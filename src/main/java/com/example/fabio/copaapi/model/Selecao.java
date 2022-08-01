package com.example.fabio.copaapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "selecoes")
public class Selecao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sel_id")
	private Long id;
	@Column(name = "sel_nome")
	private String nome;
	

	public Selecao() {
		super();
	}

	public Selecao(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		
	}

	/**
	 * @param nome
	 */
	
	public Selecao(String nome) {
		super();
		this.nome = nome;
		
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
	
	
	@Override
	public String toString() {
		return "Selecao [id=" + id + ", nome=" + nome + "]";
	}

}