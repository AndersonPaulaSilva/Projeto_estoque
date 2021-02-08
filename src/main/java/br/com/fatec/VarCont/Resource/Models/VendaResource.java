package br.com.fatec.VarCont.Resource.Models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class VendaResource {
	@JsonProperty("usuario_id")
    private Long idUsuario;
    
    @JsonProperty ("prod_id")
    private Long idProduto;
    
    @JsonProperty("ven_qtd")
    private String qtdVenda;

    
    public VendaResource(Long idUsuario, Long idProduto, String qtdVenda) {
    	this.idUsuario = idUsuario;
    	this.idProduto = idProduto;
    	this.qtdVenda = qtdVenda;
    }
    
    
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getQtdVenda() {
		return qtdVenda;
	}

	public void setQtdVenda(String qtdVenda) {
		this.qtdVenda = qtdVenda;
	}


	@Override
	public String toString() {
		return "VendaResource [idUsuario=" + idUsuario + ", idProduto=" + idProduto + ", qtdVenda=" + qtdVenda + "]";
	}

	
    
    
}

