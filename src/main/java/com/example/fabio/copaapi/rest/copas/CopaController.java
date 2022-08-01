package com.example.fabio.copaapi.rest.copas;

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

import com.example.fabio.copaapi.model.Copa;
import com.example.fabio.copaapi.model.repository.CopaRepository;

@RestController
@RequestMapping("/api/copas")
@CrossOrigin("*")
public class CopaController {

	@Autowired
	private CopaRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody CopaFormRequest request) {
		Copa copa = request.toModel();
		repository.save(copa);
		return ResponseEntity.ok(CopaFormRequest.fromModel(copa));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody CopaFormRequest request) {

		Optional<Copa> copaExistente = repository.findById(id);
		if(copaExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Copa copa = request.toModel();
		copa.setId(id);
		repository.save(copa);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<CopaFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( CopaFormRequest::fromModel )
				.map( copaFR -> ResponseEntity.ok(copaFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( copa -> {
					repository.delete(copa);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<CopaFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		@RequestParam(value="ano", required= false, defaultValue = "") String ano,
		Pageable pageable
	){
		return repository
					.buscarPorNomeAno("%" + nome + "%","%" + ano + "%" , pageable)
					.map( CopaFormRequest::fromModel );
	}
}