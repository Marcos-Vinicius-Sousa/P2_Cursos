package com.p2.Cursos.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.Cursos.cursos.model.entities.Professor;
import com.p2.Cursos.cursos.model.repository.ProfessorRepository;
import java.util.Optional;

@Service
public class ProfessorService implements ServiceInterface<Professor>{

	@Autowired
	private ProfessorRepository repository;
	
	@Override
	public Professor create(Professor obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Professor findById(Long id) {
		Optional<Professor> _professor = repository.findById(id);
		return _professor.orElse(null);
	}

	@Override
	public List<Professor> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Professor obj) {
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
