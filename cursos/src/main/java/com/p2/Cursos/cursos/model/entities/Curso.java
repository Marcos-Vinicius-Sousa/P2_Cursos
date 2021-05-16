package com.p2.Cursos.cursos.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="nm_curso")
	private String nome;
	
	
	@Column(name="nm_area")
	private String area;
	
	
	@Column(name="qtd_aulas")
	private String aulas;
	
	public Curso() {
		
	}

	public Curso( String nome,  String area,  String aulas) {
		super();
		this.nome = nome;
		this.area = area;
		this.aulas = aulas;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAulas() {
		return aulas;
	}

	public void setAulas(String aulas) {
		this.aulas = aulas;
	}
	
	
	
}
