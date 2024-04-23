package br.com.cfarias.dao;


import br.com.cfarias.dao.generic.GenericDb1Dao;
import br.com.cfarias.dao.interfaces.IClienteDao;
import br.com.cfarias.entity.Cliente;

public class ClienteDao extends GenericDb1Dao<Cliente, Long> implements IClienteDao<Cliente> {

	public ClienteDao() {
		super(Cliente.class);
	}
	

	
	
	
}
