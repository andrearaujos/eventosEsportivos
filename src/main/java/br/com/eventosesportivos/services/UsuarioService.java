package br.com.eventosesportivos.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.eventosesportivos.models.Usuario;
import br.com.eventosesportivos.repositories.EventoRepository;
import br.com.eventosesportivos.repositories.UsuarioRepository;
import br.com.eventosesportivos.util.Util;
import br.com.eventosesportivos.exceptions.CripitografiaException;
import br.com.eventosesportivos.exceptions.UsuarioException;

@Service
public class UsuarioService {
	
		
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	//vem do metodo cadastrar_usuario do usuario_controller
	public void salvarUsuario(Usuario u) throws Exception{
		try {
			if(usuarioRepository.findByEmail(u.getUsu_email()) != null ) {
				throw new UsuarioException("Já existe um e-mail cadatrado para: "+u.getUsu_email());
			}
			//System.out.println("teste md5");
			Util ut = new Util();
			String senhaCriptografadamd5 = ut.md5(u.getUsu_senha());
			//u.setUsu_senha(senhaCriptografadamd5);
			//u.setUsu_senha(Util.md5(u.getUsu_senha()));
		}catch (NoSuchAlgorithmException e) {
			throw new CripitografiaException("Erro na cripitografia da senha");
		}
		this.usuarioRepository.save(u); 
	}
	
	
	public Usuario loginUsuario(String email, String senha) throws UsuarioException{
		System.out.println("email"+email);
		System.out.println("senha"+senha);
		Usuario usuarioLogin = usuarioRepository.buscarLogin(email, senha);
		System.out.println(usuarioLogin);
		//System.out.println(usuarioLogin.getUsu_apelido());
		return usuarioLogin;
	}
	
	
	
	
	
	public Usuario autenticarUsario(String email, String senha){
		Usuario usuario = new Usuario();
		
		return usuario;
	}
	
	public Usuario cadatrarUsuario(Usuario usuario) {
		
		return usuario;
	}
	
	public void validarEmail(String email) {
		/*
		if(ur.existeEmail(email)) {
			throw new UsuarioException("E-mail já está cadastrado");
		}
		*/
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 UsuarioRepository ur = new UsuarioRepository(){
	 @Override
		public <S extends Usuario> Optional<S> findOne(Example<S> example) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Usuario> Page<S> findAll(Example<S> example, Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Usuario> boolean exists(Example<S> example) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public <S extends Usuario> long count(Example<S> example) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public <S extends Usuario> S save(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Optional<Usuario> findById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void deleteById(Long id) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAllById(Iterable<? extends Long> ids) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll(Iterable<? extends Usuario> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void delete(Usuario entity) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Page<Usuario> findAll(Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Usuario> S saveAndFlush(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Usuario> List<S> saveAllAndFlush(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Usuario> List<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Usuario getOne(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Usuario getById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public List<Usuario> findAllById(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Usuario> List<S> findAll(Example<S> example, Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Usuario> List<S> findAll(Example<S> example) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<Usuario> findAll(Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<Usuario> findAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void deleteAllInBatch(Iterable<Usuario> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAllInBatch() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAllByIdInBatch(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			
		}
	}; 
	 
	 * */
	
}
