package br.com.fatec.VarCont.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.DataSource.Models.Tabela;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Repository.TabelaRepository;
import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteNotFoundException;
import br.com.fatec.VarCont.exceptions.LoteResourceException;

@Service
public class LoteService {
	private static final Logger LOG = Logger.getLogger(LoteService.class);
	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private LoteConversor serviceConversor;
	
	@Autowired
	private TabelaRepository tabelaRepository;
	
	private Optional<Lote> getOptional(Long id) {
		Optional<Lote> optionalLote = loteRepository.findById(id);
		return optionalLote;
	}
	
	
	public void cadastrarLote(LoteResource loteResource) {
		try {
			LOG.info("Serviço para criar lote, sendo executado");
			Lote lote = serviceConversor.conversor(loteResource);
			loteRepository.saveAndFlush(lote);
		} catch (LoteResourceException e) {
			LOG.error("Erro em salvar o lote " + e.getMessage(), e);
		}
	}

	
	public List<Lote> buscarLote() {

		LOG.info("Serviço para buscar os lotes, sendo executado");
		List<Lote> listaLotes = loteRepository.findAll();
		return listaLotes;
	}
	
	public void alteraLote(LoteResource loteResource,Long id) throws Exception {
		LOG.info("Serviço para alterar os lotes, sendo executado");
		serviceConversor.conversorAltera(loteResource, id);
	}

	public Lote buscarId(Long id) throws LoteNotFoundException {
		Optional<Lote> optionalLote = getOptional(id);
		Lote lote = null;
		if (!optionalLote.isPresent()) {
			throw new LoteNotFoundException("Lote não encontrado através do ID: " + id);
		} else {
			lote = optionalLote.get();
		}
		LOG.info("Serviço para buscar lote por id sendo executado");
		return lote;
	}


	public void deleteId(Long id) throws Exception {
		Optional<Lote> optionalLote = getOptional(id);
		if (!optionalLote.isPresent()) {
			throw new LoteNotFoundException(" Lote não encontrado através do ID: " + id);
		} 
			Lote lote = optionalLote.get();
			int qtdCompra = lote.getQtdCompra();
			int qtdVenda = lote.getQtdTotal();
			if(qtdVenda != qtdCompra) {
				throw new Exception("Não é possível deletar um lote já utilizado");
			}
			LOG.info("Serviço para deletar lote, sendo realizado");
			Produto produto = lote.getProduto();
			Optional<Tabela> optionalTabela = tabelaRepository.findByData(produto.getId(),lote.getData());
			tabelaRepository.delete(optionalTabela.get());
			loteRepository.delete(lote);
	}
	
	public List<Object> listarEstoques(){
		return loteRepository.findEstoqueTotais();
	}
}