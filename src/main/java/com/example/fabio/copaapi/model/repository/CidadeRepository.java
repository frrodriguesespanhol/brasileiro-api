package com.example.fabio.copaapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.copaapi.model.Cidade;
import com.example.fabio.copaapi.model.Copa;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
		
	@Query(" select c from Cidade c where upper(c.nome) like upper(:nome) and cast( c.idCopa.id as string) like :idCopa ")
	Page<Cidade> buscarPorNomeCopa(
			@Param("nome") String nome,
			@Param("idCopa") String idCopa,
			Pageable pageable);
}