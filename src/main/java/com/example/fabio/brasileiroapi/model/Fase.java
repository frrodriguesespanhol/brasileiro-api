package com.example.fabio.brasileiroapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fases")
public class Fase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fas_id")
	private Long id;
	@Column(name = "fas_nome")
	private String nome;
	

	public Fase() {
		super();
	}

	public Fase(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		
	}

	public Fase(String nome) {
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
		return "Fase [id=" + id + ", nome=" + nome + "]";
	}

}