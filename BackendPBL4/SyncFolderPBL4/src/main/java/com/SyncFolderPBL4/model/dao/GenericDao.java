package com.SyncFolderPBL4.model.dao;

import java.util.List;

public interface GenericDao<T> {
	T findOneById(Long id);
	List<T> findAll();
	Long save(T obj);
}
