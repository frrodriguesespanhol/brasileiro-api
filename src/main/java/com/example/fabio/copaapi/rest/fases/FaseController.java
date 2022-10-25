package com.example.fabio.copaapi.rest.fases;

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

import com.example.fabio.copaapi.model.Fase;
import com.example.fabio.copaapi.model.Selecao;
import com.example.fabio.copaapi.model.repository.FaseRepository;
import com.example.fabio.copaapi.model.repository.SelecaoRepository;

@RestController
@RequestMapping("/api/fases")
@CrossOrigin("*")
public class FaseController {

	@Autowired
	private FaseRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody FaseFormRequest request) {
		Fase fase = request.toModel();
		repository.save(fase);
		return ResponseEntity.ok(FaseFormRequest.fromModel(fase));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody FaseFormRequest request) {

		Optional<Fase> faseExistente = repository.findById(id);
		if(faseExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Fase fase = request.toModel();
		fase.setId(id);
		repository.save(fase);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<FaseFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( FaseFormRequest::fromModel )
				.map( faseFR -> ResponseEntity.ok(faseFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( fase -> {
					repository.delete(fase);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<FaseFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		Pageable pageable
	){
		return repository
					.buscarPorNome("%" + nome + "%",pageable)
					.map( FaseFormRequest::fromModel );
	}
}