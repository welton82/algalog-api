package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private EntregaRepository entregaRepository; 
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long id) {
		Entrega entrega = buscaEntregaService.buscar(id);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}
	
}





