package com.algaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@Service
public class CatalogoClienteService {
	
	@Autowired // ou @AllArgsConstructor no início da classe
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Cliente Não Localizado!"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Cliente cadastrado com este Email!!!");
		}else
			return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
	
}
