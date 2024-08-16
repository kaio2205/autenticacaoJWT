package br.com.authjwt.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name ="usuarios")
@Entity

public class Usuario implements UserDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;
	

	@Column(nullable = false )
	private  String nomeCompleto;
	@Column(unique = true , length = 100, nullable = false )
	private  String email;
	@Column( nullable = false )
	private  String senha;
	
	
	@CreationTimestamp
	@Column(updatable = false, name="criado_em")
	private  Date  criadoem;
	
	@UpdateTimestamp
	@Column( name="Atualizado_em")
	private Date atualizadoEm;
	
	@Override
	public Collection<? extends GrantedAuthority>  getAuthorities(){
		return List.of();
	}

	
	@Override
	public String getPassword() {
		
		return senha ;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getCriadoem() {
		return criadoem;
	}

	public void setCriadoem(Date criadoem) {
		this.criadoem = criadoem;
	}

	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
	
	
	
	
	
}
