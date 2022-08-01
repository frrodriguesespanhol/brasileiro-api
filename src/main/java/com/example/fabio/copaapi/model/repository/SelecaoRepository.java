package com.example.fabio.copaapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.copaapi.model.Copa;
import com.example.fabio.copaapi.model.Selecao;

public interface SelecaoRepository extends JpaRepository<Selecao, Long> {
	
	@Query(" select s from Selecao s where upper(s.nome) like upper(:nome) ")
	Page<Selecao> buscarPorNome(
			@Param("nome") String nome,
			Pageable pageable);
}