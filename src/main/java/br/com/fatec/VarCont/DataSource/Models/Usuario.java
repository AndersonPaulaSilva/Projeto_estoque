package br.com.fatec.VarCont.DataSource.Models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import javax.validation.constraints.NotNull;
@Entity
@Table(name = "tbl_usuario")
public class Usuario implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 5818703528229340023L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "usuario_id")        
long id;

@Column(name = "usuario_nome")
@NotEmpty(message = "Nome não pode estar vazio")
String nome;

@Column(name = "usuario_email")
@NotEmpty(message = "Email não pode estar vazio")
@Email
String email;

@Column(name = "usuario_senha")
@NotEmpty(message = "Senha não pode estar vazia")
@Size(min = 4, max = 16, message ="A senha deve ter de 6 a 16 caracteres")
String senha;
    
@Column(name = "usuario_administrador")
@NotNull(message= "Tipo de usuário não ser vazio")
boolean admin;

   
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}