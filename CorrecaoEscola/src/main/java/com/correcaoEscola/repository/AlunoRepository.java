package com.correcaoEscola.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.correcaoEscola.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
	
	@Query("SELECT a FROM Aluno a WHERE a.nome = :nome")
    List<Aluno> findByNome(@Param("nome") String nome);

}
