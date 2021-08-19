package br.com.eventosesportivos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario" ,schema = "eventosesportivos")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private Long usu_id;
	
	@Column(name = "usu_nome_completo")
	private String usu_nome_completo;
	
	@Column(name = "usu_apelido")
	private String usu_apelido;
	
	@Column(name = "usu_email")
	private String email;
	
	@Column(name = "usu_senha")
	private String senha;

	public Long getUsu_id() {
		return usu_id;
	}

	public void setUsu_id(Long usu_id) {
		this.usu_id = usu_id;
	}

	public String getUsu_nome_completo() {
		return usu_nome_completo;
	}

	public void setUsu_nome_completo(String usu_nome_completo) {
		this.usu_nome_completo = usu_nome_completo;
	}

	public String getUsu_apelido() {
		return usu_apelido;
	}

	public void setUsu_apelido(String usu_apelido) {
		this.usu_apelido = usu_apelido;
	}

	public String getUsu_email() {
		return email;
	}

	public void setUsu_email(String usu_email) {
		this.email = usu_email;
	}

	public String getUsu_senha() {
		return senha;
	}

	public void setUsu_senha(String usu_senha) {
		this.senha = usu_senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usu_apelido == null) ? 0 : usu_apelido.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((usu_id == null) ? 0 : usu_id.hashCode());
		result = prime * result + ((usu_nome_completo == null) ? 0 : usu_nome_completo.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (usu_apelido == null) {
			if (other.usu_apelido != null)
				return false;
		} else if (!usu_apelido.equals(other.usu_apelido))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (usu_id == null) {
			if (other.usu_id != null)
				return false;
		} else if (!usu_id.equals(other.usu_id))
			return false;
		if (usu_nome_completo == null) {
			if (other.usu_nome_completo != null)
				return false;
		} else if (!usu_nome_completo.equals(other.usu_nome_completo))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
	

}
