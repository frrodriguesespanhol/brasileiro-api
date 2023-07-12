package com.example.fabio.brasileiroapi.rest.paises;

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

import com.example.fabio.brasileiroapi.model.Paises;
import com.example.fabio.brasileiroapi.model.repository.PaisesRepository;

@RestController
@RequestMapping("/api/paises")
@CrossOrigin("*")
public class PaisesController {

	@Autowired
	private PaisesRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody PaisesFormRequest request) {
		Paises paises = request.toModel();
		repository.save(paises);
		return ResponseEntity.ok(PaisesFormRequest.fromModel(paises));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody PaisesFormRequest request) {

		Optional<Paises> paisExistente = repository.findById(id);
		if(paisExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Paises paises = request.toModel();
		paises.setId(id);
		repository.save(paises);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<PaisesFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( PaisesFormRequest::fromModel )
				.map( paisesFR -> ResponseEntity.ok(paisesFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( paises -> {
					repository.delete(paises);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<PaisesFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		Pageable pageable
	){
		return repository
					.buscarPorNome("%" + nome + "%",pageable)
					.map( PaisesFormRequest::fromModel );
	}
}