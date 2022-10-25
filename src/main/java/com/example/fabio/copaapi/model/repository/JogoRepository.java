package com.example.fabio.copaapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.copaapi.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
		
	@Query(" select j from Jogo j where"
			+ " (:fase='' or cast(j.fase.id as string) = :fase) and"
			+ " (:sel1='' or (cast(j.sel1.id as string) = :sel1"
			+ " or cast(j.sel2.id as string) = :sel1)) ")
	Page<Jogo> buscarPorFaseSelecao(
			@Param("fase") String fase,
			@Param("sel1") String sel1,
			Pageable pageable);
}