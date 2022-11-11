package com.SyncFolderPBL4.model.dao;

import java.util.List;

import org.hibernate.Session;

public interface GenericDao<T> {
	T findOneById(Integer id);
	List<T> findAll();
	Integer save(T obj);
	Session getSession();
	void setSession(Session s);
	void delete(T obj);
}
