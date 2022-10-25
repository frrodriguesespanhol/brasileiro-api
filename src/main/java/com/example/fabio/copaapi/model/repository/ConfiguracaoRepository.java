package com.example.fabio.copaapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.copaapi.model.Configuracao;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
		
	@Query(" select c from Configuracao c where"
			+ " (:empresa='' or cast(c.empresa.id as string) = :empresa) and"
			+ " (:copa='' or cast(c.copa.id as string) = :copa)")
	Page<Configuracao> buscarPorEmpresaCopa(
			@Param("copa") String copa,
			@Param("empresa") String empresa,
			Pageable pageable);
}