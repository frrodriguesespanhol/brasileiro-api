package com.example.fabio.copaapi.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.fabio.copaapi.model.Palpite;
import com.example.fabio.copaapi.model.Ranking;

public interface PalpiteRepository extends JpaRepository<Palpite, Long> {
		
	@Query(" select p from Palpite p where"
			+ " (:data='' or substring(cast(p.jogo.data_hora as string), 1, 10) = :data) and"
			+ " (:usuario='' or cast(p.usuario.id as string) = :usuario)"
			+ " order by p.jogo.data_hora")
	Page<Palpite> buscarPorDataUsuario(
			@Param("data") String data,
			@Param("usuario") String usuario,
			Pageable pageable);
	
	
	@Query(value = " SELECT seq, nome, pontuacao, cravadas "
			+ "FROM view_ranking "
			+ " order by pontuacao desc, cravadas desc, nome asc"
			, nativeQuery = true)
	Page<Ranking> buscarRanking(Pageable pageable);
	
}