package com.p2.Cursos.cursos.model.repository;

import org.springframework.stereotype.Repository;
import com.p2.Cursos.cursos.model.entities.Prova;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProvaRepository extends JpaRepository<Prova,Long>{

}
