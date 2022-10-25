package com.example.fabio.copaapi.rest.configuracao;

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

import com.example.fabio.copaapi.model.Configuracao;
import com.example.fabio.copaapi.model.Copa;
import com.example.fabio.copaapi.model.Empresa;
import com.example.fabio.copaapi.model.repository.ConfiguracaoRepository;

@RestController
@RequestMapping("/api/configuracao")
@CrossOrigin("*")
public class ConfiguracaoController {

	@Autowired
	private ConfiguracaoRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody ConfiguracaoFormRequest request) {
		Configuracao configuracao = request.toModel();
		System.out.println("Configuração --> " + configuracao);
		repository.save(configuracao);
		return ResponseEntity.ok(ConfiguracaoFormRequest.fromModel(configuracao));
		
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody ConfiguracaoFormRequest request) {

		Optional<Configuracao> configuracaoExistente = repository.findById(id);
		if(configuracaoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Configuracao configuracao = request.toModel();
		configuracao.setId(id);
		repository.save(configuracao);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<ConfiguracaoFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( ConfiguracaoFormRequest::fromModel )
				.map( configuracaoFR -> ResponseEntity.ok(configuracaoFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( configuracao -> {
					repository.delete(configuracao);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<ConfiguracaoFormRequest> getLista(
		@RequestParam(value="empresa", required= false) String empresa,
		@RequestParam(value="copa", required= false) String copa,
		Pageable pageable
		
	){ System.out.println(empresa + " - " + copa);
		return repository
					.buscarPorEmpresaCopa(empresa, copa , pageable)
					.map( ConfiguracaoFormRequest::fromModel );
	}
}