package com.example.fabio.brasileiroapi.rest.usuarios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabio.brasileiroapi.model.Cidade;
import com.example.fabio.brasileiroapi.model.Campeonato;
import com.example.fabio.brasileiroapi.model.Usuario;
import com.example.fabio.brasileiroapi.model.repository.CidadeRepository;
import com.example.fabio.brasileiroapi.model.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	private PasswordEncoder encoder;
	
	public UsuarioController( UsuarioRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioFormRequest request) {
		Usuario usuario = request.toModel();
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(usuario);
		return ResponseEntity.ok(UsuarioFormRequest.fromModel(usuario));
		
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody UsuarioFormRequest request) {

		Optional<Usuario> usuarioExistente = repository.findById(id);
		if(usuarioExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Usuario usuario = request.toModel();
		usuario.setId(id);
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(usuario);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<UsuarioFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( UsuarioFormRequest::fromModel )
				.map( usuarioFR -> ResponseEntity.ok(usuarioFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( usuario -> {
					repository.delete(usuario);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<UsuarioFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		@RequestParam(value="idEmpresa", required= false, defaultValue = "") String idEmpresa,
		Pageable pageable
		
	){ System.out.println(idEmpresa + " - " + nome);
		return repository
					.buscarPorNomeEmpresa("%" + nome + "%", "%" + idEmpresa + "%", pageable)
					.map( UsuarioFormRequest::fromModel );
	}
	
	@GetMapping("/validarSenha")
	public ResponseEntity<Usuario> validarSenha(@RequestParam String email,
												@RequestParam String senha) {
		System.out.println("ok");
		Optional<Usuario> optUsuario =repository.findByEmail(email);
		if (optUsuario.isEmpty()) {
			//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
			return ResponseEntity.notFound().build();
		}
		
		Usuario usuario = optUsuario.get();
		boolean valid = encoder.matches(senha, usuario.getSenha());
		
		if (!valid) {
			HttpStatus status = HttpStatus.UNAUTHORIZED;
			return ResponseEntity.notFound().build();
		}
		{   
			HttpStatus status = HttpStatus.OK;
			return ResponseEntity.status(status).body(usuario);}
		
	}
}