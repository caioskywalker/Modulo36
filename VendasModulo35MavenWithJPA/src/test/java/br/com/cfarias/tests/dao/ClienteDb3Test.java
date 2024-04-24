package br.com.cfarias.tests.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.cfarias.dao.*;

import br.com.cfarias.dao.interfaces.IClienteDao;
import br.com.cfarias.entity.Cliente;
import br.com.cfarias.entity.ClienteMySql;
import br.com.cfarias.entity.Endereco;
import br.com.cfarias.exceptions.*;

public class ClienteDb3Test {


	private IClienteDao<Cliente> clienteDao;
	
	private IClienteDao<Cliente> clienteDao2;
	
	private IClienteDao<ClienteMySql> clienteDao3;
	
	private Random rd;
	
	public ClienteDb3Test() {
		this.clienteDao = new ClienteDao();
		this.clienteDao2 = new ClienteDb2Dao();
		this.clienteDao3 = new ClienteDb3Dao();
		
		rd = new Random();
		
	}
	
	
	@AfterEach
	public void end() throws DaoException {
		Collection<Cliente> list = clienteDao.buscarTodos();
		excluir(list, clienteDao);
		
		Collection<Cliente> list2 = clienteDao2.buscarTodos();
		excluir(list2, clienteDao2);
		
		Collection<ClienteMySql> list3 = clienteDao3.buscarTodos();
		excluir3(list3);
	}
	
	private void excluir(Collection<Cliente> list, IClienteDao<Cliente> clienteDao) {
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	private void excluir3(Collection<ClienteMySql> list) {
		list.forEach(cli -> {
			try {
				clienteDao3.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DaoException, MaisDeUmRegistroException, TableException {
		Cliente cliente = criarCliente();
		clienteDao.cadastrar(cliente);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		cliente.setId(null);
		Endereco end = getEndereco(cliente.getEnderecos());
		end.setId(null);
		cliente.addEndereco(end);
		clienteDao2.cadastrar(cliente);//ook
		
		Cliente clienteConsultado2 = clienteDao2.consultar(cliente.getId());
		assertNotNull(clienteConsultado2);
		
		ClienteMySql cliente2 = criarCliente2();
		clienteDao3.cadastrar(cliente2);
		
		ClienteMySql clienteConsultado3 = clienteDao3.consultar(cliente2.getId());
		assertNotNull(clienteConsultado3);
		
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(retorno.getId());
		assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente);
		
		Cliente clienteConsultado1 = clienteDao.consultar(retorno.getId());
		assertNull(clienteConsultado1);
	}
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNull(clienteConsultado);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clienteConsultado.setNome("Rodrigo Pires");
		clienteDao.alterar(clienteConsultado);
		
		Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
		assertNotNull(clienteAlterado);
		assertEquals("Rodrigo Pires", clienteAlterado.getNome());
		
		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(clienteAlterado.getId());
		assertNull(clienteConsultado);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente cliente1 = criarCliente();
		Cliente retorno1 = clienteDao.cadastrar(cliente1);
		assertNotNull(retorno1);
		
		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Collection<Cliente> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}
	
	
	
	private Cliente criarCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Rodrigo");
		cliente.setTelefone(33331060l);
		

		cliente.addEndereco(criarEnd());
		
		return cliente;
	}
	
	private Endereco criarEnd() {
		
		Endereco end1 = new Endereco();
		end1.setCidade("Rio de Janeiro");
		end1.setEstado("Rio das Ostras");
		end1.setNomeLogradouro("Rua Teste Mysql");
		end1.setNumero(15l);
		
		return end1;
		
	}
	
	private ClienteMySql criarCliente2() {
		ClienteMySql cliente = new ClienteMySql();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Rodrigo");
		cliente.setTelefone(33331060l);
		return cliente;
	}
	
	private Endereco getEndereco(Set<Endereco> enderecos) {
		   return enderecos.stream()
		            .findFirst()
		            .orElse(null);
	}
	
	
	
	
	
	

}
