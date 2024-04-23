package br.com.cfarias.dao.interfaces;

import br.com.cfarias.exceptions.*;

import br.com.cfarias.dao.generic.IGenericDao;
import br.com.cfarias.dao.persistence.Persistente;
import br.com.cfarias.entity.Venda;

public interface IVendaDao<T extends Persistente> extends IGenericDao<Venda, Long> {

	
public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DaoException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DaoException;
	
	/**
	 * Usando este método para evitar a exception org.hibernate.LazyInitializationException
	 * Ele busca todos os dados de objetos que tenham colletion pois a mesma por default é lazy
	 * Mas você pode configurar a propriedade da collection como fetch = FetchType.EAGER na anotação @OneToMany e usar o consultar genérico normal
	 * 
	 * OBS: Não é uma boa prática utiliar FetchType.EAGER pois ele sempre irá trazer todos os objetos da collection
	 * mesmo sem precisar utilizar.
	 * 
	 * 
	 * @see Venda produtos
	 * 
	 * @param id
	 * @return
	 */
	public Venda consultarComCollection(Long id);
}
