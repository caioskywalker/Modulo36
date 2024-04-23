package br.com.cfarias.dao.generic;

import java.io.Serializable;

import br.com.cfarias.dao.persistence.Persistente;

public abstract class GenericDb2Dao<T extends Persistente , E extends Serializable> extends GenericDao<T, E> {

	public GenericDb2Dao(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre2");
		// TODO Auto-generated constructor stub
	}

	
	
}
