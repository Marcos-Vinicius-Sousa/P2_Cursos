package com.p2.CursoClient.curso.control;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.p2.CursoClient.curso.model.Curso;
import com.p2.CursoClient.curso.restclient.CursoRestClient;




@Controller
public class CursoBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Curso curso;
	private List<Curso> cursos;
	CursoRestClient rest = new CursoRestClient();
	
	public CursoBean()  {		
		CursoRestClient rest = new CursoRestClient();
		cursos = rest.findAll();
	}

	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }
	
	@RequestMapping(value="/sobre", method=RequestMethod.GET)
    public ModelAndView sobre(){
        ModelAndView mv = new ModelAndView("sobre");
        return mv;
    }
	
	@RequestMapping(value="/listacursos", method=RequestMethod.GET)
    public ModelAndView getAllCursos(){
        ModelAndView mv = new ModelAndView("listacursos");
        List<Curso> cursos= rest.findAll();
        mv.addObject("cursos", cursos);
        return mv;
    }
	
	@RequestMapping(value="/curso", method=RequestMethod.GET)
    public String getCursoForm(Model model) {
		model.addAttribute("curso", new Curso());
        return "cadastrarcurso";
    }
	
	@PostMapping(value="/curso")
	public String curso(Curso curso, Model model) {
	 model.addAttribute("curso", curso);	
	 rest.create(curso);
		return "redirect:/listacursos";
	}
	
	@GetMapping(value="/curso/delete/{id}")
	 public String deleteCurso(@PathVariable Long id) {
	     rest.delete(id);
	     return "redirect:/listacursos";
	 }
	
	
	@RequestMapping(value="/edita/{id}", method=RequestMethod.GET)
	 public ModelAndView putCurso(@PathVariable Long id ) {
		    ModelAndView mv = new ModelAndView("updatecurso");
		    Curso update = rest.find(id);
		    mv.addObject("update", update);
		    return mv;
		}
	
	
	@PostMapping(value="/edita/{id}")
	 public String putCurso(Curso curso, Model model) {
		 model.addAttribute("curso", curso);	
		 rest.edit(curso);
		 return "redirect:/listacursos";
	}
		
	
	
	public String gravar() {
		CursoRestClient rest = new CursoRestClient();
		if (curso.getId() == null) {
			rest.create(curso);
			curso = new Curso();			
		}
		else {
			curso = rest.edit(curso);
		}
		return null;		
	}
	
	public String excluir(Curso c) {
		CursoRestClient rest = new CursoRestClient();
		if (!rest.delete(c.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir o curso " + c.getNome());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			cursos.remove(c);
		}
		return null;
	}
	
	
}
