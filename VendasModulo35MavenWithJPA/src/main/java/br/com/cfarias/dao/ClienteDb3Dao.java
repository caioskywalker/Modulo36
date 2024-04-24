package br.com.cfarias.dao;

import br.com.cfarias.dao.generic.GenericDb3Dao;
import br.com.cfarias.dao.interfaces.IClienteDao;
import br.com.cfarias.entity.ClienteMySql;

public class ClienteDb3Dao extends GenericDb3Dao<ClienteMySql, Long> implements IClienteDao<ClienteMySql> {

	public ClienteDb3Dao() {
		super(ClienteMySql.class);
		
	}

}
