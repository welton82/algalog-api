package com.algaworks.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private ClienteRepository clienteRep;
	
	private CatalogoClienteService catalogoClienteService;
	
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRep.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
//		Optional <Cliente> cliente = clienteRep.findById(id);
//		
//		if (cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}else
//			return ResponseEntity.notFound().build();
		//OU.....
		return clienteRep.findById(id)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
		//return clienteRep.save(cliente);
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente>atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
		if(!clienteRep.existsById(id)) {
			return ResponseEntity.noContent().build();
		}else {
			
			cliente.setId(id);
			//cliente = clienteRep.save(cliente);
			cliente = catalogoClienteService.salvar(cliente);
			
			return ResponseEntity.ok(cliente);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		if(!clienteRep.existsById(id)) {
			return ResponseEntity.noContent().build();
		}else {
			//clienteRep.deleteById(id);
			catalogoClienteService.excluir(id);
			return ResponseEntity.noContent().build();//retorno vazio
		}
	}
	
	
}













