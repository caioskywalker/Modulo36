package br.com.cfarias.dao;


import br.com.cfarias.dao.generic.GenericDb1Dao;
import br.com.cfarias.dao.interfaces.IProdutoDao;
import br.com.cfarias.entity.Produto;

public class ProdutoDao extends GenericDb1Dao<Produto, Long> implements IProdutoDao<Produto> {
	
	public ProdutoDao() {
		super(Produto.class);
	}

}
