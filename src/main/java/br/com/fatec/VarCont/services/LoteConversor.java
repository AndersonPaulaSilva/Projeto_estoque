package br.com.fatec.VarCont.services;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.DataSource.Models.Tabela;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Repository.ProdutoRepository;
import br.com.fatec.VarCont.Repository.TabelaRepository;
import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteNotFoundException;
import br.com.fatec.VarCont.exceptions.LoteResourceException;

@Component
public class LoteConversor {
	@Autowired
	private ProdutoRepository produtoRepository;
		
	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private TabelaRepository tabelaRepository;
			
	
	public Lote conversor (LoteResource loteResource) throws LoteResourceException{
		
		try {
			Optional<Produto> optionalProduto = produtoRepository.findById(loteResource.getIdProduto());
			Tabela tabela = new Tabela();
			Produto produto = new Produto();
			produto = optionalProduto.get();
			Lote lote = new Lote();
			int qtdCompra = checkCompra(loteResource.getQtdCompra());
			tabela.setData(new Date());
			tabela.setQtd(qtdCompra);
			tabela.setProduto(produto);
			tabela.setTipo(false);
			tabelaRepository.saveAndFlush(tabela);
			lote.setData(new Date());
			lote.setProduto(produto);
			lote.setQtdCompra(qtdCompra);
			lote.setQtdTotal(qtdCompra);
			return lote;
		}catch(Exception e) {
			throw new LoteResourceException(
					"Falha ao converter o resource para entidade, resource: " + loteResource);
			
		}
		
	}

	public void conversorAltera(LoteResource loteResource, Long id) throws LoteResourceException{
		
		try {
			Optional<Lote> optionalLote= loteRepository.findById(id);
			if(!optionalLote.isPresent()) {
				throw new LoteNotFoundException("Lote não encontrado através do ID:" + id);
			}
			Lote lote = optionalLote.get();
			if(lote.getQtdTotal()!= lote.getQtdCompra()) {
				throw new Exception("Não é possível alterar um lote já consumido");
			}
			Produto produto = lote.getProduto();
			Tabela tabela;
			Optional<Tabela> optionalTabela = tabelaRepository.findByData(produto.getId(),lote.getData());
			tabela = optionalTabela.get();
			if(loteResource.getQtdCompra() != null) {
				int qtdCompra = checkCompra(loteResource.getQtdCompra());
				lote.setQtdCompra(qtdCompra);
				lote.setQtdTotal(qtdCompra);
				tabela.setQtd(qtdCompra);
			}
			if(loteResource.getIdProduto() != null) {
				Optional<Produto> optionalProduto = produtoRepository.findById(loteResource.getIdProduto());
				produto = optionalProduto.get();
				lote.setProduto(produto);
				tabela.setProduto(produto);
			}
			loteRepository.save(lote);
			tabelaRepository.save(tabela);
		}catch(Exception e) {
			throw new LoteResourceException(
					"Falha ao converter o resource para entidade, resource: " + loteResource);
			
		}
		
	}
	
	
	private int checkCompra(String num) {
		return Integer.parseInt(num);
	}
}
