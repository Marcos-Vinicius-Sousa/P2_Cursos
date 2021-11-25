package com.p2.Cursos.cursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2.Cursos.cursos.model.entities.MateriaPrima;

@Repository
public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Long>{

}
