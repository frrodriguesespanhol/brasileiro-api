package com.example.fabio.copaapi.rest.selecoes;

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

import com.example.fabio.copaapi.model.Selecao;
import com.example.fabio.copaapi.model.repository.SelecaoRepository;

@RestController
@RequestMapping("/api/selecoes")
@CrossOrigin("*")
public class SelecaoController {

	@Autowired
	private SelecaoRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody SelecaoFormRequest request) {
		Selecao selecao = request.toModel();
		repository.save(selecao);
		return ResponseEntity.ok(SelecaoFormRequest.fromModel(selecao));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody SelecaoFormRequest request) {

		Optional<Selecao> selecaoExistente = repository.findById(id);
		if(selecaoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Selecao selecao = request.toModel();
		selecao.setId(id);
		repository.save(selecao);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<SelecaoFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( SelecaoFormRequest::fromModel )
				.map( selecaoFR -> ResponseEntity.ok(selecaoFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( selecao -> {
					repository.delete(selecao);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<SelecaoFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		Pageable pageable
	){
		return repository
					.buscarPorNome("%" + nome + "%",pageable)
					.map( SelecaoFormRequest::fromModel );
	}
}