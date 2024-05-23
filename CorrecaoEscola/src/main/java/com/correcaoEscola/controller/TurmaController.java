package com.correcaoEscola.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.correcaoEscola.entities.Turma;
import com.correcaoEscola.service.TurmaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Turma", description = "API REST DE GERENCIAMENTO DE TURMA")
@RestController
@RequestMapping("/turma/")
public class TurmaController {

	private final TurmaService turmaService;

	@Autowired
	public TurmaController(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza uma turma por ID")
	public ResponseEntity<Turma> buscaTurmaControlId(@PathVariable Long id) {
		Turma Turma = turmaService.buscaTurmaId(id);
		if (Turma != null) {
			return ResponseEntity.ok(Turma);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
    @GetMapping("/")
	@Operation(summary = "Apresenta todos as turma")
	public ResponseEntity<List<Turma>> buscaTodosTurmaControl() {
		List<Turma> Turma = turmaService.buscaTodosTurma();
		return ResponseEntity.ok(Turma);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra uma aluno")
	public ResponseEntity<Turma> salvaTurmaControl(@RequestBody @Valid Turma turma) {
		Turma salvaTurma = turmaService.salvaTurma(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaTurma);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera uma turma")
	public ResponseEntity<Turma> alterarTurmaControl(@PathVariable Long id, @RequestBody @Valid Turma turma) {
		Turma alterarTurma = turmaService.alterarTurma(id, turma);
		if (alterarTurma != null) {
			return ResponseEntity.ok(turma);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui uma turma")
	public ResponseEntity<Turma>apagaTurmaControl(@PathVariable Long id) {
		boolean apagar = turmaService.apagarTurma(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

