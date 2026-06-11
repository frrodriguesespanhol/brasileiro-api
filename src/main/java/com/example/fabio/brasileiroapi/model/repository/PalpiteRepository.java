 package com.example.fabio.brasileiroapi.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.fabio.brasileiroapi.model.Palpite;
import com.example.fabio.brasileiroapi.model.Ranking;
import com.fasterxml.jackson.annotation.JsonFormat;

public interface PalpiteRepository extends JpaRepository<Palpite, Long> {

	@Query(value = " select pal_id, p.jog_id, p.usu_id, pal_data_hora, "
			+ " case when :logado <> u.usu_email and now() - interval '3 hour' < j.jog_data_hora and p.pal_data_hora is not null then -99 else pal_gols_equ1 end as pal_gols_equ1, "
			+ " case when :logado <> u.usu_email and now() - interval '3 hour' < j.jog_data_hora and p.pal_data_hora is not null then -99 else pal_gols_equ2 end as pal_gols_equ2, "
			+ " j.jog_data_hora from palpites p "
			+ " left join jogos j on j.jog_id = p.jog_id "
			+ " left join usuarios u on p.usu_id = u.usu_id "
			+ " where "
			+ " (:data='' or substring(cast(j.jog_data_hora as character varying), 1, 10) = :data) and "
			+ " (:usuario='' or cast(p.usu_id as character varying) = :usuario) "
			+ " order by j.jog_data_hora asc, j.jog_id, u.usu_nome asc ", nativeQuery = true)
	Page<Palpite> buscarPorDataUsuario2(
			@Param("data") String data,
			@Param("usuario") String usuario,
			@Param("logado") String logado,
			Pageable pageable);
	
	@Query(" select p from Palpite p where"
			+ " (:data='' or substring(cast(p.jogo.data_hora as string), 1, 10) = :data) and"
			+ " (:usuario='' or cast(p.usuario.id as string) = :usuario)"
			+ " order by p.jogo.data_hora asc, p.jogo.id, p.usuario.nome asc")
	Page<Palpite> buscarPorDataUsuario(
			@Param("data") String data,
			@Param("usuario") String usuario,
			Pageable pageable);

	@Query(value = " select * from view_proximo_palpite"
			+ " where"
			+ " hora_jogo >= cast(:data as timestamp without time zone) and"
			+ " jog_id > cast(:id as integer) and"
			+ " usu_id = cast(:usuario as integer)"
			+ " order by hora_jogo, jog_id", nativeQuery = true)
	Page<Palpite> buscarProximoPalpite(
			@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
			@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
			@Param("data") String data,
			@Param("usuario") String usuario,
			@Param("id") String id,
			Pageable pageable);
	
	@Query(value = " SELECT seq, nome, pontuacao, cravadas, colocacao "
			+ "FROM view_ranking "
			+ " order by pontuacao desc, cravadas desc, nome asc"
			, nativeQuery = true)
	Page<Ranking> buscarRanking(Pageable pageable);
	
	//@Query(value = " SELECT now()- interval '3 hour'", nativeQuery = true)
	@Query(value = " SELECT now()", nativeQuery = true)
	Date buscarHoraAtual();
	
}