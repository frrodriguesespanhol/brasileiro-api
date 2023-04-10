package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Campeonato;
import com.example.fabio.brasileiroapi.model.Fase;
import com.example.fabio.brasileiroapi.model.Equipe;

public interface FaseRepository extends JpaRepository<Fase, Long> {
	
	@Query(" select f from Fase f where upper(f.nome) like upper(:nome) ")
	Page<Fase> buscarPorNome(
			@Param("nome") String nome,
			Pageable pageable);
}