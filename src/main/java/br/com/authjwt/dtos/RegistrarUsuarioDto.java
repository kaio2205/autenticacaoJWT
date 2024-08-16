package br.com.authjwt.dtos;

public class RegistrarUsuarioDto {
private String email;
private String senha;
private String nomecompleto;


public String getEmail() {
	return email;
}
public RegistrarUsuarioDto setEmail(String email) {
	this.email = email;
	return this;
}
public String getSenha() {
	return senha;
}
public RegistrarUsuarioDto setSenha(String senha) {
	this.senha = senha;
	return this;
}
public String getNomecompleto() {
	return nomecompleto;
}
public RegistrarUsuarioDto setNomecompleto(String nomecompleto) {
	this.nomecompleto = nomecompleto;
	return this;
}


}
