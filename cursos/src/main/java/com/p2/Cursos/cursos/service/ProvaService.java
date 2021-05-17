package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.p2.Cursos.cursos.model.entities.Prova;
import com.p2.Cursos.cursos.model.repository.ProvaRepository;

@Service
public class ProvaService implements ServiceInterface<Prova>{

	@Autowired
	private ProvaRepository repository;
	
	
	@Override
	public Prova create(Prova obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Prova findById(Long id) {
		Optional<Prova> _curso = repository.findById(id);
		return _curso.orElse(null);
	}

	@Override
	public List<Prova> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Prova obj) {
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
