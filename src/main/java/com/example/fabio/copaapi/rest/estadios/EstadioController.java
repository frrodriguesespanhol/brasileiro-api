package com.example.fabio.copaapi.rest.estadios;

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

import com.example.fabio.copaapi.model.Cidade;
import com.example.fabio.copaapi.model.Copa;
import com.example.fabio.copaapi.model.Estadio;
//import com.example.fabio.copaapi.model.repository.CidadeRepository;
import com.example.fabio.copaapi.model.repository.EstadioRepository;

@RestController
@RequestMapping("/api/estadios")
@CrossOrigin("*")
public class EstadioController {

	@Autowired
	private EstadioRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody EstadioFormRequest request) {
	//public void salvar(@RequestBody Estadio estadio) {
		Estadio estadio = request.toModel();
		//System.out.println(estadio);
		repository.save(estadio);
		return ResponseEntity.ok(EstadioFormRequest.fromModel(estadio));
		
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody EstadioFormRequest request) {

		Optional<Estadio> estadioExistente = repository.findById(id);
		if(estadioExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Estadio estadio = request.toModel();
		estadio.setId(id);
		repository.save(estadio);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<EstadioFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( EstadioFormRequest::fromModel )
				.map( estadioFR -> ResponseEntity.ok(estadioFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( estadio -> {
					repository.delete(estadio);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<EstadioFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		@RequestParam(value="idCidade", required= false, defaultValue = "") String idCidade,
		Pageable pageable
		
	){ System.out.println(idCidade + " - " + nome);
		return repository
					.buscarPorNomeCidade("%" + nome + "%", "%" + idCidade + "%", pageable)
					.map( EstadioFormRequest::fromModel );
	}
}