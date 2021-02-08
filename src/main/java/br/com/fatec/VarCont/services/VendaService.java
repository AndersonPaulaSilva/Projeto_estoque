package br.com.fatec.VarCont.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.DataSource.Models.Tabela;
import br.com.fatec.VarCont.DataSource.Models.Venda;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Repository.TabelaRepository;
import br.com.fatec.VarCont.Repository.VendaRepository;
import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.exceptions.VendaNotFoundException;
import br.com.fatec.VarCont.exceptions.VendaResourceException;

@Service
public class VendaService {
	private static final Logger LOG = Logger
            .getLogger(LoteService.class);
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private LoteRepository loteRepository;
	
	
	@Autowired
	private VendaConversor serviceConversor;
	
	@Autowired
	private TabelaRepository tabelaRepository;
	
	
	public List<Venda> buscarVendas(){
		LOG.info("Serviço de listagem de vendas, sendo realizado");
		List<Venda> listaVenda = vendaRepository.findAll();
		return listaVenda;
	}
		
	
	public List<Venda> buscarVendaId(){
		LOG.info("Serviço de listagem de vendas, sendo realizado");
		List<Venda> listaVenda = vendaRepository.findAll();
		return listaVenda;
	}
		
	
	public void criarVenda(VendaResource vendaResource) {
		try {
			LOG.info("Serviço de criação de venda, sendo executado");
			Venda venda = serviceConversor.conversor(vendaResource);
			vendaRepository.saveAndFlush(venda);
		}catch(VendaResourceException e){
			LOG.error("Erro em salvar o produto" + e.getMessage(), e);
		}
	}
	
	public void deletarId(Long id) throws VendaNotFoundException {	
			LOG.info("Serviço de criação de venda, sendo executado");
			Optional<Venda> optionalVenda = vendaRepository.findById(id);
			if(!optionalVenda.isPresent()) {
				throw new VendaNotFoundException("A venda cujo o id é :" + id + " não foi encontrada");
			}
			Venda venda = optionalVenda.get();
			Produto produto = venda.getProduto();
			Optional<Lote> optionalLote = loteRepository.findLoteUsado(produto.getId());
			Lote lote = optionalLote.get();
			int qtdVenda = venda.getQtdVenda();
			int qtdTotal = lote.getQtdTotal();
			int qtdCompra = lote.getQtdCompra();
			while (qtdVenda > 0 ) {
				if(qtdTotal == lote.getQtdCompra()) {
					optionalLote = loteRepository.findLoteUsado(produto.getId());
					lote = optionalLote.get();
					qtdTotal = lote.getQtdTotal();
					qtdCompra = lote.getQtdCompra();
				}
				int qtdAux = qtdCompra - qtdTotal;
				if(qtdVenda >= qtdAux) {
					qtdTotal += qtdAux;
					qtdVenda -= qtdAux;
				}else {
					qtdTotal += qtdVenda;
					qtdVenda = 0;
				}
				Long idLote = lote.getId();
				loteRepository.updateQtdTotal(qtdTotal, idLote);
			}
			
			Optional<Tabela> optionalTabela = tabelaRepository.findByData(produto.getId(), venda.getData());
			tabelaRepository.delete(optionalTabela.get());
			vendaRepository.delete(venda);
			
	}
}
