package com.example.fabio.brasileiroapi.rest.cidades;

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

import com.example.fabio.brasileiroapi.model.Cidade;
import com.example.fabio.brasileiroapi.model.Campeonato;
import com.example.fabio.brasileiroapi.model.repository.CidadeRepository;

@RestController
@RequestMapping("/api/cidades")
@CrossOrigin("*")
public class CidadeController {

	@Autowired
	private CidadeRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody CidadeFormRequest request) {
	//public void salvar(@RequestBody Cidade cidade) {
		Cidade cidade = request.toModel();
		//System.out.println(cidade);
		repository.save(cidade);
		return ResponseEntity.ok(CidadeFormRequest.fromModel(cidade));
		
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody CidadeFormRequest request) {

		Optional<Cidade> cidadeExistente = repository.findById(id);
		if(cidadeExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cidade cidade = request.toModel();
		cidade.setId(id);
		repository.save(cidade);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<CidadeFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( CidadeFormRequest::fromModel )
				.map( cidadeFR -> ResponseEntity.ok(cidadeFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( cidade -> {
					repository.delete(cidade);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<CidadeFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		@RequestParam(value="idCampeonato", required= false, defaultValue = "") String idCampeonato,
		Pageable pageable
		
	){ System.out.println(idCampeonato + " - " + nome);
		return repository
					.buscarPorNomeCidade("%" + nome + "%", "%" + idCampeonato + "%", pageable)
					.map( CidadeFormRequest::fromModel );
	}
}