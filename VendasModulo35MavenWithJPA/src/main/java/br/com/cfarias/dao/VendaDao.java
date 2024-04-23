package br.com.cfarias.dao;



import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.cfarias.dao.generic.GenericDb1Dao;

import br.com.cfarias.dao.interfaces.IVendaDao;

import br.com.cfarias.entity.*;
import br.com.cfarias.exceptions.*;



public class VendaDao extends GenericDb1Dao<Venda, Long> implements IVendaDao<Venda>{

	public VendaDao() {
		super(Venda.class);
	}
	
	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DaoException {
		super.alterar(venda);
	}

	@Override
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DaoException {
		super.alterar(venda);
	}

	@Override
	public void excluir(Venda entity) throws DaoException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public Venda cadastrar(Venda entity) throws TipoChaveNaoEncontradaException, DaoException {
		try {
			openConnection();
			entity.getProdutosQuantidade().forEach(prod -> {
				Produto prodJpa = entityManager.merge(prod.getProduto());
				prod.setProduto(prodJpa);
			});
			Cliente cliente = entityManager.merge(entity.getCliente());
			entity.setCliente(cliente);
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			closeConnection();
			return entity;
		} catch (Exception e) {
			throw new DaoException("ERRO SALVANDO VENDA ", e);
		}
		
	}

	@Override
	public Venda consultarComCollection(Long id) {
		openConnection();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Venda> query = builder.createQuery(Venda.class);
		Root<Venda> root = query.from(Venda.class);
		root.fetch("cliente");
		root.fetch("produtos");
		query.select(root).where(builder.equal(root.get("id"), id));
		TypedQuery<Venda> tpQuery = 
				entityManager.createQuery(query);
		Venda venda = tpQuery.getSingleResult();   
		closeConnection();
		return venda;
	}

	
	
}
