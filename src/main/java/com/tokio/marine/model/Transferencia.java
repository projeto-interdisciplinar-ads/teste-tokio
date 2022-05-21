package com.tokio.marine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Transferencia extends BaseData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5502401917657330677L;

	@Column(length = 6)
	private String contaOrigem;

	@Column(length = 6)
	private String contaDestino;

	private double valor;

	private double taxa;

	private String tipoOperacao;

}
