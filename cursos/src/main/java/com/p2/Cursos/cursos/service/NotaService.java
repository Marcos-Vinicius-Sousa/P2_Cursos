package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.p2.Cursos.cursos.model.entities.Nota;
import com.p2.Cursos.cursos.model.repository.NotaRepository;

@Service
public class NotaService implements ServiceInterface<Nota>{

	@Autowired
	private NotaRepository repository;
	
	@Override
	public Nota create(Nota obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Nota findById(Long id) {
		Optional<Nota> _curso = repository.findById(id);
		return _curso.orElse(null);
	}

	@Override
	public List<Nota> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Nota obj) {
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
