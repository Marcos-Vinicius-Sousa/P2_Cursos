package com.p2.Cursos.cursos.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Professor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name="nm_professor")
	private String nome;
	
	
	@CPF
	@NotBlank
	private String cpf;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getCurso() {
		return curso.getId();
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Professor() {
		
	}
	
	public Professor(@NotBlank String nome, @NotBlank String cpf, @NotBlank @Email String email,
			@NotBlank String telefone, Curso curso) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.curso = curso;
	}
	
	
}
