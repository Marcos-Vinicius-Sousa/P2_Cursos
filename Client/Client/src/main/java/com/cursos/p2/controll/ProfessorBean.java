package com.cursos.p2.controll;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.cursos.p2.model.Professor;
import com.cursos.p2.restClient.ProfessorRestClient;



@ManagedBean
@SessionScoped
public class ProfessorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Professor professor;
	private List<Professor> professores;
	
	public ProfessorBean()  {		
		ProfessorRestClient rest = new ProfessorRestClient();
		professores = rest.findAll();
	}

	public Professor getProfessor() {
		return professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}		
	

	public String pagPrincipal() {
		ProfessorRestClient rest = new ProfessorRestClient();
		professores = rest.findAll();
		return "/protected/professor?faces-redirect=true";		
	}
	
	public String pagProfessor() {
		this.professor = new Professor();
		return "/protected/pag_professor?faces-redirect=true";
	}
	
	public String pagProfessor(Professor professor) {
		this.professor = professor;
		return "/protected/pag_professor?faces-redirect=true";
	}
	
	
	public String gravar() {
		ProfessorRestClient rest = new ProfessorRestClient();
		if (professor.getId() == null) {
			rest.create(professor);
			professor = new Professor();			
		}
		else {
			professor = rest.edit(professor);
		}
		return null;		
	}
	
	public String excluir(Professor p) {
		ProfessorRestClient rest = new ProfessorRestClient();
		if (!rest.delete(p.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("NÃ£o foi possÃ­vel excluir a categoria " + p.getNome());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			professores.remove(p);
		}
		return null;
	}
}
