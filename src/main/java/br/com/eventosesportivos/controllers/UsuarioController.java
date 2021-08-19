package br.com.eventosesportivos.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventosesportivos.models.Evento;
import br.com.eventosesportivos.models.Usuario;
import br.com.eventosesportivos.repositories.UsuarioRepository;
import br.com.eventosesportivos.services.UsuarioService;

//@Controller
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
		
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView formLogin() {
		System.out.println("chegou no login no controller get");
		//return "usuario/cadastrarUsuario";
		return new ModelAndView("usuario/loginUsuario");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView formLogin(Usuario u) {
		System.out.println("chegou em efetuarLogin");
		System.out.println(u.getUsu_email());
		System.out.println(u.getUsu_senha());
		return new ModelAndView("evento/listarEventos.html");
		//return "redirect:/evento/listarEventos";
	}
	

	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public ModelAndView formUsuario() {
		System.out.println("chegou no cadastrarusuario no controller get");
		//return "usuario/cadastrarUsuario";
		return new ModelAndView("usuario/cadastrarUsuario");
	}
	
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public ModelAndView formUsuario(Usuario u) throws Exception {
		usuarioService.salvarUsuario(u);
		//return "redirect:/evento/listarEventos";
		//return new ModelAndView("/");
		//LoginController lc = new LoginController();
		//lc.login();
		//return "redirect:/";
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/oiusuario")
	public String oiUsuario() {
		return "oi usuario";
	}
	
	//@GetMapping("/encontrarUsuarioId")
	@GetMapping("/consultarUsuarioId/{id}")
	public Usuario consultarUsuarioId(@PathVariable Long id) {
		Usuario u = new Usuario();
		System.out.println("retorno u");
		System.out.println(id);
		Optional<Usuario> us =  this.usuarioRepository.findById(id);
		if(us.isPresent()) {
			return us.get();
		}else {
			return null;
		}
	}
	
	
	@PostMapping("/criarUsuario")
	public Usuario criarUsuario() throws ParseException {
		//public Usuario criarUsuario(@RequestBody Usuario usu) throws ParseException {
		//listEv.add(e);
		Usuario u = new Usuario();
		u.setUsu_nome_completo("André Araújo");
		u.setUsu_apelido("Andre");
		u.setUsu_email("andre@gmail.com");
		u.setUsu_senha("123456");
		return this.usuarioRepository.save(u);
	}
	/*
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	*/
}
