package com.tokio.marine.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class BaseData extends BaseDocument {
	
	private LocalDate dataTransferencia;

	private LocalDate dataAgendamento;
	
	private String agendamentoFormatado;
	
	private String transferenciaFormatado;
	
}
