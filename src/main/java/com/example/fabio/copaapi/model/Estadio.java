package com.example.fabio.copaapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estadios")
public class Estadio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "est_id")
	private Long id;
	@Column(name = "est_nome")
	private String nome;
	@ManyToOne
	@JoinColumn(name = "cid_id")
	private Cidade idCidade;



	public Estadio() {
		super();
	}

	public Estadio(Long id, String nome, Cidade idCidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.idCidade = idCidade;
		System.out.println(id + " " + nome + " " + idCidade);
	}
	
	public Estadio(String nome, Cidade idCidade) {
		super();
		this.nome = nome;
		this.idCidade = idCidade;
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

	public Cidade getIdCidade() {
		return idCidade;
	}

	public void setIdCopa(Cidade idCidade) {
		this.idCidade = idCidade;
	}

	
	@Override
	public String toString() {
		return "Estadio [id=" + id + ", nome=" + nome + ", idCidade=" + idCidade + " ]";
	}

}