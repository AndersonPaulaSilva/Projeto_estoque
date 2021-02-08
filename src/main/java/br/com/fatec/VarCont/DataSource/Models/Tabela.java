package br.com.fatec.VarCont.DataSource.Models;

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
@Table(name = "tbl_tabela")
public class Tabela {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tabela_id")
	private long id;

	@Column(name = "tabela_data")
	private Date data;
	
	@Column(name = "tabela_tipo")
	private boolean tipo;

	@Column(name= "tabela_quantidade")
	private int qtd;
	
	@ManyToOne
	@JoinColumn (name = "prod_id", referencedColumnName = "prod_id")
	private Produto produto;

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

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}	
