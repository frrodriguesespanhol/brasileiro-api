package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
	
	@Query(" select c from Campeonato c where upper(c.nome) like upper(:nome) and c.ano like :ano ")
	Page<Campeonato> buscarPorNomeAno(
			@Param("nome") String nome,
			@Param("ano") String ano,
			Pageable pageable);
}