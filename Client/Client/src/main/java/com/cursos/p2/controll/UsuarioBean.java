package com.cursos.p2.controll;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.cursos.p2.dto.CredenciaisDTO;
import com.cursos.p2.restClient.UsuarioRestClient;
import com.cursos.p2.util.SessionContext;




@ManagedBean
@RequestScoped
public class UsuarioBean {
	
	private CredenciaisDTO usuario = new CredenciaisDTO();
	
	public UsuarioBean() {
	}
	
	public void setUsuario(CredenciaisDTO usuario) {
		this.usuario = usuario;
	}
	
	public String autenticar() {
		UsuarioRestClient client = new UsuarioRestClient();
		if (client.authenticate(usuario)) {
			return "/protected/categoria?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Login/senha invÃ¡lidos");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
			
	public String logout() {
		SessionContext.getInstance().encerrarSessao();
		return "/index?faces-redirect=true";
	}

}
