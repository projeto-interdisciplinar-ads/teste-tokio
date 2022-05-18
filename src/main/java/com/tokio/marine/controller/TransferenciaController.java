package com.tokio.marine.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tokio.marine.dto.TransferenciaDTO;
import com.tokio.marine.model.Transferencia;
import com.tokio.marine.repository.TransferenciaRepository;
import com.tokio.marine.service.TransferenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/transferencia")
@CrossOrigin("*")
public class TransferenciaController {

	@Autowired
	TransferenciaRepository transferenciaRepository;

	@Autowired
	TransferenciaService transferenciaService;

	@GetMapping
	public List<TransferenciaDTO> listarTodos() {

		List<TransferenciaDTO> transferenciaResponse = transferenciaService.listarTodos();

		if (transferenciaResponse == null) {
			transferenciaResponse = new ArrayList<TransferenciaDTO>();
		}

		return transferenciaResponse;

	}

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Transferencia salvar(@RequestBody Transferencia transferencia) {
		return transferenciaService.salvar(transferencia);
	}
}
