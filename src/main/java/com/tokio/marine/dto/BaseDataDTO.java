package com.tokio.marine.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public abstract class BaseDataDTO extends BaseDocumentDTO {

	private LocalDate dataTransferencia;

	private LocalDate dataAgendamento;

	private String agendamentoFormatado;

	private String transferenciaFormatado;
	
}
