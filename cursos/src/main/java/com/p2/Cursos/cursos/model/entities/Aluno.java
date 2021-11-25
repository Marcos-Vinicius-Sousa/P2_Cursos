package com.p2.Cursos.cursos.model.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Aluno")
	private Long id;
	
	@NotBlank
	@Column(name="nm_aluno")
	private String nome;
	
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String genero;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String telefone;
	
	@ManyToMany
	private List<Curso> cursos;

	
	
	public List<Curso> getCursos() {
		return cursos;
	}

	
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Aluno() {
		super();
		
	}

	
}
