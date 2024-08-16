package br.com.authjwt.dtos;

public class LoginUsuarioDto {
	private String email;
	private String senha;

public String getEmail() {
	return email;
}
public LoginUsuarioDto setEmail(String email) {
	this.email = email;
	return this;
}
public String getSenha() {
	return senha;
}
public LoginUsuarioDto setSenha(String senha) {
	this.senha = senha;
	return this;
}


}

