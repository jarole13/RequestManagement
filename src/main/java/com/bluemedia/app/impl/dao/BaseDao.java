package com.bluemedia.app.impl.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.jodah.typetools.TypeResolver;

public abstract class BaseDao<T_ENTITY> {

	private Class<T_ENTITY> entityClass;

	@PersistenceContext(name = "RequestManagement")
	private EntityManager em;

	public T_ENTITY store(T_ENTITY entity) {
		return em.merge(entity);
	}

	public T_ENTITY findById(final Long id) {
		if(id==null){
			return null;
		}
		return em.find(entityClass, id);
	}

	@PostConstruct
	public void postConstruct() {
		entityClass = (Class<T_ENTITY>) TypeResolver.resolveRawArgument(BaseDao.class, getClass());
		
	}
	
	protected EntityManager getEntityManager(){
		return em;
	}
}
