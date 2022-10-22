package com.SyncFolderPBL4.model.service;

public interface GenericService<T> {
	T findOne(Integer id);
	T findOne(T obj);
	Integer save(T obj);
}
