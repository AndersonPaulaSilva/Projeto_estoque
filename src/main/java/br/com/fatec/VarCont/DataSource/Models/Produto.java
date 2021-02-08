package br.com.fatec.VarCont.DataSource.Models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "tbl_produto")
public class Produto implements Serializable {
    
	private static final long serialVersionUID = -1190609681859950392L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    private long id;
    
    @Column(name = "prod_nome")
    @NotEmpty(message = "O nome do produto não pode ser vazio")
    private String nomeProd;
    
    @Column(name = "prod_valor_compra")
    @NotNull(message = "O valor de compra não pode ser vazio")
    private double valorCompra;
    
    @Column(name = "prod_valor_venda")
    @NotNull(message = "O valor de venda não pode ser vazio")
    private double valorVenda;
    
	public long getId() {
		return id;
	}

	public void setId(long idProduto) {
		this.id = idProduto;
	}

	public String getNomeProd() {
		return nomeProd;
	}

	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
 
}
