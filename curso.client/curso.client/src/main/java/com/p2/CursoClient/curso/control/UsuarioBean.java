package com.p2.CursoClient.curso.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import com.p2.CursoClient.curso.restclient.UsuarioRestClient;
import com.p2.CursoClient.curso.util.SessionContext;
import com.p2.CursoCliente.curso.dto.CredenciaisDTO;



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
