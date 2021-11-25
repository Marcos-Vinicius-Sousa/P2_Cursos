package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.Cursos.exception.AuthorizationException;
import com.p2.Cursos.cursos.model.entities.Fornecedor;
import com.p2.Cursos.cursos.model.repository.FornecedorRepository;
import com.p2.Cursos.security.JWTUtil;

@Service
public class FornecedorService implements ServiceInterface<Fornecedor>{

	@Autowired
	private FornecedorRepository repository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public Fornecedor create(Fornecedor obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Fornecedor findById(Long id) throws AuthorizationException {
		if(!jwtUtil.authorized(id)) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<Fornecedor> _fornecedor = repository.findById(id);
		return _fornecedor.orElse(null);
	}

	@Override
	public List<Fornecedor> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Fornecedor obj) {
		if(repository.existsById(obj.getCd_Fornecedor())) {
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
