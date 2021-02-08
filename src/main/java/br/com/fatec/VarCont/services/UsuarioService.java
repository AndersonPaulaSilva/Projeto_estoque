package br.com.fatec.VarCont.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;
import br.com.fatec.VarCont.exceptions.UsuarioNotFoundException;
import br.com.fatec.VarCont.exceptions.UsuarioResourceException;

@Service
public class UsuarioService {

	private static final Logger LOG = Logger.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioConversor serviceConversor;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private Optional<Usuario> getOptional(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		return optionalUsuario;
	}

	public List<Usuario> buscarUsuario() {
		LOG.info("Serviço para buscar os usuario, sendo executado");
		List<Usuario> listaUsuarios = usuarioRepository.findAll();

		return listaUsuarios;
	}

	public Usuario buscarId(Long id) throws UsuarioNotFoundException {
		Optional<Usuario> optionalUsuario = getOptional(id);
		Usuario usuario = null;
		if (!optionalUsuario.isPresent()) {
			throw new UsuarioNotFoundException("Usuario não encontrado através do ID: " + id);
		} else {
			usuario = optionalUsuario.get();
		}
		LOG.info("Serviço para buscar usuario, sendo executado");
		return usuario;
	}

	public void deletarId(Long id) throws UsuarioNotFoundException {
		Optional<Usuario> optionalUsuario = getOptional(id);
		if (!optionalUsuario.isPresent()) {
			throw new UsuarioNotFoundException("Usuario não encontrado através do ID: " + id);
		} else {
			LOG.info("Serviço para deletar usuario, sendo executado");
			usuarioRepository.delete(optionalUsuario.get());
		}
	}
    
	public void cadastrarUsuario(UsuarioResource usuarioResource){
	try {
		LOG.info("Serviço para criar caixa, sendo executado");
		Usuario usuario = serviceConversor.conversor(usuarioResource);
    	usuarioRepository.saveAndFlush(usuario);

	} catch (UsuarioResourceException e) {
		LOG.error("Erro em salva o usuario: " + e.getMessage(), e);
	}
       
}
	public void alterarUsuario(UsuarioResource usuarioResource, Long id) throws UsuarioNotFoundException, UsuarioResourceException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (!usuarioOptional.isPresent()) {
			throw new UsuarioNotFoundException("Usuario não encontrado através do ID: " + id);
		}
			usuarioRepository.save(serviceConversor.alterarConversor(usuarioResource, id));
	}
}