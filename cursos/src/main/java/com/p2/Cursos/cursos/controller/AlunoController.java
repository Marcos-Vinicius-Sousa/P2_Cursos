package com.p2.Cursos.cursos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.p2.Cursos.cursos.model.entities.Aluno;
import com.p2.Cursos.cursos.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController implements ControllerInterfaces<Aluno>{

	@Autowired
	private AlunoService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Aluno>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value="/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Aluno _aluno = service.findById(id);
		if(_aluno != null) {
			return ResponseEntity.ok(_aluno);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	public ResponseEntity<Aluno> post(@RequestBody Aluno obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}
	
	/*@PostMapping("/AlunoCurso/cursos/{idAluno}/alunos/{idCurso}")
	public ResponseEntity<Aluno> postAlunoCurso(@PathVariable long idAluno, @PathVariable long idCurso){
		service.cadastroAlunoCurso(idAluno, idCurso);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		//return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroAlunoCurso(idAluno, idCurso));
	} */

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Aluno obj) {
		if(service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
