package com.example.fabio.copaapi.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.fabio.copaapi.model.Palpite;
import com.example.fabio.copaapi.model.Ranking;
import com.fasterxml.jackson.annotation.JsonFormat;

public interface PalpiteRepository extends JpaRepository<Palpite, Long> {
		
	@Query(" select p from Palpite p where"
			+ " (:data='' or substring(cast(p.jogo.data_hora as string), 1, 10) = :data) and"
			+ " (:usuario='' or cast(p.usuario.id as string) = :usuario)"
			+ " order by p.jogo.data_hora asc, p.usuario.nome asc")
	Page<Palpite> buscarPorDataUsuario(
			@Param("data") String data,
			@Param("usuario") String usuario,
			Pageable pageable);

	@Query(value = " select * from palpites"
			+ " left join jogos on jogos.jog_id = palpites.jog_id "
			+ " where"
			+ " jogos.data_hora > :data and"
			+ " cast(p.usuario.id as string) = :usuario"
			+ " order by p.jogo.data_hora asc limit 1" , nativeQuery = true)
	Page<Palpite> buscarProximoPalpite(
			@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
			@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
			@Param("data") Date data,
			@Param("usuario") String usuario,
			Pageable pageable);
	
	@Query(value = " SELECT seq, nome, pontuacao, cravadas "
			+ "FROM view_ranking "
			+ " order by pontuacao desc, cravadas desc, nome asc"
			, nativeQuery = true)
	Page<Ranking> buscarRanking(Pageable pageable);
	
}