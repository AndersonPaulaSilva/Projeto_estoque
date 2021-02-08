package br.com.fatec.VarCont.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Tabela;
import br.com.fatec.VarCont.Repository.TabelaRepository;

@RestController
public class TabelaController {

	@Autowired
	private TabelaRepository tabelaRepository;
	
	@GetMapping("tabela/{id}")
	public List<Tabela> buscarTabelas(@PathVariable(name = "id", required = true) Long id){
		return tabelaRepository.findByIdProd(id);
	}
}
