package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Campeonato;
import com.example.fabio.brasileiroapi.model.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
	
	@Query(" select s from Equipe s where upper(s.nome) like upper(:nome) ")
	Page<Equipe> buscarPorNome(
			@Param("nome") String nome,
			Pageable pageable);
}