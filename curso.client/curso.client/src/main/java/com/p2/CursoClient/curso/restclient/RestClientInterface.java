package com.p2.CursoClient.curso.restclient;

import java.util.List;

import javax.ws.rs.core.Response;

public interface RestClientInterface<T> {
	
	public static final int STATUS_OK = Response.Status.OK.getStatusCode();
	public static final int STATUS_UNAUTHORIZED = Response.Status.UNAUTHORIZED.getStatusCode();
	public static final int STATUS_NOT_ACCEPTABLE = Response.Status.NOT_ACCEPTABLE.getStatusCode();

	public static final String REST_WEBSERVICE_URL = 
 	       "https://p2cursos.herokuapp.com/";
	
	public static final String REST_CURSO_URL = "curso/";
	public static final String REST_ALUNO_URL = "aluno/";
	public static final String REST_PROFESSOR_URL = "professor/";
	
	public List<T> findAll();
    public T find(Long id);
    public T create(T obj);
    public T edit(T obj);
    public boolean delete(Long id); 
}
