package com.example.fabio.brasileiroapi.rest.palpites;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
import com.example.fabio.brasileiroapi.model.Palpite;
import com.example.fabio.brasileiroapi.model.Ranking;
import com.example.fabio.brasileiroapi.model.repository.JogoRepository;
import com.example.fabio.brasileiroapi.model.repository.PalpiteRepository;
import com.fasterxml.jackson.annotation.JsonFormat;


@RestController
@RequestMapping("/api/palpites")
@CrossOrigin("*")
public class PalpiteController {

	@Autowired
	private PalpiteRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody PalpiteFormRequest request) {
		Palpite palpite = request.toModel();
		//System.out.println("Palpite --> " + palpite);
		repository.save(palpite);
		return ResponseEntity.ok(PalpiteFormRequest.fromModel(palpite));
		
	}

	@PutMapping("{id}")
	public ResponseEntity<String> atualizar(
			@PathVariable Long id,
			@RequestBody PalpiteFormRequest request) {

		Optional<Palpite> palpiteExistente = repository.findById(id);
		if(palpiteExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Palpite palpite = request.toModel();
		palpite.setId(id);

		//Date data = new Date();
		Date data = repository.buscarHoraAtual();
		System.out.println("Data do Banco: ");
		System.out.println(data);
		if(palpite.getJogo().getData_hora().before(data)) {
			System.out.println(palpite.getJogo().getData_hora().before(data));
			return ResponseEntity.ok().body("erro_data");
		}
		
		palpite.setData_hora(data);
 		repository.save(palpite);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<PalpiteFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( PalpiteFormRequest::fromModel )
				.map( palpiteFR -> ResponseEntity.ok(palpiteFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( palpite -> {
					repository.delete(palpite);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<PalpiteFormRequest> getLista(
		@RequestParam(value="data", required= false) String data,
		@RequestParam(value="usuario", required= false) String usuario,
		Pageable pageable)
	{
		//System.out.println(data + " - " + usuario);
		return repository
					.buscarPorDataUsuario(data , usuario, pageable)
					.map( PalpiteFormRequest::fromModel );
	}
	
	@GetMapping("/ranking")
	public Page<Ranking> getLista(Pageable pageable){ 
		return repository
					.buscarRanking(pageable);
	}
	
	@GetMapping("/proximo")
	public Page<PalpiteFormRequest> getLista2(
			@RequestParam(value="data", required= false) String data,
			@RequestParam(value="usuario", required= false) String usuario,
			@RequestParam(value="id", required= false) String id,
			Pageable pageable){ 
		return repository
					.buscarProximoPalpite(data, usuario, id, pageable)
					.map( PalpiteFormRequest::fromModel );
					
	}
		
}