package com.example.fabio.brasileiroapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipes")
public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equ_id")
	private Long id;
	@Column(name = "equ_nome")
	private String nome;
	

	public Equipe() {
		super();
	}

	public Equipe(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		
	}

	public Equipe(String nome) {
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
		return "Equipe [id=" + id + ", nome=" + nome + "]";
	}

}