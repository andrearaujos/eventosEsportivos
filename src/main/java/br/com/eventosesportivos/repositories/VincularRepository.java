package br.com.eventosesportivos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import br.com.eventosesportivos.models.Vincular;

public interface VincularRepository extends JpaRepository<Vincular,Long>{
	
}
