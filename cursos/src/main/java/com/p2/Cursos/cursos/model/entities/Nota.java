package com.p2.Cursos.cursos.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Nota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private float nota;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public float getNota() {
		return nota;
	}



	public void setNota(float nota) {
		this.nota = nota;
	}



	public Nota() {
		super();
	}
	
	

}
