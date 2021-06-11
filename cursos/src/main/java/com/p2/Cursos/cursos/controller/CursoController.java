package com.p2.Cursos.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.p2.Cursos.cursos.model.entities.Curso;
import com.p2.Cursos.cursos.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin()
@RequestMapping("/curso")
public class CursoController implements ControllerInterfaces<Curso>{

	@Autowired
	private CursoService service;

	@Override
	@GetMapping(produces ="application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna uma lista de cursos"),
			@ApiResponse(responseCode = "403",
			description = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(responseCode = "500",
			description = "Foi gerada uma exceção"),
			})
	@Operation(summary = "Devolve uma lista de cursos")
	public ResponseEntity<List<Curso>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value="/{id}",produces ="application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna um curso de acordo com seu id"),
			@ApiResponse(responseCode = "403",
			description = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(responseCode = "404",
			description = "Não existe nenhum curso com esse id"),
			})
	@Operation(summary = "Devolve o curso dado seu id")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Curso _curso = service.findById(id);
		if(_curso != null) {
			return ResponseEntity.ok(_curso);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping(produces ="application/json")
	@Operation(summary = "Grava uma novo curso")
	public ResponseEntity<Curso> post(@RequestBody Curso obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping(produces ="application/json")
	@Operation(summary = "Atualiza um curso")
	public ResponseEntity<?> put(@RequestBody Curso obj) {
		if(service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value="/{id}")
	@Operation(summary = "Exclui um curso")
	public ResponseEntity<?> delete( @PathVariable("id") Long id) {
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	

	
	

}
