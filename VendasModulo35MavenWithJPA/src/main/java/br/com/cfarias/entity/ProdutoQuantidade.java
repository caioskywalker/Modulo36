package br.com.cfarias.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import br.com.cfarias.dao.persistence.Persistente;

@Entity
@Table(name = "tb_produto_quantidade")
public class ProdutoQuantidade implements Persistente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prod_qtd_seq")
	@SequenceGenerator(name="prod_qtd_seq", sequenceName="sq_prod_qtd", initialValue = 1, allocationSize = 1)
	private Long idProdutoQuantidade;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Produto produto;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_venda_fk", 
		foreignKey = @ForeignKey(name = "fk_prod_qtd_venda"), 
		referencedColumnName = "idVenda", nullable = false)
	private Venda venda;
	
	

	public Long getId() {
		return idProdutoQuantidade;
	}

	public void setId(Long idProdutoQuantidade) {
		this.idProdutoQuantidade = idProdutoQuantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public ProdutoQuantidade(){
		this.quantidade = 0;
		this.valorTotal = BigDecimal.ZERO;
	}
	
	public void adicionar(Integer quantidade) {
		this.quantidade += quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		BigDecimal novoTotal = this.valorTotal.add(novoValor);
		this.valorTotal = novoTotal;
	}
	
	public void remover(Integer quantidade) {
		this.quantidade -= quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		this.valorTotal = this.valorTotal.subtract(novoValor);
	}
	
	
	
	
	
	

}
