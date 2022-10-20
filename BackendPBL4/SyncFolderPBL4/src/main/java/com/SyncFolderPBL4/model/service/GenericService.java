package com.SyncFolderPBL4.model.service;

public interface GenericService<T> {
	T findOne(Long id);
	T findOne(T obj);
	Long save(T obj);
}
