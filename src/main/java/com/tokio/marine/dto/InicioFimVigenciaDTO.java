package com.tokio.marine.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class InicioFimVigenciaDTO extends BaseDocumentDTO {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	LocalDate dataInicioVigencia;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	LocalDate dataTerminoVigencia;

	public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
		if (dataInicioVigencia != null) {
			this.dataInicioVigencia = dataInicioVigencia.with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay());
		}
	}

	public void setDataTerminoVigencia(LocalDate dataTerminoVigencia) {
		if (dataTerminoVigencia != null) {
			this.dataTerminoVigencia = dataTerminoVigencia.with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay());
		}
	}

	public LocalDate getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public LocalDate getDataTerminoVigencia() {
		return dataTerminoVigencia;
	}

}
