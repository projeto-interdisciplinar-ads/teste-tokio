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
	/*
	 * @Autowired Gson gson;
	 */

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

	/*
	 * @SuppressWarnings({ "rawtypes", "unchecked" }) public
	 * ResponseEntity<TransferenciaResponse> salvar(TransferenciaResponse
	 * transferenciaResponse) { transferenciaReposi
	 * 
	 * Transferencia transferencia = modelMapper.map(transferenciaResponse,
	 * Transferencia.class); ResponseEntity responseEntity = new
	 * ResponseEntity(HttpStatus.CREATED); try {
	 * transferencia.setDataInicioVigencia(transferenciaResponse.
	 * getDataInicioVigencia().atTime(0, 0, 0));a
	 * transferenciaRepository.save(transferencia); if
	 * (transferenciaResponse.getDataTerminoVigencia() != null) {
	 * transferencia.setDataTerminoVigencia(transferenciaResponse.
	 * getDataTerminoVigencia().atTime(23, 59, 59)); }
	 * transferencia.setDataAtualizacao(LocalDate.now());
	 * transferenciaRepository.save(transferencia); } catch (Exception e) {
	 * responseEntity =
	 * ResponseEntity.status(HttpStatus.CREATED).body("Data Inválida"); } return
	 * responseEntity;
	 * 
	 * }
	 */

	/*
	 * public void alterar(TransferenciaResponse transferenciaResponse) { Log log =
	 * new Log(); Transferencia transferencia =
	 * transferenciaRepository.findAll(transferenciaResponse.getId());
	 * log.setTipoOperacao("UPDATE"); log.setDataAlteracao(new Date());
	 * log.setJson(gson.toJson(transferencia));
	 * log.setCollection(transferencia.getClass().getSimpleName());
	 * logRepository.save(log);
	 * 
	 * transferencia.setDataInicioVigencia(transferenciaResponse.
	 * getDataInicioVigencia().atTime(00, 00, 00)); if
	 * (transferenciaResponse.getDataTerminoVigencia() != null) {
	 * transferencia.setDataTerminoVigencia(transferenciaResponse.
	 * getDataTerminoVigencia().atTime(00, 00, 00)); }
	 * 
	 * transferencia.setDataAtualizacao(LocalDate.now()); //usuario atualização
	 * transferenciaRepository.save(transferencia); }
	 */

	/*
	 * private boolean validarDataParaImportacao(Transferencia transferencia) {
	 * boolean retornoValor = false;
	 * 
	 * if
	 * (transferencia.getDataInicioVigencia().isBefore(LocalDateTime.now().minusDays
	 * (1))) { throw new DateTimeException(""); }
	 * 
	 * List<Transferencia> transferenciaIgual =
	 * transferenciaRepository.listarTransferenciasIguais(transferencias); if
	 * (transferenciasIguais != null && !transferenciasIguais.isEmpty()) {
	 * retornoValor = true; for (Transferencia transferenciaRealizada :
	 * transferenciasIguais) { if
	 * (transferencia.getDataInicioVigencia().isBefore(transferenciaRealizada.
	 * getDataInicioVigencia())) { if (transferencia.getDataTerminoVigencia() ==
	 * null ||
	 * transferencia.getDataTerminoVigencia().isAfter(transferenciaRealizada.
	 * getDataInicioVigencia())) {
	 * transferencia.setDataTerminoVigencia(transferenciaRealizada.
	 * getDataInicioVigencia().minusDays(1)) } break; } if
	 * (transferencia.getDataInicioVigencia().isAfter(transferenciaRealizada.
	 * getDataInicioVigencia())) { if
	 * (transferenciaRealizada.getDataTerminoVigencia() == null) {
	 * transferenciaRealizada.setDataTerminoVigencia(transferencia.
	 * getDataInicioVigencia().minusDays(1));
	 * transferenciaRepository.save(transferenciaRealizada); } if
	 * (transferencia.getDataTerminoVigencia().isBefore(transferenciaRealizada.
	 * getDataTerminoVigencia())) { throw new DuplicateKeyException(""); } } } }
	 * retornoValor; }
	 */

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
