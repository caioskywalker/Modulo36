package br.com.cfarias.dao.interfaces;

import br.com.cfarias.dao.generic.IGenericDao;
import br.com.cfarias.dao.persistence.Persistente;
import br.com.cfarias.entity.Endereco;

public interface IEnderecoDao<T extends Persistente> extends IGenericDao<Endereco,Long> {

}
