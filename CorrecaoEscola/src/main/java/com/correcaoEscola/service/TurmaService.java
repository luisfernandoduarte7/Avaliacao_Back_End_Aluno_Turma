package com.correcaoEscola.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.correcaoEscola.entities.Turma;
import com.correcaoEscola.repository.TurmaRepository;

@Service
public class TurmaService {

	private final TurmaRepository turmaRepository;
	@Autowired

	public TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}
    
	public  List<Turma> buscaTodosTurma(){
		return turmaRepository.findAll();
	}
	public Turma buscaTurmaId(Long id) {
		Optional <Turma> Turma = turmaRepository.findById(id);
		return Turma.orElse(null);
	}
	public Turma salvaTurma(Turma Turma){
		return turmaRepository.save(Turma);
	}
	public Turma alterarTurma(Long id, Turma alterarTurma) {
		Optional <Turma> existeTurma = turmaRepository.findById(id);
		if (existeTurma.isPresent()) {
			alterarTurma.setId(id);
			return turmaRepository.save(alterarTurma);
		}
		return null;
	}
	public boolean apagarTurma (Long id) {
		Optional <Turma> existeTurma = turmaRepository.findById(id);
		if (existeTurma.isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}


