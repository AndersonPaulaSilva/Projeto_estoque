package br.com.fatec.VarCont.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.Resource.Models.UsuarioResource;
import br.com.fatec.VarCont.services.UsuarioService;
import br.com.fatec.VarCont.services.UsuarioConversor;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserControllers {

	@Autowired
	UsuarioConversor serviceConversor;

	@Autowired
	private UsuarioService serviceUsuario;


	@GetMapping(path = "/usuario")
	public ResponseEntity<Object> buscarUsuario() {
			return ResponseEntity.ok(serviceUsuario.buscarUsuario());

	}

	@GetMapping("usuario/{id}")
	public ResponseEntity<Object> buscarCaixaId(@PathVariable(name = "id", required = true) Long id, HttpSession session) {
		try {
				return ResponseEntity.ok(serviceUsuario.buscarId(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}
		
	}

	@PostMapping("usuario/criar")
	public ResponseEntity<Object> criarCaixa(@Valid @RequestBody UsuarioResource usuarioResource, HttpSession session) {
		try {
				serviceUsuario.cadastrarUsuario(usuarioResource);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
	}

	@DeleteMapping("usuario/{id}")
	public ResponseEntity<Object> deleteCaixa(@PathVariable(name = "id", required = true) Long id ) {
		try {
				serviceUsuario.deletarId(id);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}	
}
	@PutMapping("usuario/{id}")
	public ResponseEntity<Object> alterarCaixa(@PathVariable Long id, @RequestBody UsuarioResource usuarioResource) {
		try {
				serviceUsuario.alterarUsuario(usuarioResource, id);
				return ResponseEntity.ok("Usuario alterado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}

	}

}
