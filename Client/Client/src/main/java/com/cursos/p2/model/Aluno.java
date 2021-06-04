package com.cursos.p2.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String cidade;
	private List<Curso> cursos;
	private Set<Integer> perfis = new HashSet<Integer>();
	private String login;
	private String senha;
	
	public Aluno() {
		
	}
	
	public boolean isAdmin() {
		return perfis.contains(TipoPerfil.ADMIN.getCod());
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Set<Integer> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Integer> perfis) {
		this.perfis = perfis;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", email=" + email + ",  login=" + login + "]";
	}
	
}
