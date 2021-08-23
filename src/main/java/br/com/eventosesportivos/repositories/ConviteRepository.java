package br.com.eventosesportivos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eventosesportivos.models.Convite;
import br.com.eventosesportivos.models.Usuario;



public interface ConviteRepository extends JpaRepository<Convite,Long>{
	
	//@Query("select i from Convite i where i.con_usuario_convidado = :id")
	//public List<Convite> convitesPorIdDeConvidado(Long id);
	//public List<Convite> convitesPorIdDeConvidado(Usuario u);
}
