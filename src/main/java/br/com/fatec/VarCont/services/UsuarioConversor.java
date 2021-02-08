/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.exceptions.UsuarioResourceException;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;

/**
 *
 * @author OkamotoPc
 */
@Component
public class UsuarioConversor {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario conversor(UsuarioResource usuarioResource) throws UsuarioResourceException {

		try {
			checkEmail(usuarioResource.getEmail());
			Usuario usuario = new Usuario();
			Boolean adminstrador = checkAdministrador(usuarioResource.getAdmin());
			usuario.setEmail(usuarioResource.getEmail());
			usuario.setNome(usuarioResource.getNome());
			usuario.setSenha(usuarioResource.getSenha());
			usuario.setAdmin(adminstrador);
			return usuario;
			
		} catch (Exception e) {
			throw new UsuarioResourceException(
					"Falha ao converter o resource para entidade, resource: "
								+ usuarioResource + " " + e);
		}
	}
	
	public Usuario alterarConversor(UsuarioResource usuarioResource, Long id) throws UsuarioResourceException {

		try {
			
			Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
			Usuario usuario = optionalUsuario.get();
			
			String email = usuarioResource.getEmail();
			String senha = usuarioResource.getSenha();
			String nome = usuarioResource.getNome();
			Boolean administrador = checkAdministrador(usuarioResource.getAdmin());
			
			if(email != null) {
				String emailconfere = usuario.getEmail();
				if(emailconfere.contentEquals(email) == false ) {
					checkEmail(email);
				}					
				usuario.setEmail(email);					
			}
			if(nome != null) {
				usuario.setNome(nome);
			}
			if(senha != null) {
				usuario.setSenha(senha);
			}
			if(administrador != null) {
				usuario.setAdmin(administrador);
			}
			return usuario;
			
		} catch (Exception e) {
			throw new UsuarioResourceException(
					"Falha ao converter o resource para entidade, resource: "
								+ usuarioResource + " " + e);
		}
	}
		private Boolean checkAdministrador(String adminstrador) {
			return Boolean.parseBoolean(adminstrador);
		}
		public void checkEmail(String email) {
			Optional<Usuario> optionalEmail = usuarioRepository.findByEmail(email);
			if(optionalEmail.isPresent()) {
				throw new Error("Já existe um usuário com este email");
			}
		}
}
