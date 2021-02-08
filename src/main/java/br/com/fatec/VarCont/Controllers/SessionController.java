package br.com.fatec.VarCont.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.LoginResource;


@RestController
public class SessionController {
	/**
	 * 
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<Object> validarLogin(@RequestBody LoginResource loginResource ,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try{
			
			Usuario usuario =  usuarioRepository.findByEmailAndPassword(loginResource.getEmail(), loginResource.getSenha() );
			if(!usuario.equals(null)) {
				String tipo = "";
			if(usuario.isAdmin() == true) {
				 tipo = "admin";
			} else {
					tipo = "caixa";
					
				}
	//			HttpSession session = request.getSession();
	//			session.setAttribute("login", usuario);
	//			 create a cookie
//			String id = String.valueOf(usuario.getId());
//			Cookie cookie = new Cookie("login", id );
//			cookie.setMaxAge(1 * 24 * 60 * 60); // expires in 7 days
//			cookie.setPath("/");
//			//add cookie to response
//			response.addCookie(cookie);s
			return ResponseEntity.ok(tipo);
			}
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return null;
	}
	
//	@RequestMapping("logout")
//	public String logout(HttpServletRequest request) {
//	    HttpSession session = request.getSession(false);
//	    if (session != null) {
//	        session.invalidate();
//	    }
//	    return "redirect:/login";  //Where you go after logout here.
//	}
}
