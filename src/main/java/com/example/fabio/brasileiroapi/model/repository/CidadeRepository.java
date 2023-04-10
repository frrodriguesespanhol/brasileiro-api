package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Cidade;

public interface CidadeRepository extends JpaRepository<com.example.fabio.brasileiroapi.model.Cidade, Long> {
		
	@Query(" select c from Cidade c where upper(c.nome) like upper(:nome) and cast( c.idCampeonato.id as string) like :idCampeonato ")
	Page<Cidade> buscarPorNomeCidade(
			@Param("nome") String nome,
			@Param("idCampeonato") String idCampeonato,
			Pageable pageable);
}