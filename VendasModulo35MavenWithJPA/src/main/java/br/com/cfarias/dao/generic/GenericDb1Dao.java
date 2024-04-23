package br.com.cfarias.dao.generic;

import java.io.Serializable;


import br.com.cfarias.dao.persistence.Persistente;
import br.com.cfarias.entity.Venda;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;

public abstract class GenericDb1Dao<T extends Persistente, E extends Serializable> extends GenericDao<T,E> {

	public GenericDb1Dao(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre1");
		
	}

	
	
	

}
