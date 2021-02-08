package br.com.fatec.VarCont.Controllers;

import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.Resource.Models.ProdutoResource;
import br.com.fatec.VarCont.exceptions.ProdutoNotFoundException;
import br.com.fatec.VarCont.services.ProdutoService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProdutoController {
    
      
    @Autowired
    private ProdutoService serviceProduto;
    
    
    @GetMapping ("produto")
    public ResponseEntity<Object> listaProduto(HttpSession session){		
				return ResponseEntity.ok(serviceProduto.buscarProduto());
	}
	
    
    @GetMapping("produto/{id}")
	public ResponseEntity<Object> buscarProdutoId(@PathVariable(name = "id", required = true) Long id,HttpSession session){
	try {	
			return ResponseEntity.ok(serviceProduto.buscarId(id));
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}
    }
    
    @PostMapping("produto/criar")
	public ResponseEntity<Object> criarProduto(@Valid @RequestBody ProdutoResource produtoResource,HttpSession session) {
		try {
				serviceProduto.cadastrarProduto(produtoResource);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}

    @PutMapping("produto/{id}")
    public ResponseEntity<Object> alterarProduto(@PathVariable(name = "id", required = true) Long id,@Valid @RequestBody Produto produto, HttpSession session){
    	try {
    		serviceProduto.alterarProduto(id, produto);
    		return ResponseEntity.ok(null);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    	}
    }
    
    @DeleteMapping("produto/{id}")
	public ResponseEntity<Object> deleteProdutoId(@PathVariable(name = "id", required = true) Long id, HttpSession session) throws  ProdutoNotFoundException {
		try {
				serviceProduto.deleteId(id);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
		}
	}
    
	 	    	
    }


    
    
    
    

