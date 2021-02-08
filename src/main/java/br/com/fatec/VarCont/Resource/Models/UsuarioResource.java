package br.com.fatec.VarCont.Resource.Models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UsuarioResource {
  // Aqui � onde decidimos o que colocaremos na requisi��o para api.
	
	@JsonProperty("usuario_nome")
    private String nome;
    
    @JsonProperty("usuario_email")
    private String email;
    
    @JsonProperty("usuario_senha")
    private String senha;
    
    @JsonProperty("usuario_administrador")
    private String admin;
    
    public UsuarioResource(String nome, String email, String senha, String admin) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.admin = admin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "UsuarioResource [nome=" + nome + ", email=" + email + ", senha=" + senha + ", admin=" + admin + "]";
	}

    
}