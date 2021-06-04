package com.cursos.p2.restClient;

import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cursos.p2.model.Professor;
import com.cursos.p2.util.SessionContext;



public class ProfessorRestClient implements RestClientInterface<Professor> {
	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");
	
	
	public List<Professor> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_PROFESSOR_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Professor> professores = this.response
				.readEntity(new GenericType<List<Professor>>() {});
		return professores;
	}

	
	public Professor find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PROFESSOR_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Professor professores = this.response.readEntity(Professor.class);
			return professores;
		}
		return null;
	}


	
	public Professor create(Professor obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PROFESSOR_URL)
				.queryParam("Professor", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Professor professores = this.response.readEntity(Professor.class);

		return professores;
	}

	
	public Professor edit(Professor obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PROFESSOR_URL)
				.queryParam("Professor", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Professor professores = this.response.readEntity(Professor.class);
			return professores;
		}
		return null;
	}

	
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PROFESSOR_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}
}
