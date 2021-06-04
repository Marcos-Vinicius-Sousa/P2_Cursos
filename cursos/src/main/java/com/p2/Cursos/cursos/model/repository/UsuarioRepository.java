package com.p2.Cursos.cursos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.p2.Cursos.cursos.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLogin(String login);

}
