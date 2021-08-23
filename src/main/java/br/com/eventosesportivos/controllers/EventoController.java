package br.com.eventosesportivos.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventosesportivos.models.Evento;
import br.com.eventosesportivos.models.Usuario;
import br.com.eventosesportivos.models.Vincular;
import br.com.eventosesportivos.repositories.EventoRepository;
import br.com.eventosesportivos.repositories.UsuarioRepository;
import br.com.eventosesportivos.repositories.VincularRepository;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	private ArrayList<Evento> listEv = new ArrayList<>();
	
	//injetando o repository dentro do controller
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private VincularRepository vincularRepository;
	
	@RequestMapping(value="/listarEventos", method=RequestMethod.GET)
	public ModelAndView listarEventos() {
		ModelAndView mv = new ModelAndView("evento/listarEventos");
		List<Evento> evlist = eventoRepository.findAll();
		mv.addObject("eventos",evlist);
		//return "evento/listarEvento";
		return mv;
	}
	
	@RequestMapping(value="/cadastrarEventoView", method=RequestMethod.GET)
	public String cadastrarEventoView() {
		return "evento/cadastrarEventoView";
		//return new ModelAndView("evento/formEvento");
	}
	
	@RequestMapping(value="/meusEventosView", method=RequestMethod.GET)
	public ModelAndView meusEventosView(HttpSession session) {
		ModelAndView mv = new ModelAndView("evento/meusEventosView");
		System.out.println("chegou aqui");
		
		Usuario ulogado = (Usuario) session.getAttribute("usuarioLogado");
		System.out.println(ulogado.getUsu_id());
		long id = Long.valueOf(ulogado.getUsu_id());
		List<Vincular> v_list_all = vincularRepository.findAll();
		List<Evento> e_list = new ArrayList();
		
		for(Vincular vin : v_list_all) {
			
			System.out.println("vin.getVin_usuario().getUsu_id() "+vin.getVin_usuario().getUsu_id() );
			System.out.println("ulogado.getUsu_id() "+ulogado.getUsu_id());
			if(vin.getVin_usuario().getUsu_id() == ulogado.getUsu_id()) {
				System.out.println("chegou aqui 2");
				System.out.println("vin.getVin_evento().getEve_id()"+ vin.getVin_evento().getEve_id());
				Optional<Evento> op_ev = eventoRepository.findById(vin.getVin_evento().getEve_id());
				
				System.out.println(op_ev.get().getEve_nome_desricao());
				//Evento evento = new Evento();
				if(op_ev.isPresent()) {
					e_list.add(op_ev.get()); //evento = op_ev.get();
				}
				//System.out.println("evento.getEve_nome_desricao() ===="+ evento.getEve_nome_desricao());
				//e_list.add(evento);
			}
		}
		
//		
//		List<Evento> ev_list = null;
//		for(Vincular v : v_list) {
//			Optional<Evento> op_ev = eventoRepository.findById(v.getVin_evento().getEve_id());
//			Evento ev = new Evento();
//			if(op_ev.isPresent()) {
//				ev = op_ev.get();
//			}
//			ev_list.add(ev);
//		}
//		
//		 //= eventoRepository.findAll();
		mv.addObject("eventos",e_list);
		return mv;
		//return "evento/meusEventosView";
		//return new ModelAndView("evento/formEvento");
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String formEvento(Evento e, HttpSession session) {
		Usuario ulogado = (Usuario) session.getAttribute("usuarioLogado");
		e.setEve_usuario(ulogado);
		this.eventoRepository.save(e);
		long id_evento = this.eventoRepository.maxId();
		Optional<Evento> op = eventoRepository.findById(id_evento);
		Evento ev = new Evento();
		if(op.isPresent()) {
			ev = op.get();
		}
		
		Vincular v = new Vincular();
		v.setVin_evento(ev);
		v.setVin_usuario(ulogado);
		this.vincularRepository.save(v);
		return "redirect:/evento/listarEventos";
	}
	
	//Apenas para usar em request insonia
	@PostMapping(value="/criarEvento")
	public Evento criarEvento(@RequestBody Evento e) throws ParseException {
		//listEv.add(e);
		return this.eventoRepository.save(e);
	}
	
	
	//https://www.baeldung.com/spring-thymeleaf-request-parameters
	@RequestMapping("/excluirEvento2")
	public String excluirEvento2(@RequestParam(value = "eve_id", required = false) String id,Model model) {
		//model.addAttribute("id", id);
		long id_del = Long.valueOf(id);
		System.out.println(id_del);
		eventoRepository.deleteById(id_del);
		return "redirect:/evento/listarEventos";
		
	}
	
	@RequestMapping("/vincular")
	public String vincular(@RequestParam(value = "eve_id", required = false) String id,Model model, HttpSession session) {
		//model.addAttribute("id", id);
		long id_evento = Long.valueOf(id);
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");

		//Evento e = eventoRepository.findById(id_evento);
		Optional<Evento> op = eventoRepository.findById(id_evento);
		Evento ev = new Evento();
		if(op.isPresent()) {
			ev = op.get();
		}
		
		//System.out.println(u.getUsu_id());
		
		Vincular v = new Vincular();
		v.setVin_evento(ev);
		v.setVin_usuario(u);
		this.vincularRepository.save(v);
		
		return "redirect:/evento/listarEventos";
		
	}
	

	
	
	@RequestMapping("/convidados/{eve_id}")
	public ModelAndView convidadosDoEvento(@PathVariable("eve_id") long id) {

		Optional<Evento> op = eventoRepository.findById(id);
		Evento ev = new Evento();
		if(op.isPresent()) {
			ev = op.get();
		}
		ModelAndView mv = new ModelAndView("evento/listarEventos");
		mv.addObject("evento",ev);
		return mv;
	}
	
	//@GetMapping("/listarEventos")
	public List<Evento> listarEventosApi() {
		return this.eventoRepository.findAll();
	}

	@GetMapping("/consultarEvento/{id}")
	public Evento consultarEvento(@PathVariable Long id) {
		Optional<Evento> ev =  this.eventoRepository.findById(id);
		if(ev.isPresent()) {
			return ev.get();
		}else {
			return null;
		}
	}
	
	
	
	//@RequestMapping(value="/excluirEvento/{id}", method=RequestMethod.POST)
		//@GetMapping("/excluirEvento")
		//public String excluirEvento(@PathVariable long id) {
		@RequestMapping(value="/excluirEvento", method=RequestMethod.POST)
		public String excluirEvento(Long id) {
			
			System.out.println("chegou no metodo excluir");
			System.out.println(id);
			//Usuario usu = new Usuario();
			//Optional<Usuario> u =  this.usuarioRepository.findById(1L);
			//if(u.isPresent()) {
			//	usu = u.get();
			//}
			//e.setEve_usuario(usu);
			//e.setEve_data(new Date(e.getEve_data().toString()));
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS");
			//SimpleDateFormat formtdate = new SimpleDateFormat("dd/MM/yyyy");
	        //e.setEve_data(formtdate.parse(e.getEve_data().toString())) ;
			//this.eventoRepository.save(e);
			return "redirect:/evento/listarEventos";
			//return new ModelAndView("evento/formEvento");
		}
	
}
