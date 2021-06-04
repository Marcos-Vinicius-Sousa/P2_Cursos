package com.cursos.p2.restClient;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cursos.p2.model.Curso;
import com.cursos.p2.util.SessionContext;



public class CursoRestClient implements RestClientInterface<Curso>{

	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");
	
	@Override
	public List<Curso> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_CURSO_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Curso> cursos = this.response
				.readEntity(new GenericType<List<Curso>>() {});
		return cursos;
	}

	@Override
	public Curso find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CURSO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Curso cursos = this.response.readEntity(Curso.class);
			return cursos;
		}
		return null;
	}

	@Override
	public Curso create(Curso obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CURSO_URL)
				.queryParam("categoria", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Curso curso = this.response.readEntity(Curso.class);

		return curso;
	}

	@Override
	public Curso edit(Curso obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CURSO_URL)
				.queryParam("categoria", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Curso curso = this.response.readEntity(Curso.class);
			return curso;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CURSO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
