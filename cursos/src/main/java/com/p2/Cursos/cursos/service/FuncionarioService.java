package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2.Cursos.exception.AuthorizationException;
import com.p2.Cursos.cursos.model.entities.Funcionario;
import com.p2.Cursos.cursos.model.repository.FuncionarioRepository;
import com.p2.Cursos.security.JWTUtil;

@Service
public class FuncionarioService implements ServiceInterface<Funcionario>{

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private JWTUtil jwtUtil;
	

	@Override
	public Funcionario create(Funcionario obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Funcionario findById(Long id) throws AuthorizationException {
		if(!jwtUtil.authorized(id)) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<Funcionario> _funcionario = repository.findById(id);
		return _funcionario.orElse(null);
	}

	@Override
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Funcionario obj) {
		if(repository.existsById(obj.getCd_Funcionario())) {
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
