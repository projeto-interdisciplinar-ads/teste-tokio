package com.tokio.marine.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	/*
	 * @Autowired Gson gson;
	 */

	private static final ModelMapper modelMapper = new ModelMapper();

	public Transferencia calculoTaxa(Transferencia transferencia) {
		int dias = transferencia.getDataTransferencia().getDayOfYear()
				- transferencia.getDataAgendamento().getDayOfYear();
		if (transferencia.getTipoOperacao().contains("A")) {
			if (transferencia.getDataAgendamento().equals(transferencia.getDataTransferencia())) {
				transferencia.setTaxa((transferencia.getValor() * 0.03) + 3);
			}
		} else if (transferencia.getTipoOperacao().contains("B")) {
			if (dias <= 10) {
				transferencia.setTaxa(12);
			}
		} else if (transferencia.getTipoOperacao().contains("C")) {
			if (dias > 10 && dias <= 20) {
				transferencia.setTaxa(transferencia.getValor() * 0.082);
			} else if (dias > 20 && dias <= 30) {
				transferencia.setTaxa(transferencia.getValor() * 0.069);
			} else if (dias > 30 && dias <= 50) {
				transferencia.setTaxa(transferencia.getValor() * 0.047);
			} else if (dias > 40) {
				transferencia.setTaxa(transferencia.getValor() * 0.017);
			}
		} else if (transferencia.getTipoOperacao().contains("D")) {
			if (transferencia.getValor() <= 1000) {
				if (transferencia.getDataAgendamento().equals(transferencia.getDataTransferencia())) {
					transferencia.setTaxa((transferencia.getValor() * 0.03) + 3);
				}
			} else if (transferencia.getValor() >= 1001 && transferencia.getValor() <= 2000) {
				if (dias <= 10) {
					transferencia.setTaxa(12);
				}
			} else if (transferencia.getValor() > 2000) {
				if (dias > 10 && dias <= 20) {
					transferencia.setTaxa(transferencia.getValor() * 0.082);
				} else if (dias > 20 && dias <= 30) {
					transferencia.setTaxa(transferencia.getValor() * 0.069);
				} else if (dias > 30 && dias <= 50) {
					transferencia.setTaxa(transferencia.getValor() * 0.047);
				} else if (dias > 40) {
					transferencia.setTaxa(transferencia.getValor() * 0.017);
				}
			}
		}
		return transferencia;
	}

	public Transferencia salvar(Transferencia transferencia) throws Exception {
		calculoTaxa(transferencia);
		if (transferencia.getTaxa() == 0) {
			throw new Exception("Sem taxas aplicáveis.");
		} else {
			transferencia.setDataAtualizacao(LocalDate.now());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			transferencia.setAgendamentoFormatado(transferencia.getDataAgendamento().format(formatter));
			transferencia.setTransferenciaFormatado(transferencia.getDataTransferencia().format(formatter));
			return transferenciaRepository.save(transferencia);
		}
	}

	public List<TransferenciaDTO> listarTodos() {
		List<Transferencia> transferencia = transferenciaRepository.findAll();
		if (transferencia.isEmpty()) {
			throw new ResultadoNaoEncontradoException();
		}
		return new ArrayList<>(transferencia.stream().map(prod -> new ModelMapper().map(prod, TransferenciaDTO.class))
				.collect(Collectors.toList()));
	}

}
