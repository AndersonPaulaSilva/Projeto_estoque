package br.com.fatec.VarCont.DataSource.Models;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "tbl_venda")
public class Venda implements Serializable {
    
	private static final long serialVersionUID = -2212373740869554918L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ven_id")
    long id;

    @Column(name = "ven_data")
    private Date data = new Date();

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn (name = "prod_id", referencedColumnName = "prod_id")
    private Produto produto;
    
    @Column (name = "ven_qtd")
    private int qtdVenda;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario idUsuario) {
		this.usuario = idUsuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto idProduto) {
		this.produto = idProduto;
	}

	public int getQtdVenda() {
		return qtdVenda;
	}

	public void setQtdVenda(int qtdVenda) {
		this.qtdVenda = qtdVenda;
	}
    
}
