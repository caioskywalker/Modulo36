package br.com.cfarias.dao.interfaces;

import br.com.cfarias.dao.generic.IGenericDao;
import br.com.cfarias.dao.persistence.Persistente;


public interface IClienteDao<T extends Persistente> extends IGenericDao<T, Long> {

}
