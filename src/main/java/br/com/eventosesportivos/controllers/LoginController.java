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
	public ModelAndView loginView() {
		ModelAndView mv = new ModelAndView("login/loginView");
		//mv.addObject("usuario", new Usuario());
		//mv.addObject("msg","Teste");
		return mv;
	}
	
	@PostMapping("executaLogin")
	public ModelAndView executaLogin(Usuario u, BindingResult br, HttpSession session) throws NoSuchAlgorithmException{

		ModelAndView mv = new ModelAndView("redirect:/");
		mv.addObject("usuario",new Usuario());
		if(br.hasErrors()) {
			mv.setViewName("/");
		}
		
		//Usuario usuario = usuarioService.loginUsuario(u.getUsu_email(), Util.md5(u.getUsu_senha()));
		System.out.println("debug");
		Usuario usuario = usuarioService.loginUsuario(u.getUsu_email(), u.getUsu_senha());
		if(usuario == null) {
			mv.addObject("msg","Usuário não encontrado");			
		}else {
			session.setAttribute("usuarioLogado", usuario);
			return new ModelAndView("redirect:/evento/listarEventos");
		}
		
		return mv;
	}
	
	@GetMapping("/sair")
	public ModelAndView sair(HttpSession hs) {
		hs.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
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
