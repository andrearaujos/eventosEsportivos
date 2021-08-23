package br.com.eventosesportivos.repositories;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eventosesportivos.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	//public boolean existeEmail(String email);
	@Query("select i from Usuario i where i.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("select j from Usuario j where j.email = :email and j.senha = :senha")
	public Usuario buscarLogin(String email, String senha);

	@Query("select k from Usuario k where k.id not in (:id)")
	public List<Usuario> buscarUsuariosNotInId(long id);
	
}
