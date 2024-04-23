package br.com.cfarias.dao.generic;

import java.io.Serializable;

import br.com.cfarias.dao.persistence.Persistente;

public abstract class GenericDb3Dao <T extends Persistente , E extends Serializable> extends GenericDao<T, E> {

	public GenericDb3Dao(Class<T> persistenteClass) {
		super(persistenteClass, "Mysql1");
		// TODO Auto-generated constructor stub
	}

}
