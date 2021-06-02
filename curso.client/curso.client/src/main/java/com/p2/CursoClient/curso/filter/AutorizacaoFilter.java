package com.p2.CursoClient.curso.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.p2.CursoClient.curso.model.Aluno;


@WebFilter(urlPatterns = "/faces/protected/*", dispatcherTypes = {DispatcherType.REQUEST})
public class AutorizacaoFilter implements Filter {

	
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Aluno user = (Aluno) sessao.getAttribute("usuario");
        if (user != null) {
            chain.doFilter(request, response);
        }
        else {
            sessao.setAttribute("message", "FaÃ§a o login");
            String contextPath = ((HttpServletRequest) request).
                                                    getContextPath();
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/faces/index.xhtml");
        }
	}

	
	public void destroy() {
	}

}
