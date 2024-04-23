package br.com.cfarias.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cfarias.dao.persistence.Persistente;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteMySql implements Persistente{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_seq")//Para MySql
	@SequenceGenerator(name="cliente_seq", sequenceName="sq_cliente", initialValue = 1, allocationSize = 1)
	private Long idCliente;
	
	@Column(name = "Nome" , nullable = false , length = 50)
	private String nome;
	
	@Column(name = "cpf", nullable = false, unique = true)
	private Long cpf;
	
	@Column(name = "telefone", nullable = false)
	private Long telefone;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Endereco> enderecos;
	

	public ClienteMySql( String nome, Long cpf, Long telefone, Set<Endereco> enderecos) {
		super();
		
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.enderecos = enderecos;
	}


	public Long getId() {
		return idCliente;
	}


	public void setId(Long idCliente) {
		this.idCliente = idCliente;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Long getCpf() {
		return cpf;
	}


	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}


	public Long getTelefone() {
		return telefone;
	}


	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}


	public Set<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public void addEndereco(Endereco endereco) {
		enderecos = new HashSet<Endereco>();
		enderecos.add(endereco);
	}

}
