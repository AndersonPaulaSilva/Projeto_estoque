package br.com.fatec.VarCont.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.exceptions.VendaNotFoundException;
import br.com.fatec.VarCont.services.VendaService;

@RestController
public class VendaController {
	@Autowired
	private VendaService serviceVenda;

	@GetMapping("venda")
	public ResponseEntity<Object> listarVendas(){
		try {
				return ResponseEntity.ok(serviceVenda.buscarVendas());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar as vendas");
		}
	}
	
	@GetMapping("venda/{id}")
	public ResponseEntity<Object> vendaBuscaId(@PathVariable(name = "id", required = true) Long id) throws VendaNotFoundException{
		try {
				return ResponseEntity.ok(serviceVenda.buscarVendas());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar as vendas");
		}
	}

	@PostMapping("venda/criar")
	public ResponseEntity<Object> cadastrarVenda(@Valid @RequestBody VendaResource vendaResource) {
		try {
				serviceVenda.criarVenda(vendaResource);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar a venda");
		}
	}
	
	@DeleteMapping("venda/{id}")
	public ResponseEntity<Object> deletarVenda(@PathVariable(name = "id", required = true) Long id) {
		try {
				serviceVenda.deletarId(id);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível deletar a venda" + e);
		}
	}
}