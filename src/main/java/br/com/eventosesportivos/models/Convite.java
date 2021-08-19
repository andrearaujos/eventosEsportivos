package br.com.eventosesportivos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "convite", schema = "eventosesportivos")
public class Convite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_id")
	private Long con_id;
	
	@ManyToOne
	@JoinColumn(name = "con_usu_convidante_id")
	private Usuario con_usuario_convidante;

	@OneToOne
	@JoinColumn(name = "con_usu_convidado_id")
	private Usuario con_usuario_convidado;

	@ManyToOne
	@JoinColumn(name = "con_eve_id")
	private Evento con_evento;

	public Long getCon_id() {
		return con_id;
	}

	public void setCon_id(Long con_id) {
		this.con_id = con_id;
	}

	public Usuario getCon_usuario_convidante() {
		return con_usuario_convidante;
	}

	public void setCon_usuario_convidante(Usuario con_usuario_convidante) {
		this.con_usuario_convidante = con_usuario_convidante;
	}

	public Usuario getCon_usuario_convidado() {
		return con_usuario_convidado;
	}

	public void setCon_usuario_convidado(Usuario con_usuario_convidado) {
		this.con_usuario_convidado = con_usuario_convidado;
	}

	public Evento getCon_evento() {
		return con_evento;
	}

	public void setCon_evento(Evento con_evento) {
		this.con_evento = con_evento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((con_evento == null) ? 0 : con_evento.hashCode());
		result = prime * result + ((con_id == null) ? 0 : con_id.hashCode());
		result = prime * result + ((con_usuario_convidado == null) ? 0 : con_usuario_convidado.hashCode());
		result = prime * result + ((con_usuario_convidante == null) ? 0 : con_usuario_convidante.hashCode());
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
		Convite other = (Convite) obj;
		if (con_evento == null) {
			if (other.con_evento != null)
				return false;
		} else if (!con_evento.equals(other.con_evento))
			return false;
		if (con_id == null) {
			if (other.con_id != null)
				return false;
		} else if (!con_id.equals(other.con_id))
			return false;
		if (con_usuario_convidado == null) {
			if (other.con_usuario_convidado != null)
				return false;
		} else if (!con_usuario_convidado.equals(other.con_usuario_convidado))
			return false;
		if (con_usuario_convidante == null) {
			if (other.con_usuario_convidante != null)
				return false;
		} else if (!con_usuario_convidante.equals(other.con_usuario_convidante))
			return false;
		return true;
	}
	
	/*
	@Column(name = "con_usu_convidante_id")
	private Long con_usu_convidante_id;
	
	@Column(name = "con_usu_convidado_id")
	private Long con_usu_convidado_id;
	
	@Column(name = "con_evento_id")
	private Long con_evento_id;
	*/
	
	
	
	
	
	
}
