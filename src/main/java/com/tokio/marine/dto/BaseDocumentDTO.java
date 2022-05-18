package com.tokio.marine.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseDocumentDTO {

	protected Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	protected LocalDate dataAtualizacao;

	protected String usuarioAtualizacao;
}
