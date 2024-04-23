package br.com.cfarias.dao.interfaces;

import br.com.cfarias.dao.generic.IGenericDao;
import br.com.cfarias.dao.persistence.Persistente;
import br.com.cfarias.entity.Produto;

public interface IProdutoDao<T extends Persistente> extends IGenericDao<Produto, Long>{

	
	
}
