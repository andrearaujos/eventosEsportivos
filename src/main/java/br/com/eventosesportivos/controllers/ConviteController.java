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
import br.com.eventosesportivos.repositories.ConviteRepository;
import br.com.eventosesportivos.repositories.EventoRepository;
import br.com.eventosesportivos.repositories.UsuarioRepository;

@Controller
@RequestMapping("/convite")
public class ConviteController {
		
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ConviteRepository conviteRepository;
	
	@GetMapping("/meusConvites")
	public ModelAndView meusConvites(HttpSession session) {
		//return new ModelAndView("convite/listarConvites");
		ModelAndView mv = new ModelAndView("convite/listarConvites");
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		System.out.println(u.getUsu_nome_completo());
		
		List<Convite> convitesDoUsuario = conviteRepository.convitesPorIdDeConvidado(u.getUsu_id());
//		System.out.println(convitesDoUsuario);
		
		return mv;
	}
	
	//@RequestMapping(value="/cadastrarConvite", method=RequestMethod.POST)
	//@PostMapping("/cadastrarConvite")
	//@PostMapping("/cadastrarConvite/{id}")
	@RequestMapping("/cadastrarConvite")
	public ModelAndView cadastrarConvite(@RequestParam(value = "eve_id_convite", required = false) String id,Model model, HttpSession session) {
		
		ModelAndView mv = new ModelAndView("convite/cadastrarConvites");
		Evento eve = new Evento();
		long id_evento = Long.valueOf(id);
		Optional<Evento> e = eventoRepository.findById(id_evento);
		if(e.isPresent()) {
			eve = e.get();
		}
		mv.addObject("evento",eve);
		List<Usuario> usuList = usuarioRepository.findAll();
		mv.addObject("usuarios",usuList);
		System.out.println("id_evento ========= "+id_evento);
		System.out.println("Numero de usuario cadastrador: " + usuList.size());
		
//		List<Convite> possiveisConvidadosList = new ArrayList<Convite>();
//		for(Usuario us : usuList) {
//			Convite con = new Convite();
//			con.setCon_evento(eve);
//			con.setCon_usuario_convidado(us);
//		}
//		mv.addObject("possiveisConvidados",possiveisConvidadosList);
		
		session.setAttribute("eventoCadastrarConvite", id_evento);
		
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
