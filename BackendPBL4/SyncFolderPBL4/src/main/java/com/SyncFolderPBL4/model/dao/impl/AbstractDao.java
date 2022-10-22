package com.SyncFolderPBL4.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.SyncFolderPBL4.model.dao.GenericDao;
import com.SyncFolderPBL4.utils.HibernateUtils;

public abstract class AbstractDao<T> implements GenericDao<T> {
	private Class<T> clazz;
	protected Session session;

	protected AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void setSession(Session s) {
		this.session = s;
	}

	@Override
	public Session getSession() {
		return session;
	}

	@Override
	public T findOneById(Integer id) {
		return session.get(clazz, id);
	}

	@Override
	public List<T> findAll() {

		return null;
	}

	@Override
	public Integer save(T obj) {
		return (Integer)session.save(obj);
	}

	protected Query<T> setListParamsInHQL(Query<T> query, Object... params) {
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query;

	}

}