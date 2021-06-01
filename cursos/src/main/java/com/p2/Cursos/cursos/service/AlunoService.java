package com.p2.Cursos.cursos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.p2.Cursos.cursos.model.entities.Aluno;
import com.p2.Cursos.cursos.model.entities.Curso;
import com.p2.Cursos.cursos.model.repository.AlunoRepository;
import com.p2.Cursos.cursos.model.repository.CursoRepository;
import com.p2.Cursos.security.JWTUtil;
import com.p2.Cursos.exception.AuthorizationException;


@Service
public class AlunoService  implements ServiceInterface<Aluno>{

	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	private JWTUtil jwtUtil;
	
	
	
	@Autowired
	private CursoRepository repoCurso;
	
	
	
	@Override
	public Aluno create(Aluno obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		repository.save(obj);
		return obj;
	}

	@Override
	public Aluno findById(Long id) throws AuthorizationException{
		if (!jwtUtil.authorized(id)) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<Aluno> _aluno = repository.findById(id);
		return _aluno.orElse(null);
	}

	@Override
	public List<Aluno> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Aluno obj) {
		if(repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}

	
	public boolean delete(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
			return false;
	}
	
	public Aluno cadastroAlunoCurso( Long idAluno , Long idCurso) {
		
		Optional<Aluno> alunoExistente = repository.findById(idAluno);
		Optional<Curso> cursoExistente = repoCurso.findById(idCurso);
		
		if(alunoExistente.isPresent() && cursoExistente.isPresent()) {
			alunoExistente.get().getCursos().add(cursoExistente.get());
			
			repository.save(alunoExistente.get());
			
			return repository.save(alunoExistente.get());
		}
		
		return null;
	}

}
