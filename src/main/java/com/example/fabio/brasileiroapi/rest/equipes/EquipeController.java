package com.example.fabio.brasileiroapi.rest.equipes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.example.fabio.brasileiroapi.model.Equipe;
import com.example.fabio.brasileiroapi.model.repository.EquipeRepository;

@RestController
@RequestMapping("/api/equipes")
@CrossOrigin("*")
public class EquipeController {

	@Autowired
	private EquipeRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody EquipeFormRequest request) {
		Equipe equipe = request.toModel();
		repository.save(equipe);
		return ResponseEntity.ok(EquipeFormRequest.fromModel(equipe));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody EquipeFormRequest request) {

		Optional<Equipe> equipeExistente = repository.findById(id);
		if(equipeExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Equipe equipe = request.toModel();
		equipe.setId(id);
		repository.save(equipe);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<EquipeFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( EquipeFormRequest::fromModel )
				.map( equipeFR -> ResponseEntity.ok(equipeFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( equipe -> {
					repository.delete(equipe);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<EquipeFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		Pageable pageable
	){
		return repository
					.buscarPorNome("%" + nome + "%",pageable)
					.map( EquipeFormRequest::fromModel );
	}
}