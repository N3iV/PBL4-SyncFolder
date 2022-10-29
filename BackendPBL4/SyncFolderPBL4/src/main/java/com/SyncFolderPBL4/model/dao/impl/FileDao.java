package com.SyncFolderPBL4.model.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import com.SyncFolderPBL4.constant.SystemConstant;
import com.SyncFolderPBL4.model.dao.IFileDao;
import com.SyncFolderPBL4.model.entities.FileEntity;

public class FileDao extends AbstractDao<FileEntity> implements IFileDao {

	public FileDao(Class<FileEntity> clazz) {
		super(clazz);
	}

	@Override
	public List<FileEntity> getFilesByOwnerIdAndNodeId(int ownerId, int nodeId, int page) {
		String hql = "from FileEntity f where f.ownerId = ?0 and f.nodeId = ?1";
		return setListParamsInHQL(
				session.createQuery(hql,FileEntity.class),
					ownerId,nodeId)
				.setFirstResult((page-1)*SystemConstant.MAX_PAGE_SIZE)
				.setMaxResults(SystemConstant.MAX_PAGE_SIZE)
				.getResultList();
	}

	@Override
	public Long countFilesByOwnerIdAndNodeId(int ownerId, int nodeId) {
		String hql = "select count(f) from FileEntity f where f.ownerId = ?0 and f.nodeId = ?1";
		Query<Long> query = session.createQuery(hql,Long.class)
						.setParameter(0, ownerId)
						.setParameter(1, nodeId);		
		return query.uniqueResult();
	}

	

}
