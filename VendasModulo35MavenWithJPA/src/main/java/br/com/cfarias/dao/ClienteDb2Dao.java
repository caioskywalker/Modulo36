package br.com.cfarias.dao;


import br.com.cfarias.dao.generic.GenericDb2Dao;
import br.com.cfarias.dao.interfaces.IClienteDao;
import br.com.cfarias.entity.Cliente;

public class ClienteDb2Dao extends GenericDb2Dao<Cliente, Long> implements IClienteDao<Cliente>{

	public ClienteDb2Dao() {
		super(Cliente.class);
		// TODO Auto-generated constructor stub
	}
	
	

}
