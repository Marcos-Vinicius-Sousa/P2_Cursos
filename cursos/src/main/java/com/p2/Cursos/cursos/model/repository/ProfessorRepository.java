package com.p2.Cursos.cursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2.Cursos.cursos.model.entities.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
