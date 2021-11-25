package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.Cursos.exception.AuthorizationException;
import com.p2.Cursos.cursos.model.entities.MateriaPrima;
import com.p2.Cursos.cursos.model.repository.MateriaPrimaRepository;
import com.p2.Cursos.security.JWTUtil;

@Service
public class MateriaPrimaService implements ServiceInterface<MateriaPrima>{

	
	@Autowired
	private MateriaPrimaRepository repository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public MateriaPrima create(MateriaPrima obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public MateriaPrima findById(Long id) throws AuthorizationException {
		if(!jwtUtil.authorized(id)) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<MateriaPrima> _materia = repository.findById(id);
		return _materia.orElse(null);
	}

	@Override
	public List<MateriaPrima> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(MateriaPrima obj) {
		if(repository.existsById(obj.getCd_MateriaPrima())) {
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
