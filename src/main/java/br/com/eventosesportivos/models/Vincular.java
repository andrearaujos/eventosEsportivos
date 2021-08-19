package br.com.eventosesportivos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vincular", schema = "eventosesportivos")
public class Vincular {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vin_id")
	private Long vin_id;
	
	@ManyToOne
	@JoinColumn(name = "vin_usu_id")
	private Usuario vin_usuario;
	
	@ManyToOne
	@JoinColumn(name = "vin_eve_id")
	private Evento vin_evento;
	
	/*
	@Column(name = "vin_eve_id")
	private Long vin_eve_id;
	
	@Column(name = "vin_usu_id")
	private Long vin_usu_id;
	*/

	public Long getVin_id() {
		return vin_id;
	}

	public void setVin_id(Long vin_id) {
		this.vin_id = vin_id;
	}

	public Evento getVin_evento() {
		return vin_evento;
	}

	public void setVin_evento(Evento vin_eve) {
		this.vin_evento = vin_eve;
	}

	public Usuario getVin_usuario() {
		return vin_usuario;
	}

	public void setVin_usuario(Usuario vin_usu_id) {
		this.vin_usuario = vin_usu_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vin_evento == null) ? 0 : vin_evento.hashCode());
		result = prime * result + ((vin_id == null) ? 0 : vin_id.hashCode());
		result = prime * result + ((vin_usuario == null) ? 0 : vin_usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vincular other = (Vincular) obj;
		if (vin_evento == null) {
			if (other.vin_evento != null)
				return false;
		} else if (!vin_evento.equals(other.vin_evento))
			return false;
		if (vin_id == null) {
			if (other.vin_id != null)
				return false;
		} else if (!vin_id.equals(other.vin_id))
			return false;
		if (vin_usuario == null) {
			if (other.vin_usuario != null)
				return false;
		} else if (!vin_usuario.equals(other.vin_usuario))
			return false;
		return true;
	}

	
	
	
	
}
