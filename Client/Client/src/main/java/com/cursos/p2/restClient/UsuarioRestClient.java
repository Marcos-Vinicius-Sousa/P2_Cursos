package com.cursos.p2.restClient;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cursos.p2.dto.CredenciaisDTO;
import com.cursos.p2.model.Aluno;
import com.cursos.p2.util.SessionContext;





public class UsuarioRestClient {
	
	
	public static final String REST_WEBSERVICE_URL = 
	 	       "https://p2cursos.herokuapp.com/";

	
	private static final String REST_LOGIN_URL = "/login";
	private Response response;
	
	public boolean authenticate(CredenciaisDTO usuario) {		
		this.response = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_LOGIN_URL).
	    		queryParam("usuario", usuario).
	    		request(MediaType.APPLICATION_JSON).
	    		post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			Aluno cliente = this.response.readEntity(Aluno.class);
			System.out.println(cliente);
			SessionContext.getInstance().setAttribute("usuario", cliente);
			String token = response.getHeaderString("Authentication");
			SessionContext.getInstance().setAttribute("token", token);
			return true;
		}	    
		return false;
	}
}
