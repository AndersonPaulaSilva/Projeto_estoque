package br.com.fatec.VarCont.Resource.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoResource {
	 // Aqui � onde decidimos o que colocaremos na requisi��o para api.
    @JsonProperty ("prod_nome")
    private String nomeProd; 
    
    @JsonProperty ("prod_valor_compra")
    private Double valorCompra; 
    
    @JsonProperty ("prod_valor_venda")
    private Double valorVenda; 

	
	public ProdutoResource(String nomeProd, Double valorCompra, Double valorVenda) {
		this.nomeProd = nomeProd;
		this.valorCompra = valorCompra;
		this.valorVenda = valorVenda;
	}


	public String getNomeProd() {
		return nomeProd;
	}


	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}


	public Double getValorCompra() {
		return valorCompra;
	}


	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}


	public Double getValorVenda() {
		return valorVenda;
	}


	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}


	@Override
	public String toString() {
		return "ProdutoResource [nomeProd=" + nomeProd + ", valorCompra=" + valorCompra + ", valorVenda=" + valorVenda
				+ "]";
	}
}
