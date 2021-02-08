package br.com.fatec.VarCont.services;

import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Repository.ProdutoRepository;
import br.com.fatec.VarCont.Resource.Models.ProdutoResource;
import br.com.fatec.VarCont.exceptions.ProdutoNotFoundException;
import br.com.fatec.VarCont.exceptions.ProdutoResourceException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProdutoService {
    
    private static final Logger LOG = Logger
            .getLogger(ProdutoService.class);
            
    @Autowired
    private ProdutoConversor serviceConversor;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private LoteRepository loteRepository;
    
    private Optional<Produto> getOptional(Long id) {
    	Optional<Produto> optionalProduto = produtoRepository.findById(id);
    	return optionalProduto;
    } 
    
    
    public void cadastrarProduto(ProdutoResource produtoResource){
    	try {
    		LOG.info("Serviço para criar produto, sendo executado");
    		Produto produto = serviceConversor.conversor(produtoResource);
    		produtoRepository.saveAndFlush(produto);
    		
    	} catch (ProdutoResourceException e) {
    		LOG.error("Erro em salva o produto: " + e.getMessage(), e);
    	}
    	
    }

    
    public List<Produto> buscarProduto()
    {
        LOG.info ("Serviço para buscar os produtos sendo executado");
        List<Produto> listaProdutos = produtoRepository.findAll();
        return listaProdutos;
    }
    
    
    public Produto buscarId(Long id) throws ProdutoNotFoundException {
		Optional<Produto> optionalProduto = getOptional(id);
		Produto produto = null;
		if (!optionalProduto.isPresent()) {
			throw new ProdutoNotFoundException(" Produto não encontrado através do ID: " + id);
		} 	
		produto = optionalProduto.get();
		LOG.info("Serviço para buscar produto, sendo executado");
		return produto;
	}
	
	
    public void alterarProduto(Long id, Produto produto) throws ProdutoNotFoundException {
    	Optional<Produto> produtoOptional = produtoRepository.findById(id);
    	if(!produtoOptional.isPresent()) {
    		throw new ProdutoNotFoundException("O produto não existe");
    	}else {
    		produto.setId(id);
    		produtoRepository.saveAndFlush(produto);    		
    	} 	   
    }
    
    
    public void deleteId(Long id) throws Exception {
    	Optional<Produto> optionalProduto = getOptional(id);
    	if (!optionalProduto.isPresent()) {
    		throw new ProdutoNotFoundException(" Produto não encontrado através do ID: " + id);
    	}
    	Optional<Lote> optionalLote = loteRepository.findProdutoLote(id);
    	if(optionalLote.isPresent()) {
    		throw new Exception("Não é possível deletar o produto, pois existe um lote deste produto");
    	}
    	LOG.info("Serviço para deletar produto, sendo executado");
    	produtoRepository.delete(optionalProduto.get());
    }
}
