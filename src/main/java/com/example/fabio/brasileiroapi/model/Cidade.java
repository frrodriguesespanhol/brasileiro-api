package com.example.fabio.brasileiroapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cidades")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid_id")
	private Long id;
	@Column(name = "cid_nome")
	private String nome;
	@ManyToOne
	@JoinColumn(name = "cam_id")
	private Campeonato idCampeonato;



	public Cidade() {
		super();
	}

	public Cidade(Long id, String nome, Campeonato idCampeonato) {
		super();
		this.id = id;
		this.nome = nome;
		this.idCampeonato = idCampeonato;
		System.out.println(id + " " + nome + " " + idCampeonato);
	}

	/**
	 * @param nome
	 * @param idcampeonato
	 */
	public Cidade(String nome, Campeonato idCampeonato) {
		super();
		this.nome = nome;
		this.idCampeonato = idCampeonato;
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

	public Campeonato getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Campeonato idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	
	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", idcampeonato=" + idCampeonato + " ]";
	}

}