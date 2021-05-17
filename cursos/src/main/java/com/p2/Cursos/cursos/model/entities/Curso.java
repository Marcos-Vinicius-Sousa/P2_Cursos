package com.p2.Cursos.cursos.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Curso")
	private Long id;
	
	
	private String nome;
	private String area;
	private String aulas;
	


	public Curso() {
		super();
		
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
