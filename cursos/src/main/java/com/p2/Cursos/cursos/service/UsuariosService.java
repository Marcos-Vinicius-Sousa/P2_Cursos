package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.p2.Cursos.cursos.model.entities.Usuario;
import com.p2.Cursos.cursos.model.repository.UsuarioRepository;
import com.p2.Cursos.exception.AuthorizationException;
import com.p2.Cursos.security.JWTUtil;

@Service
public class UsuariosService implements ServiceInterface<Usuario>{
	
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public Usuario create(Usuario obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		repository.save(obj);
		return obj;
	}

	@Override
	public Usuario findById(Long id) {
		if (!jwtUtil.authorized(id)) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Usuario obj) {
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
