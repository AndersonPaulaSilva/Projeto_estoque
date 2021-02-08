package br.com.fatec.VarCont.Resource.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResource {

	@JsonProperty("usuario_email")
	private String email;
	
	@JsonProperty("usuario_senha")
	private String senha;

	
	public LoginResource(String email, String senha) {
		this.email = email;
		this.senha = senha;
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

	@Override
	public String toString() {
		return "LoginResource [email=" + email + ", senha=" + senha + "]";
	}
	
	
}
