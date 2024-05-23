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

import com.correcaoEscola.entities.Aluno;
import com.correcaoEscola.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Aluno", description = "API REST DE GERENCIAMENTO DE ALUNO")
@RestController
@RequestMapping("/aluno/")
public class AlunoController {

	private final AlunoService alunoService;

	@Autowired
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza uma aluno por ID")
	public ResponseEntity<Aluno> buscaAlunoControlId(@PathVariable Long id) {
		Aluno Aluno = alunoService.buscaAlunoId(id);
		if (Aluno != null) {
			return ResponseEntity.ok(Aluno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Query
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Aluno>>buscarAlunoPorNome(@PathVariable String nome){
		List<Aluno> aluno = alunoService.findBuscaAlunoPorNome(nome);
		return ResponseEntity.ok(aluno);
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos as aluno")
	public ResponseEntity<List<Aluno>> buscaTodosAlunoControl() {
		List<Aluno> Aluno = alunoService.buscaTodosAluno();
		return ResponseEntity.ok(Aluno);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra uma aluno")
	public ResponseEntity<Aluno> salvaAlunoControl(@RequestBody @Valid Aluno aluno) {
		Aluno salvaAluno = alunoService.salvaAluno(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaAluno);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera uma aluno")
	public ResponseEntity<Aluno> alterarAlunoControl(@PathVariable Long id, @RequestBody @Valid Aluno aluno) {
		Aluno alterarAluno = alunoService.alterarAluno(id, aluno);
		if (alterarAluno != null) {
			return ResponseEntity.ok(aluno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui uma aluno")
	public ResponseEntity<Aluno>apagaAlunoControl(@PathVariable Long id) {
		boolean apagar = alunoService.apagarAluno(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
