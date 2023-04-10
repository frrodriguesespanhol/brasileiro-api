package com.example.fabio.brasileiroapi.rest.jogos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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


import com.example.fabio.brasileiroapi.model.Jogo;
import com.example.fabio.brasileiroapi.model.repository.JogoRepository;

@RestController
@RequestMapping("/api/jogos")
@CrossOrigin("*")
public class JogoController {

	@Autowired
	private JogoRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody JogoFormRequest request) {
		Jogo jogo = request.toModel();
		System.out.println("Jogo --> " + jogo);
		repository.save(jogo);
		return ResponseEntity.ok(JogoFormRequest.fromModel(jogo));
		
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody JogoFormRequest request) {

		Optional<Jogo> jogoExistente = repository.findById(id);
		if(jogoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Jogo jogo = request.toModel();
		jogo.setId(id);
		repository.save(jogo);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<JogoFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( JogoFormRequest::fromModel )
				.map( jogoFR -> ResponseEntity.ok(jogoFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( jogo -> {
					repository.delete(jogo);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<JogoFormRequest> getLista(
		@RequestParam(value="fase", required= false) String fase,
		@RequestParam(value="equ1", required= false) String equ1,
		Pageable pageable
		
	){ System.out.println(equ1 + " - " + fase);
		return repository
					.buscarPorFaseEquipe(fase, equ1 , pageable)
					.map( JogoFormRequest::fromModel );
	}
}