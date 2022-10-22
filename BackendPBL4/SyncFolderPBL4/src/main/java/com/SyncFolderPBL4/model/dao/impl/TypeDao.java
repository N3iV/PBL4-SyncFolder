package com.SyncFolderPBL4.model.dao.impl;

import com.SyncFolderPBL4.model.dao.ITypeDao;
import com.SyncFolderPBL4.model.entities.TypeEntity;

public class TypeDao extends AbstractDao<TypeEntity> implements ITypeDao {
	
	public TypeDao(Class<TypeEntity> clazz) {
		super(clazz);
	}
	
	
	
}
