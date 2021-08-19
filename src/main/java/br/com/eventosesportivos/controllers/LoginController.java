package br.com.eventosesportivos.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventosesportivos.models.Usuario;
import br.com.eventosesportivos.repositories.UsuarioRepository;
import br.com.eventosesportivos.services.UsuarioService;
import br.com.eventosesportivos.util.Util;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login/login");
		//mv.addObject("usuario", new Usuario());
		//mv.addObject("msg","Teste");
		return mv;
	}
	
	@PostMapping("executaLogin")
	public ModelAndView executaLogin(Usuario u, BindingResult br, HttpSession session) throws NoSuchAlgorithmException{
		//ModelAndView mv = new ModelAndView("login/login");
		
		System.out.println(u.getUsu_email());
		System.out.println(u.getUsu_senha());
		
		
		ModelAndView mv = new ModelAndView("redirect:/");
		mv.addObject("usuario",new Usuario());
		if(br.hasErrors()) {
			//mv.setViewName("login/login");
			mv.setViewName("/");
		}
		
		//Usuario usuario = usuarioService.loginUsuario(u.getUsu_email(), Util.md5(u.getUsu_senha()));
		System.out.println("debug");
		Usuario usuario = usuarioService.loginUsuario(u.getUsu_email(), u.getUsu_senha());
		if(usuario == null) {
			mv.addObject("msg","Usuário não encontrado");
			//mv.setViewName("/");
			//return mv; 
			
		}else {
			session.setAttribute("usuarioLogado", usuario);
			return new ModelAndView("redirect:/evento/listarEventos");
		}
		
		return mv;
		
		//return new ModelAndView("redirect:/evento/listarEventos");
		
		//System.out.println("executaLogin===========================");
		//mv.addObject("msg","Teste");
		//return mv;
		//return new ModelAndView("redirect:/");
	}
	
//	@GetMapping("/")
//	public ModelAndView index() {
//		ModelAndView mv = new ModelAndView("home/index");
//		mv.addObject("msg","Teste");
//		return mv;
//	}
	
//	@GetMapping("/")
//	public ModelAndView index() {
//		ModelAndView mv = new ModelAndView("");
//		mv.setViewName("");
//		//return mv;
//		return new ModelAndView("login/loginUsu");
//	}
	
	
}
