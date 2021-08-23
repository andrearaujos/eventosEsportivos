package br.com.eventosesportivos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eventosesportivos.models.Evento;


public interface EventoRepository extends JpaRepository<Evento,Long>{
	
	@Query("select max(i.eve_id) from Evento i")
	public long maxId();
}
