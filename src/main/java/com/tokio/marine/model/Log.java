package com.tokio.marine.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Log {

	@Id
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;

	private String usuarioAlteracao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataAlteracao;

	private String tipoOperacao;

	private String json;

	private String collection;

}
