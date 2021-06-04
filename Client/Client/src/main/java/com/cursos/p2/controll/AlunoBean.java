package com.cursos.p2.controll;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.cursos.p2.model.Aluno;
import com.cursos.p2.restClient.AlunoRestClient;



@ManagedBean
@SessionScoped
public class AlunoBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Aluno aluno;
	private List<Aluno> alunos;
	
	public AlunoBean()  {		
		AlunoRestClient rest = new AlunoRestClient();
		alunos = rest.findAll();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}		
	
	

	public String pagPrincipal() {
		AlunoRestClient rest = new AlunoRestClient();
		alunos = rest.findAll();
		return "/protected/aluno?faces-redirect=true";		
	}
	
	public String pagCategoria() {
		this.aluno = new Aluno();
		return "/protected/pag_categoria?faces-redirect=true";
	}
	
	public String pagCategoria(Aluno aluno) {
		this.aluno = aluno;
		return "/protected/pag_categoria?faces-redirect=true";
	}
	
	
	public String gravar() {
		AlunoRestClient rest = new AlunoRestClient();
		if (aluno.getId() == null) {
			rest.create(aluno);
			aluno = new Aluno();			
		}
		else {
			aluno = rest.edit(aluno);
		}
		return null;		
	}
	
	public String excluir(Aluno a) {
		AlunoRestClient rest = new AlunoRestClient();
		if (!rest.delete(a.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir a categoria " + a.getNome());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			alunos.remove(a);
		}
		return null;
	}
	
	

}
