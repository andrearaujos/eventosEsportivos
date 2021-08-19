package br.com.eventosesportivos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eventosesportivos.models.Evento;

public interface EventoRepository extends JpaRepository<Evento,Long>{
	
}
