package br.com.cfarias.service;

import java.io.Serializable;
import java.util.Collection;

import br.com.cfarias.dao.persistence.Persistente;
import br.com.cfarias.exceptions.DaoException;
import br.com.cfarias.exceptions.MaisDeUmRegistroException;
import br.com.cfarias.exceptions.TableException;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;

public interface IGenericService<T extends Persistente, E extends Serializable> {

	
	public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DaoException;
	public void excluir(T entity) throws DaoException;
	public T alterar(T entity) throws TipoChaveNaoEncontradaException, DaoException;
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DaoException;
	public Collection<T> buscarTodos() throws DaoException;
	
	
	
}
