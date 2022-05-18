package com.tokio.marine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;	
import org.springframework.stereotype.Service;

import com.tokio.marine.dto.TransferenciaDTO;
import com.tokio.marine.exception.ResultadoNaoEncontradoException;
import com.tokio.marine.model.Transferencia;
import com.tokio.marine.repository.LogRepository;
import com.tokio.marine.repository.TransferenciaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransferenciaService extends BaseService {

	TransferenciaRepository transferenciaRepository;
	
	LogRepository logRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	public Transferencia calculoDias(Transferencia transferencia) {
		var total = 0;
		if (transferencia.getDataAgendamento() > transferencia.getDataTransferencia()) {
			total = transferencia.getDataAgendamento() - transferencia.getDataTransferencia();
		} else {
			total = transferencia.getDataTransferencia() - transferencia.getDataTransferencia();
		}
		transferencia.setDias(total);
		return transferencia;
	}

	public Transferencia calculoTaxa(Transferencia transferencia) {
		if (transferencia.getDias() == 0) {
			transferencia.setTaxa(((transferencia.getValor() + 3) * 1.3) - transferencia.getValor());
		} else if (transferencia.getDias() >= 1 && transferencia.getDias() <= 10) {
			transferencia.setTaxa((transferencia.getValor() + 12) - transferencia.getValor());
		} else if (transferencia.getDias() > 10 && transferencia.getDias() <= 20) {
			transferencia.setTaxa((transferencia.getValor() * 1.082) - transferencia.getValor());
		} else if (transferencia.getDias() > 20 && transferencia.getDias() <= 30) {
			transferencia.setTaxa((transferencia.getValor() * 1.069) - transferencia.getValor());
		} else if (transferencia.getDias() > 30 && transferencia.getDias() <= 40) {
			transferencia.setTaxa((transferencia.getValor() * 1.047) - transferencia.getValor());
		} else {
			transferencia.setTaxa((transferencia.getValor() * 1.017) - transferencia.getValor());
		}
		return transferencia;
	}

	public Transferencia salvar(Transferencia transferencia) {
		return transferenciaRepository.save(transferencia);
	}

	public List<TransferenciaDTO> listarTodos() {
		List<Transferencia> transferencia = transferenciaRepository.findAll();
		var transferenciaResponse = new Transferencia();
		this.calculoDias(transferenciaResponse);
		this.calculoTaxa(transferenciaResponse);
		if (transferencia.isEmpty()) {
			throw new ResultadoNaoEncontradoException();
		}
		return new ArrayList<>(transferencia.stream()
				.map(prod -> new ModelMapper().map(prod, TransferenciaDTO.class)).collect(Collectors.toList()));
	}

}