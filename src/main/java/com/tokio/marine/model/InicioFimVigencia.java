package com.tokio.marine.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@Entity
public class InicioFimVigencia extends BaseDocument {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	LocalDateTime dataInicioVigencia;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	LocalDateTime dataTerminoVigencia;

	public void setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
		if (dataInicioVigencia != null) {
			this.dataInicioVigencia = dataInicioVigencia.with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay());
		}
	}

	public void setDataTerminoVigencia(LocalDateTime dataTerminoVigencia) {
		if (dataTerminoVigencia != null) {
			this.dataTerminoVigencia = dataTerminoVigencia.with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay());
		}
	}

}
