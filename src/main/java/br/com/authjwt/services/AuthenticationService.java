package br.com.authjwt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.authjwt.dtos.LoginUsuarioDto;
import br.com.authjwt.dtos.RegistrarUsuarioDto;
import br.com.authjwt.entities.Usuario;
import br.com.authjwt.repositories.UsuarioRepository;

@Service
public class AuthenticationService {
private  UsuarioRepository usuarioRespository;
private PasswordEncoder senhaEncoder;
private AuthenticationManager authenticationManager;

public AuthenticationService(
		UsuarioRepository ur,
		AuthenticationManager am,
		PasswordEncoder pe
		) {
	
	
	
	usuarioRespository = ur;
	senhaEncoder = pe;
	authenticationManager = am;
	
	
	}


public Usuario  registrar (RegistrarUsuarioDto rud) {
	Usuario us = new Usuario();
	us.setNomeCompleto(rud.getNomecompleto());
	us.setEmail(rud.getEmail());
	us.setSenha(senhaEncoder.encode(rud.getSenha()));
	return usuarioRespository.save(us);
}



public Usuario  login (LoginUsuarioDto lud) {
	authenticationManager.authenticate(
			
			new UsernamePasswordAuthenticationToken(
					lud.getEmail(),
					lud.getSenha()
					)
			
			
		);
		return usuarioRespository.findByEmail(lud.getEmail()).orElseThrow();
}

public List<Usuario> listar(){
	List <Usuario> lst  = new  ArrayList<Usuario>();
	usuarioRespository.findAll().forEach(lst :: add);
	return lst;			
}

}
