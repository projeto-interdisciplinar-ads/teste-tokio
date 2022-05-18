package com.tokio.marine.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class TransferenciaDTO extends InicioFimVigenciaDTO {
	
	private String contaOrigem;
	
	private String contaDestino;
	
	private double valor;
	
	private int dataTransferencia;
	
	private int dataAgendamento;
	
	private double taxa;
	
	private int dias;
	
}
