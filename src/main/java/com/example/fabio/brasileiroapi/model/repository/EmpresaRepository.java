package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	@Query(" select e from Empresa e where upper(e.nome) like upper(:nome) ")
	Page<Empresa> buscarPorNome(
			@Param("nome") String nome,
			Pageable pageable);
}