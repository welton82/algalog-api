package com.algaworks.algalog.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInput {
	
	@NotBlank
	private String descricao;
	
}
