package br.com.fatec.VarCont.DataSource.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_lote")
public class Lote implements Serializable {

	private static final long serialVersionUID = 3299433036351463860L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lote_id")
	private long id;

	@Column(name = "lote_data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
	private Produto produto;

	@Column(name = "lote_qtdCompra")
	private int qtdCompra;

	@Column(name = "lote_qtdTotal")
	private int qtdTotal;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto idProduto) {
		this.produto = idProduto;
	}

	public int getQtdCompra() {
		return qtdCompra;
	}

	public void setQtdCompra(int qtdCompra) {
		this.qtdCompra = qtdCompra;
	}

	public int getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(int qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
