package com.example.fabio.copaapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
	@JoinColumn(name = "cop_id")
	private Copa idCopa;



	public Cidade() {
		super();
	}

	public Cidade(Long id, String nome, Copa idCopa) {
		super();
		this.id = id;
		this.nome = nome;
		this.idCopa = idCopa;
		System.out.println(id + " " + nome + " " + idCopa);
	}

	/**
	 * @param nome
	 * @param idcopa
	 */
	public Cidade(String nome, Copa idCopa) {
		super();
		this.nome = nome;
		this.idCopa = idCopa;
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

	public Copa getIdCopa() {
		return idCopa;
	}

	public void setIdCopa(Copa idCopa) {
		this.idCopa = idCopa;
	}

	
	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", idcopa=" + idCopa + " ]";
	}

}