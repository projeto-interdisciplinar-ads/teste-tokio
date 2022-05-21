package com.tokio.marine.dto;

import javax.persistence.Column;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class TransferenciaDTO extends BaseDataDTO {
	
	@Column(length = 6)
	private String contaOrigem;

	@Column(length = 6)
	private String contaDestino;

	private double valor;

	private double taxa;

	private String tipoOperacao;
	
}
