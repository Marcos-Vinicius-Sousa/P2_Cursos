package com.cursos.p2.restClient;

import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cursos.p2.model.Aluno;
import com.cursos.p2.util.SessionContext;



public class AlunoRestClient implements RestClientInterface<Aluno> {
	
	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");
	
	
	public List<Aluno> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_ALUNO_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Aluno> Alunos = this.response
				.readEntity(new GenericType<List<Aluno>>() {});
		return Alunos;
	}

	
	public Aluno find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_ALUNO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Aluno Aluno = this.response.readEntity(Aluno.class);
			return Aluno;
		}
		return null;
	}


	
	public Aluno create(Aluno obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_ALUNO_URL)
				.queryParam("Aluno", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Aluno Aluno = this.response.readEntity(Aluno.class);

		return Aluno;
	}

	
	public Aluno edit(Aluno obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_ALUNO_URL)
				.queryParam("Aluno", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Aluno Aluno = this.response.readEntity(Aluno.class);
			return Aluno;
		}
		return null;
	}

	
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_ALUNO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
