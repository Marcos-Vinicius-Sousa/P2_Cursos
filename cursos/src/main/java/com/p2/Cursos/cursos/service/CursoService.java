package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.p2.Cursos.cursos.model.entities.Curso;
import com.p2.Cursos.cursos.model.repository.CursoRepository;


@Service
public class CursoService implements ServiceInterface<Curso>{

	@Autowired
	private CursoRepository repository;
	
	
	@Override
	public Curso create(Curso obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Curso findById(Long id) {
		Optional<Curso> _curso = repository.findById(id);
		return _curso.orElse(null);
	}

	@Override
	public List<Curso> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Curso obj) {
		if(repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
			return false;
	}

}
