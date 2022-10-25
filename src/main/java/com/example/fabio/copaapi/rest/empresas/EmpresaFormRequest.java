package com.example.fabio.copaapi.rest.empresas;

import com.example.fabio.copaapi.model.Empresa;

public class EmpresaFormRequest {

	private Long emp_id;
	private String emp_nome;
	private String emp_cidade;
	
	
	public EmpresaFormRequest() {
		super();
	}


	public EmpresaFormRequest(Long id, String nome, String cidade) {
		super();
		this.emp_id = id;
		this.emp_nome = nome;
		this.emp_cidade = cidade;
		
	}

	public Long getId() {
		return emp_id;
	}
	public void setId(Long id) {
		this.emp_id = id;
	}
	public String getNome() {
		return emp_nome;
	}
	public void setNome(String nome) {
		this.emp_nome = nome;
	}
	
	public String getCidade() {
		return emp_cidade;
	}
	public void setCidade(String cidade) {
		this.emp_cidade = cidade;
	}
		
	public Empresa toModel() {
		return new Empresa(emp_id, emp_nome, emp_cidade);
	}

	public static EmpresaFormRequest fromModel(Empresa empresa) {
		return new EmpresaFormRequest(empresa.getId(), empresa.getNome(), empresa.getCidade());
	}

}