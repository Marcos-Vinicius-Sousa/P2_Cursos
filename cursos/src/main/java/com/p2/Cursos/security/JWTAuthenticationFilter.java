package com.p2.Cursos.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.Cursos.dto.CredenciaisDTO;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;	
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getLogin(),
					creds.getSenha(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Authentication", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		
	}
	

	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws java.io.IOException, javax.servlet.ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}
	
	
	private String json() {
		return "{\"timestamp\": " + new Date().getTime() + ", " 
				+ "\"status\": 401, "
				+ "\"error\": \"Não autorizado\", " 
				+ "\"message\": \"Email ou senha inválidos\", "
				+ "\"path\": \"/login\"}";
	}
	


	

}
