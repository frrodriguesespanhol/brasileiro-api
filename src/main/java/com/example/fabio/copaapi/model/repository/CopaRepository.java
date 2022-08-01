package com.example.fabio.copaapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.copaapi.model.Copa;

public interface CopaRepository extends JpaRepository<Copa, Long> {
	
	@Query(" select c from Copa c where upper(c.nome) like upper(:nome) and c.ano like :ano ")
	Page<Copa> buscarPorNomeAno(
			@Param("nome") String nome,
			@Param("ano") String ano,
			Pageable pageable);
}