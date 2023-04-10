package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
		
	@Query(" select j from Jogo j where"
			+ " (:fase='' or cast(j.fase.id as string) = :fase) and"
			+ " (:equ1='' or (cast(j.equ1.id as string) = :equ1"
			+ " or cast(j.equ2.id as string) = :equ1)) ")
	Page<Jogo> buscarPorFaseEquipe(
			@Param("fase") String fase,
			@Param("equ1") String equ1,
			Pageable pageable);
}