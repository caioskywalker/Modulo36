package br.com.cfarias.tests.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.*;

import br.com.cfarias.dao.interfaces.IClienteDao;
import br.com.cfarias.dao.ClienteDb2Dao;
import br.com.cfarias.entity.*;
import br.com.cfarias.exceptions.*;
import br.com.cfarias.dao.ClienteDao;
public class ClienteDaoDb2Test {
	
	private IClienteDao<Cliente> clienteDao;
	
	private IClienteDao<Cliente> clienteDB2Dao;
	
	
	private Random rd;
	
	public ClienteDaoDb2Test() {
		this.clienteDao = new ClienteDao();
		this.clienteDB2Dao = new ClienteDb2Dao();
		rd = new Random();
	}
	
	@AfterEach
	public void end() throws DaoException {
		Collection<Cliente> list1 = clienteDao.buscarTodos();
		excluir1(list1);
		
		Collection<Cliente> list2 = clienteDB2Dao.buscarTodos();
		excluir2(list2);
	}
	
	private void excluir1(Collection<Cliente> list) {
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	private void excluir2(Collection<Cliente> list) {
		list.forEach(cli -> {
			try {
				clienteDB2Dao.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DaoException, MaisDeUmRegistroException, TableException {
		Cliente cliente = criarCliente();
		Cliente cliente2 = criarCliente();
		
		clienteDao.cadastrar(cliente);
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		
		cliente.setId(null);
		Endereco end = getEndereco(cliente.getEnderecos());
		end.setId(null);// Foi necessário anular o id de endereço para cadastrar o mesmo cliente no DB2 p/ evitar "javax.persistence.PersistenceException"
		cliente.addEndereco(end);
		clienteDB2Dao.cadastrar(cliente);
		Cliente clienteConsultado2 = clienteDB2Dao.consultar(cliente.getId());
		assertNotNull(clienteConsultado2);
		
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
		Endereco end1 = new Endereco();
		

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
	
	private Endereco getEndereco(Set<Endereco> enderecos) {
		   return enderecos.stream()
		            .findFirst()
		            .orElse(null);
	}
}
