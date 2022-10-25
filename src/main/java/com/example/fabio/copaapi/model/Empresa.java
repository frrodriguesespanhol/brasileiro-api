package com.example.fabio.copaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long id;
	@Column(name = "emp_nome")
	private String nome;
	@Column(name = "emp_cidade")
	private String cidade;
	

	public Empresa() {
		super();
	}

	public Empresa(Long id, String nome, String cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		
	}

	public Empresa(String nome, String cidade) {
		super();
		this.nome = nome;
		this.cidade = cidade;
		
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", cidade=" + cidade + "]";
	}

}