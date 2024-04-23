package br.com.cfarias.tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import java.util.Random;

import br.com.cfarias.entity.*;
import br.com.cfarias.exceptions.DaoException;
import br.com.cfarias.exceptions.MaisDeUmRegistroException;
import br.com.cfarias.exceptions.TableException;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;
import br.com.cfarias.dao.*;

import org.junit.jupiter.api.*;

import br.com.cfarias.dao.interfaces.IClienteDao;
import br.com.cfarias.dao.interfaces.IEnderecoDao;

public class DaoTests {

	private IClienteDao<Cliente> clienteDao;
	private IEnderecoDao<Endereco> enderecoDao;

	private Random rd;

	public DaoTests() {
		this.clienteDao = new ClienteDao();
		this.enderecoDao = new EnderecoDao();
		rd = new Random();
	}

	@AfterEach
	public void end() throws DaoException {
		Collection<Cliente> list = clienteDao.buscarTodos();
		Collection<Endereco> listEnd = enderecoDao.buscarTodos();
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		listEnd.forEach(end -> {
			try {
				enderecoDao.excluir(end);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void pesquisarCliente()
			throws TipoChaveNaoEncontradaException, DaoException, MaisDeUmRegistroException, TableException {
		Cliente cliente = criarCliente();
		clienteDao.cadastrar(cliente);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);

	}

	@Test
	public void salvarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
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
	public void excluirCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
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
	public void alterarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DaoException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);

		clienteConsultado.setNome("Caio Felipe");
		clienteDao.alterar(clienteConsultado);

		Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
		assertNotNull(clienteAlterado);
		assertEquals("Caio Felipe", clienteAlterado.getNome());

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
		end1.setCidade("Rio de Janeiro");
		end1.setEstado("Rio das Ostras");
		end1.setNomeLogradouro("Rua Getulio Vargas");
		end1.setNumero(15l);

		cliente.addEndereco(end1);
		;
		return cliente;
	}

}
