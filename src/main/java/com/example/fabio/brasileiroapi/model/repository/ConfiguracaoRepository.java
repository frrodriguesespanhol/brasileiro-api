package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Configuracao;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
		
	@Query(" select c from Configuracao c where"
			+ " (:empresa='' or cast(c.empresa.id as string) = :empresa) and"
			+ " (:campeonato='' or cast(c.campeonato.id as string) = :campeonato)")
	Page<Configuracao> buscarPorEmpresaCampeonato(
			@Param("campeonato") String campeonato,
			@Param("empresa") String empresa,
			Pageable pageable);
}