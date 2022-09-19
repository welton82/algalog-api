package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
	
	private ModelMapper mapperMapper;
	
	public EntregaModel toModel(Entrega entrega) {
		return mapperMapper.map(entrega, EntregaModel.class);
	}
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return mapperMapper.map(entregaInput, Entrega.class);
	}
	
}
