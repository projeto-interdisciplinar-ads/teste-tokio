package com.tokio.marine.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

public class BaseService {

	protected static final ModelMapper modelMapper = new ModelMapper();

	public LocalDateTime validarDataInicioVigencia(LocalDateTime dataInicioExistente, LocalDateTime dataFimExistente,
			LocalDateTime dataInicioCadastro) {
		
		if(dataInicioCadastro.isBefore(dataInicioExistente)) {
			return null;
		} else if(dataInicioCadastro.isEqual(dataInicioExistente)) {
			dataFimExistente = dataInicioCadastro.minusDays(1L);
			return dataFimExistente;
		} else if(dataFimExistente != null && dataInicioCadastro.isBefore(dataFimExistente)) {
			return null;
		}
		return dataInicioCadastro;
	}
	
	public LocalDate validarDataInicioVigencia(LocalDate dataInicioExistente, LocalDate dataFimExistente,
			LocalDate dataInicioCadastro) {
		
		if(dataInicioCadastro.isBefore(dataInicioExistente)) {
			return null;
		} else if(dataInicioCadastro.isEqual(dataInicioExistente)) {
			dataFimExistente = dataInicioCadastro.minusDays(1L);
			return dataFimExistente;
		} else if(dataFimExistente != null && dataInicioCadastro.isBefore(dataFimExistente)) {
			return null;
		}
		return dataInicioCadastro;
	}
	
	public LocalDateTime validarDateTimeInicioVigencia(LocalDateTime dataInicioExistente, LocalDateTime dataFimExistente,
			LocalDate dataInicioCadastro) {
		
		LocalDateTime dataInicioCadastroTime = dataInicioCadastro.atStartOfDay();
		
		if(dataInicioCadastroTime.isBefore(dataInicioExistente)) {
			return null;
		} else if(dataInicioCadastroTime.isEqual(dataInicioExistente)) {
			dataFimExistente = dataInicioCadastroTime.minusDays(1L);
			return dataFimExistente;
		} else if(dataFimExistente != null && dataInicioCadastroTime.isBefore(dataFimExistente)) {
			return null;
		}
		return dataInicioCadastroTime;
	}

}
