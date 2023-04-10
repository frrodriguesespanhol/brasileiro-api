package com.example.fabio.brasileiroapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.brasileiroapi.model.Cidade;
import com.example.fabio.brasileiroapi.model.Estadio;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
		
	@Query(" select c from Estadio c where upper(c.nome) like upper(:nome) and cast( c.idCidade.id as string) like :idCidade ")
	Page<Estadio> buscarPorNomeCidade(
			@Param("nome") String nome,
			@Param("idCidade") String idCidade,
			Pageable pageable);
}