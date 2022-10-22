package com.SyncFolderPBL4.model.dao.impl;

import com.SyncFolderPBL4.model.dao.IFileDao;
import com.SyncFolderPBL4.model.entities.FileEntity;

public class FileDao extends AbstractDao<FileEntity> implements IFileDao {

	public FileDao(Class<FileEntity> clazz) {
		super(clazz);
	}

}
