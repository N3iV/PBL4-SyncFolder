package com.SyncFolderPBL4.model.dao.impl;

import java.math.BigInteger;
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
	public List<FileEntity> getFilesByOwnerIdAndNodeId(int ownerId, int nodeId,String path, int page) {
		String hql =  "FROM FileEntity f "
					+ "where f.ownerId = ?0 and f.nodeId = ?1 and f.path LIKE ?2 "
					+ "ORDER BY f.type.id ASC , f.id ASC";
		Query<FileEntity> query = setListParamsInHQL(session.createQuery(hql, FileEntity.class), ownerId, nodeId, path)
									.setFirstResult((page - 1) * SystemConstant.MAX_PAGE_SIZE)
									.setMaxResults(SystemConstant.MAX_PAGE_SIZE);
		return query.getResultList();
	}

	@Override
	public Long countFilesByOwnerIdAndNodeId(int ownerId, int nodeId) {
		String hql = "select count(f) from FileEntity f where f.ownerId = ?0 and f.nodeId = ?1";
		Query<Long> query = session.createQuery(hql, Long.class)
							.setParameter(0, ownerId)
							.setParameter(1, nodeId);
		return query.uniqueResult();
	}

	@Override
	public Long countFiles(int ownerId, int nodeId) {
		String hql = "SELECT COUNT(*) FROM FileEntity f WHERE f.nodeId = ?0 and f.ownerId = ?1";
		Query<Long> query = session.createQuery(hql, Long.class)
							.setParameter(0, nodeId)
							.setParameter(1, ownerId);
		return query.uniqueResult();

	}

	@Override
	public List<FileEntity> getAllFiles(int page, int ownerId, int nodeId) {
		String hql = "FROM FileEntity f WHERE f.nodeId = ?0 and f.ownerId = ?1";
		Query<FileEntity> query = setListParamsInHQL(session.createQuery(hql, FileEntity.class), nodeId, ownerId)
									.setFirstResult((page - 1) * SystemConstant.MAX_PAGE_SIZE)
									.setMaxResults(SystemConstant.MAX_PAGE_SIZE);
		return query.getResultList();
	}

	@Override
	public FileEntity findOneByOwnerIdAndNodeId(int ownerId, int nodeId) {
		String hql = "FROM FileEntity f WHERE f.ownerId = ?0 AND f.nodeId = ?1";
		Query<FileEntity> query = setListParamsInHQL(session.createQuery(hql, FileEntity.class), ownerId, nodeId);
		return query.uniqueResult();
	}

	@Override
	public Long countSharedFiles(int userId) {
		String sql = "SELECT count(*) "
					+ "FROM file f "
					+ "JOIN user_role_file urf "
					+ "ON f.id = urf.file_id "
					+ "WHERE urf.user_id = ?0 AND f.owner_id <> ?1";
		System.out.println(sql);
		Query query = session.createNativeQuery(sql)
				.setParameter(0, userId)
				.setParameter(1, userId);
		return ((BigInteger)query.uniqueResult()).longValue();
	}

	@Override
	public List<FileEntity> getSharedFiles(int userId, int page) {
		String sql = "SELECT f.* "
				+ "FROM file f "
				+ "JOIN user_role_file urf "
				+ "ON f.id = urf.file_id "
				+ "WHERE urf.user_id = ?0 AND f.owner_id <> ?1 "
				+ "ORDER BY f.type_id ASC , f.id ASC ";
		Query<FileEntity> query = session.createNativeQuery(sql, FileEntity.class)
							.setParameter(0, userId)
							.setParameter(1, userId)
							.setFirstResult((page -1) * SystemConstant.MAX_PAGE_SIZE)
							.setMaxResults(SystemConstant.MAX_PAGE_SIZE);
		return query.getResultList();
		}
	public void deleteFileByPath(String path, int ownerId) {
		String sql = "DELETE FROM file WHERE path LIKE ?0 AND owner_id = ?1";
		Query<FileEntity> query = session.createNativeQuery(sql, FileEntity.class)
				.setParameter(0, path)
				.setParameter(1, ownerId);
		query.executeUpdate();
	}

}
