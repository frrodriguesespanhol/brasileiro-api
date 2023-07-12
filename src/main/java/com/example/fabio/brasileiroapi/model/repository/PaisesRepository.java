package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Paises;

public interface PaisesRepository extends JpaRepository<Paises, Long> {
	
	@Query(" select s from Paises s where upper(s.nome) like upper(:nome) ")
	Page<Paises> buscarPorNome(
			@Param("nome") String nome,
			Pageable pageable);
}