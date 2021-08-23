package br.com.eventosesportivos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventosesportivos.models.Convite;
import br.com.eventosesportivos.models.Evento;
import br.com.eventosesportivos.models.Usuario;
import br.com.eventosesportivos.models.Vincular;
import br.com.eventosesportivos.repositories.ConviteRepository;
import br.com.eventosesportivos.repositories.EventoRepository;
import br.com.eventosesportivos.repositories.UsuarioRepository;
import br.com.eventosesportivos.repositories.VincularRepository;

@Controller
@RequestMapping("/convite")
public class ConviteController {
		
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ConviteRepository conviteRepository;
	@Autowired
	private VincularRepository vincularRepository;
	
	@GetMapping("/meusConvitesView")
	public ModelAndView meusConvitesView(HttpSession session) {
		//return new ModelAndView("convite/listarConvites");
		ModelAndView mv = new ModelAndView("convite/meusConvitesView");
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");

		List<Convite> convitesDoUsuario = new ArrayList<Convite>();
		List<Convite> con_list = conviteRepository.findAll();
		List<Evento> eventoQueFoiConvidado = new ArrayList<Evento>();
		List<Vincular> vincular_lista_tudo = vincularRepository.findAll();
				
		for(Convite c : con_list) {
			if(u.getUsu_id() == c.getCon_usuario_convidado().getUsu_id()) {
				convitesDoUsuario.add(c);
				
				Evento eve = new Evento();
				Optional<Evento> e = eventoRepository.findById(c.getCon_evento().getEve_id());
				if(e.isPresent()) {
					eve = e.get();
				}
				boolean jatemvinculo = false;
				for (Vincular v : vincular_lista_tudo) {
					
					if(u.getUsu_id() == v.getVin_usuario().getUsu_id() && eve.getEve_id() == v.getVin_evento().getEve_id()){
						jatemvinculo = true;
					}
				}
				if(jatemvinculo == false) {
					eventoQueFoiConvidado.add( eve );
				}
			}
		}
		mv.addObject("eventos",eventoQueFoiConvidado);
		return mv;
	}
	
	@RequestMapping(value="/convidarView", method=RequestMethod.GET)
	public ModelAndView convidarView() {
		ModelAndView mv = new ModelAndView("convite/convidarView");
		List<Evento> evlist = eventoRepository.findAll();
		mv.addObject("eventos",evlist);
		//return "evento/listarEvento";
		return mv;
	}
	
	//@RequestMapping(value="/cadastrarConvite", method=RequestMethod.POST)
	//@PostMapping("/cadastrarConvite")
	//@PostMapping("/cadastrarConvite/{id}")
	@RequestMapping("/cadastrarConviteView")
	public ModelAndView cadastrarConviteView(@RequestParam(value = "eve_id_convite", required = false) String id,Model model, HttpSession session) {
	
	//@RequestMapping(value="/cadastrarConviteView", method=RequestMethod.POST)
	//public ModelAndView cadastrarConviteView(Evento evento, HttpSession session) {
		
		//session.setAttribute("evento_id_cadastrar_convite", evento.getEve_id());
		System.out.println("chegou em cadastrarConviteView");
		System.out.println("evento " +id);
		
		Evento eve = new Evento();
		long id_evento = Long.valueOf(id);
		Optional<Evento> e = eventoRepository.findById(id_evento);
		if(e.isPresent()) {
			eve = e.get();
		}
		
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		List<Usuario> usuList = usuarioRepository.buscarUsuariosNotInId(u.getUsu_id());
		
		List<Convite> con_list = conviteRepository.findAll();
		List<Usuario> usuarioJaConvidados = new ArrayList<Usuario>();
		for(Convite c : con_list) {
			//c.getCon_usuario_convidado().getUsu_id()
			if(c.getCon_evento().getEve_id() == id_evento) {
				usuarioJaConvidados.add(c.getCon_usuario_convidado());
			}
		}
		
		ArrayList<String> a = new ArrayList();
		boolean jafoiconvidado = false;
		for(Usuario uTodos : usuList) {
			for(Usuario ujaconv : usuarioJaConvidados) {
				if ( uTodos.getUsu_id() == ujaconv.getUsu_id() ) {
					jafoiconvidado = true;
				}
			}
			if(jafoiconvidado) {
				usuList.remove(uTodos);
			}
		}
		
		ModelAndView mv = new ModelAndView("convite/cadastrarConviteView");
		mv.addObject("evento",eve);
		mv.addObject("usuarios",usuList);
		return mv;
		
	}
	
//	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
//	public ModelAndView formUsuario(Usuario u) throws Exception {
//		usuarioService.salvarUsuario(u);
//		//return "redirect:/evento/listarEventos";
//		//return new ModelAndView("/");
//		//LoginController lc = new LoginController();
//		//lc.login();
//		//return "redirect:/";
//		return new ModelAndView("redirect:/");
//	}
	
	@RequestMapping(value="/registrarConvite", method=RequestMethod.POST)
	public ModelAndView cadastrarConvite(Usuario usu, HttpSession session) {
		Convite c = new Convite();
		System.out.println("convite registrarConvite");
		
		Usuario convidante = (Usuario) session.getAttribute("usuarioLogado");
		System.out.println(convidante.getUsu_email());
		
		long id_evento = (long) session.getAttribute("evento_id_cadastrar_convite");
		Evento eve = new Evento();
		Optional<Evento> e = eventoRepository.findById(id_evento);
		if(e.isPresent()) {
			eve = e.get();
		}
		System.out.println(eve.getEve_nome_desricao());
		System.out.println("convidado"+usu.getUsu_id());
		
		Usuario convidado = new Usuario();
		Optional<Usuario> con = usuarioRepository.findById(usu.getUsu_id());
		if(con.isPresent()) {
			convidado = con.get();
		}
		
		//String url = "redirect:/evento/listarEventos/";
		String url = "redirect:/convite/cadastrarConviteView/";
		url = url + eve.getEve_id();
		
		String id = "";
		id = id+eve.getEve_id();
		
		//cadastrarConviteView(id, null, session);
		//return new ModelAndView(url);
		
		
		c.setCon_evento(eve);
		c.setCon_usuario_convidado(convidado);
		c.setCon_usuario_convidante(convidante);
	
		this.conviteRepository.save(c);
		//this.eventoRepository.save(e);
		ModelAndView mv = new ModelAndView("convite/convidarView");
		return mv;
	}
	
//	@RequestMapping(value="/listarEventos", method=RequestMethod.GET)
//	public ModelAndView listarEventos() {
//		ModelAndView mv = new ModelAndView("evento/listarEventos");
//		List<Evento> evlist = eventoRepository.findAll();
//		mv.addObject("eventos",evlist);
//		
//		//return "evento/listarEvento";
//		return mv;
//	}
}
