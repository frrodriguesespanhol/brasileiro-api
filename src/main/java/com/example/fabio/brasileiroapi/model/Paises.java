package com.example.fabio.brasileiroapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paises")
public class Paises {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pai_id")
	private Long id;
	@Column(name = "pai_nome")
	private String nome;
	

	public Paises() {
		super();
	}

	public Paises(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		
	}

	public Paises(String nome) {
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
		return "País [id=" + id + ", nome=" + nome + "]";
	}

}