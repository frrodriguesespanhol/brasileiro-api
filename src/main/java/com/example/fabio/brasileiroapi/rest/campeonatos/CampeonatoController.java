package com.example.fabio.brasileiroapi.rest.campeonatos;

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

import com.example.fabio.brasileiroapi.model.Campeonato;
import com.example.fabio.brasileiroapi.model.repository.CampeonatoRepository;

@RestController
@RequestMapping("/api/campeonatos")
@CrossOrigin("*")
public class CampeonatoController {

	@Autowired
	private CampeonatoRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody CampeonatoFormRequest request) {
		Campeonato campeonato = request.toModel();
		repository.save(campeonato);
		return ResponseEntity.ok(CampeonatoFormRequest.fromModel(campeonato));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody CampeonatoFormRequest request) {

		Optional<Campeonato> campeonatoExistente = repository.findById(id);
		if(campeonatoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Campeonato campeonato = request.toModel();
		campeonato.setId(id);
		repository.save(campeonato);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<CampeonatoFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( CampeonatoFormRequest::fromModel )
				.map( campeonatoFR -> ResponseEntity.ok(campeonatoFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( campeonato -> {
					repository.delete(campeonato);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<CampeonatoFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		@RequestParam(value="ano", required= false, defaultValue = "") String ano,
		Pageable pageable
	){
		return repository
					.buscarPorNomeAno("%" + nome + "%","%" + ano + "%" , pageable)
					.map( CampeonatoFormRequest::fromModel );
	}
}