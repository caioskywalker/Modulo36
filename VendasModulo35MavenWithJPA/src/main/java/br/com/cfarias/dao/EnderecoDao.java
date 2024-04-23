package br.com.cfarias.dao;


import br.com.cfarias.dao.generic.GenericDb1Dao;
import br.com.cfarias.dao.interfaces.IEnderecoDao;
import br.com.cfarias.entity.Endereco;


public class EnderecoDao extends GenericDb1Dao<Endereco, Long> implements IEnderecoDao<Endereco> {
	
	public EnderecoDao() {
		super(Endereco.class );
	}

}
