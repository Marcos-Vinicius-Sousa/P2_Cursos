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
import com.p2.Cursos.cursos.model.entities.Professor;
import com.p2.Cursos.cursos.service.ProfessorService;
import com.p2.Cursos.exception.AuthorizationException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/professor")
public class ProfessorController implements ControllerInterfaces<Professor>{

	@Autowired
	private ProfessorService service;
	
	
	@Override
	@GetMapping(produces ="application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna uma lista de professores"),
			@ApiResponse(responseCode = "403",
			description = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(responseCode = "500",
			description = "Foi gerada uma exceção"),
			})
	@Operation(summary = "Devolve uma lista de professores")
	public ResponseEntity<List<Professor>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value="/{id}",produces ="application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna um professor de acordo com seu id"),
			@ApiResponse(responseCode = "403",
			description = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(responseCode = "404",
			description = "Não existe nenhum professor com esse id"),
			})
	@Operation(summary = "Devolve um professor dado o seu id")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		try {
		Professor _professor = service.findById(id);
		if(_professor != null) {
			return ResponseEntity.ok(_professor);
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch (AuthorizationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@Override
	@PostMapping(produces ="application/json")
	@Operation(summary = "Grava um novo professor")
	public ResponseEntity<Professor> post(@RequestBody Professor obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping(produces ="application/json")
	@Operation(summary = "Atualiza um professor")
	public ResponseEntity<?> put( @RequestBody Professor obj) {
		if(service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value="/{id}")
	@Operation(summary = "Exclui um professor")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
