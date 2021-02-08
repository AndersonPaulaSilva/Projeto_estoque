package br.com.fatec.VarCont.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.DataSource.Models.Tabela;
import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.DataSource.Models.Venda;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Repository.ProdutoRepository;
import br.com.fatec.VarCont.Repository.TabelaRepository;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.exceptions.VendaResourceException;

@Component
public class VendaConversor {

	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private TabelaRepository tabelaRepository;
	
	public Venda conversor(VendaResource vendaResource) throws VendaResourceException {

		try {
			int qtd = checkInt(vendaResource.getQtdVenda());
			checkEstoque(vendaResource.getIdProduto(), qtd);
			Optional<Lote> optionalLote = loteRepository.findByPepsProximo(vendaResource.getIdProduto());
			Optional<Usuario> optionalUsuario = usuarioRepository.findById(vendaResource.getIdUsuario());
			Optional<Produto> optionalProduto = produtoRepository.findById(vendaResource.getIdProduto());
			Produto produto = new Produto();
			produto = optionalProduto.get();
			Lote lote = new Lote();
			lote = optionalLote.get();
			Usuario usuario = new Usuario();
			usuario = optionalUsuario.get();
			Venda venda = new Venda();
			int qtdConta = qtd;
			int antigoTotal = lote.getQtdTotal();
			if(antigoTotal < qtdConta) {
				while(qtdConta > 0) {
					Long loteId = lote.getId();
					if(antigoTotal >= qtdConta) {
					antigoTotal -= qtdConta;
					qtdConta = 0;
					loteRepository.updateQtdTotal(antigoTotal, loteId);
				} else {
				antigoTotal -= qtdConta;
				qtdConta = -(antigoTotal);
				antigoTotal = 0;
				loteRepository.updateQtdTotal(antigoTotal, loteId);
				optionalLote = loteRepository.findByPepsProximo(vendaResource.getIdProduto());
				lote = new Lote();
				lote = optionalLote.get();
				antigoTotal = lote.getQtdTotal();
				}
				}
			}else {
				Long loteId = lote.getId();
				antigoTotal -= qtdConta;
				loteRepository.updateQtdTotal(antigoTotal, loteId);
			}
			Tabela tabela = new Tabela();
			tabela.setData(new Date());
			tabela.setProduto(produto);
			tabela.setQtd(qtd);
			tabela.setTipo(true);
			tabelaRepository.saveAndFlush(tabela);
			venda.setProduto(produto);
			venda.setUsuario(usuario);
			venda.setQtdVenda(qtd);
			return venda;
		} catch (Exception e) {
			throw new VendaResourceException("Falha ao converter o resource para entidade, resource: " + vendaResource + " " + e);
		}

	}
	
	private int checkInt(String num) {
		return Integer.parseInt(num);
	}
	
	private void checkEstoque(Long id, int qtd) {
		int total = loteRepository.findEstoqueTotal(id);
		if(total < qtd) {
			 throw new Error("Não há quantidade suficiente no estoque");
		}
	}
}
