package com.p2.Cursos.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.p2.Cursos.cursos.model.entities.Usuario;
import com.p2.Cursos.cursos.model.repository.UsuarioRepository;
import com.p2.Cursos.security.UserDetailsImpl;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findByLogin(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return new UserDetailsImpl(usuario.getId(),usuario.getLogin(), usuario.getSenha(), usuario.getPerfis());
	}

}
