package com.SyncFolderPBL4.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.SyncFolderPBL4.model.dao.GenericDao;
import com.SyncFolderPBL4.utils.HibernateUtils;

public class AbstractDao<T> implements GenericDao<T> {
	private Class<T> clazz;
	protected static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	protected static Session session;

	@Override
	public T findOneById(Long id) {
		session = sessionFactory.openSession();
		return session.get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return null;
	}

	@Override
	public Long save(T obj) {
		return null;
	}

	protected Query<T> setListParamsInHQL(Query<T> query, Object... params) {
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query;

	}

}
