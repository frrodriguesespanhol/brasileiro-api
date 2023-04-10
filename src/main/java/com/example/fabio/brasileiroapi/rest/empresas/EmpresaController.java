package com.example.fabio.brasileiroapi.rest.empresas;

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

import com.example.fabio.brasileiroapi.model.Empresa;
import com.example.fabio.brasileiroapi.model.repository.EmpresaRepository;


@RestController
@RequestMapping("/api/empresas")
@CrossOrigin("*")
public class EmpresaController {

	@Autowired
	private EmpresaRepository repository;

	@PostMapping
	public ResponseEntity salvar(@RequestBody EmpresaFormRequest request) {
		Empresa empresa = request.toModel();
		repository.save(empresa);
		return ResponseEntity.ok(EmpresaFormRequest.fromModel(empresa));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(
			@PathVariable Long id,
			@RequestBody EmpresaFormRequest request) {

		Optional<Empresa> empresaExistente = repository.findById(id);
		if(empresaExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Empresa empresa = request.toModel();
		empresa.setId(id);
		repository.save(empresa);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<EmpresaFormRequest> getById(@PathVariable Long id){
		return repository.findById(id)
				.map( EmpresaFormRequest::fromModel )
				.map( empresaFR -> ResponseEntity.ok(empresaFR) )
				.orElseGet( () -> ResponseEntity.notFound().build()  );				
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		return repository
				.findById(id)
				.map( empresa -> {
					repository.delete(empresa);
					return ResponseEntity.noContent().build();
				})
				.orElseGet( () -> ResponseEntity.notFound().build()  );			
	}

	@GetMapping
	public Page<EmpresaFormRequest> getLista(
		@RequestParam(value="nome", required= false, defaultValue = "") String nome,
		Pageable pageable
	){
		return repository
					.buscarPorNome("%" + nome + "%",pageable)
					.map( EmpresaFormRequest::fromModel );
	}
}