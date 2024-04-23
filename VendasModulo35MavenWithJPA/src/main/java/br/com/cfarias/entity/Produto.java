package br.com.cfarias.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import br.com.cfarias.dao.persistence.Persistente;

@Entity
@Table(name = "tb_produto")
public class Produto implements Persistente {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	@SequenceGenerator(name = "produto_seq", sequenceName = "sq_produto", initialValue = 1 , allocationSize = 1)
	private Long idProduto;

	@Column(name = "codigo_produto", nullable = false, length = 10, unique = true)
	private String codigo;
	
	@Column(name = "nome_produto", nullable = false, length = 100 )
	private String nome;
	
	@Column(name = "descricao_produto", nullable = false, length = 200)
	private String descricao;
	
	@Column(name = "valor_produto", nullable = false )
	private BigDecimal valor;
	
	
	public Produto() {
			}


	public Produto( String codigo, String nome, String descricao, BigDecimal valor) {
		super();
		
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}


	public Long getId() {
		return idProduto;
	}


	public void setId(Long idProduto) {
		this.idProduto = idProduto;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
	
	

}
