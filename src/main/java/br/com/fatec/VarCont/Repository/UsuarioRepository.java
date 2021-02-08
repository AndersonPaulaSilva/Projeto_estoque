/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.Repository;

/**
 *
 * @author OkamotoPc
 */
import br.com.fatec.VarCont.DataSource.Models.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("SELECT u FROM Usuario u WHERE u.email = :email and u.senha = :senha")
	public Usuario findByEmailAndPassword(@Param("email") String email, @Param("senha") String senha);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	public Optional<Usuario> findByEmail(@Param("email") String email);
	
}
