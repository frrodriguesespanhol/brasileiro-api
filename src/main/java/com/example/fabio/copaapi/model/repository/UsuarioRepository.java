package com.example.fabio.copaapi.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fabio.copaapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);
	
	@Query(" select u from Usuario u where upper(u.nome) like upper(:nome) and cast( u.idEmpresa.id as string) like :idEmpresa ")
	Page<Usuario> buscarPorNomeEmpresa(
			@Param("nome") String nome,
			@Param("idEmpresa") String idEmpresa,
			Pageable pageable);
}