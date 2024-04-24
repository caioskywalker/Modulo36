package br.com.cfarias.entity;

import javax.persistence.*;

import br.com.cfarias.dao.persistence.Persistente;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Persistente{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="endereco_seq")
	@SequenceGenerator(name="endereco_seq", sequenceName="sq_endereco", initialValue = 1, allocationSize = 1)
	private Long idEndereco;
	
	@Column(name = "numero", nullable = false, length = 50)
	private Long numero;
	
	@Column(name = "nome_logradouro", nullable = false, length = 100)
	private String nomeLogradouro;

	@Column(name = "cidade", nullable = false, length = 100)
	private String cidade;
	
	@Column(name = "estado", nullable = false, length = 50)
	private String estado;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente_fk",
			foreignKey = @ForeignKey(name = "fk_endereco_cliente"),
			referencedColumnName = "idCliente", nullable = true
			)
	private Cliente cliente;

	
	
	public Endereco(String nomeLogradouro, Long numero,   String estado, String cidade,
			Cliente cliente) {
		super();
		
		this.numero = numero;
		this.nomeLogradouro = nomeLogradouro;
		this.cidade = cidade;
		this.estado = estado;
		this.cliente = cliente;
	}

	public Endereco() {
		
	}
	

	public Long getId() {
		return idEndereco;
	}

	public void setId(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
	
	

}
