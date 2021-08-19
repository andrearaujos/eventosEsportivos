package br.com.eventosesportivos.models;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "evento", schema = "eventosesportivos")
public class Evento  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//gerar id autoincremental
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eve_id")
	private Long eve_id;
	
	@Column(name = "eve_nome_desricao")
	private String eve_nome_desricao;
	
	@Column(name = "eve_data")
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@JsonFormat(pattern="dd/MM/yyyy")
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	//private Date eve_data;
	//private Date eve_data;
	private String eve_data;
	//Como pegar data dinamicamente
	//@Column(name = "data")
	//@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	//private Date data;
	
	@Column(name = "eve_hora")
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@JsonFormat(pattern="dd/MM/yyyy")
	//private Time eve_hora;
//	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//	dateFormat.setLenient(false);
	private String eve_hora;
	
	@Column(name = "eve_local_endereco")
	private String eve_local_endereco;
	
	@ManyToOne
	@JoinColumn(name = "eve_usu_id")
	private Usuario eve_usuario;
	
	//--------------------------------------------------------------------------------------
	
	public Long getEve_id() {
		return eve_id;
	}

	public void setEve_id(Long eve_id) {
		this.eve_id = eve_id;
	}

	public String getEve_nome_desricao() {
		return eve_nome_desricao;
	}

	public void setEve_nome_desricao(String eve_nome_desricao) {
		this.eve_nome_desricao = eve_nome_desricao;
	}
	
//	public S getEve_data() {
//		return eve_data;
//	}
//
//	public void setEve_data(Date eve_data) {
//		this.eve_data = eve_data;
//	}
	
	
	public String getEve_data() {
		return eve_data;
	}

	public void setEve_data(String eve_data) {
		this.eve_data = eve_data;
	}
	
	
//	public Time getEve_hora() {
//		return eve_hora;
//	}
//
//	public void setEve_hora(Time eve_hora) {
//		this.eve_hora = eve_hora;
//	}
	
	
	public String getEve_hora() {
		return eve_hora;
	}

	public void setEve_hora(String eve_hora) {
		this.eve_hora = eve_hora;
	}
	
	public String getEve_local_endereco() {
		return eve_local_endereco;
	}
	

	public void setEve_local_endereco(String eve_local_endereco) {
		this.eve_local_endereco = eve_local_endereco;
	}

	public Usuario getEve_usuario() {
		return eve_usuario;
	}

	public void setEve_usuario(Usuario eve_usu) {
		this.eve_usuario = eve_usu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eve_data == null) ? 0 : eve_data.hashCode());
		result = prime * result + ((eve_hora == null) ? 0 : eve_hora.hashCode());
		result = prime * result + ((eve_id == null) ? 0 : eve_id.hashCode());
		result = prime * result + ((eve_local_endereco == null) ? 0 : eve_local_endereco.hashCode());
		result = prime * result + ((eve_nome_desricao == null) ? 0 : eve_nome_desricao.hashCode());
		result = prime * result + ((eve_usuario == null) ? 0 : eve_usuario.hashCode());
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
		Evento other = (Evento) obj;
		if (eve_data == null) {
			if (other.eve_data != null)
				return false;
		} else if (!eve_data.equals(other.eve_data))
			return false;
		if (eve_hora == null) {
			if (other.eve_hora != null)
				return false;
		} else if (!eve_hora.equals(other.eve_hora))
			return false;
		if (eve_id == null) {
			if (other.eve_id != null)
				return false;
		} else if (!eve_id.equals(other.eve_id))
			return false;
		if (eve_local_endereco == null) {
			if (other.eve_local_endereco != null)
				return false;
		} else if (!eve_local_endereco.equals(other.eve_local_endereco))
			return false;
		if (eve_nome_desricao == null) {
			if (other.eve_nome_desricao != null)
				return false;
		} else if (!eve_nome_desricao.equals(other.eve_nome_desricao))
			return false;
		if (eve_usuario == null) {
			if (other.eve_usuario != null)
				return false;
		} else if (!eve_usuario.equals(other.eve_usuario))
			return false;
		return true;
	}
	
	
	
}
