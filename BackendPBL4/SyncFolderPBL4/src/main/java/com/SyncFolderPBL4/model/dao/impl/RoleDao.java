package com.SyncFolderPBL4.model.dao.impl;

import org.hibernate.query.Query;

import com.SyncFolderPBL4.model.dao.IRoleDao;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;
import com.SyncFolderPBL4.utils.HibernateUtils;

public class RoleDao extends AbstractDao<UserRoleFileEntity> implements IRoleDao {

	public RoleDao(Class<UserRoleFileEntity> clazz) {
		super(clazz);
	}
	@Override
	public Integer save(UserRoleFileEntity obj) {
		return ((RoleID)session.save(obj)).getUserId();
	}
	
	@Override
	public void delete(UserRoleFileEntity file) {
		session.delete(file);
	}
	@Override
	public UserRoleFileEntity getRoleByFileId(int fileId) {
		String sql = "SELECT * FROM pbl4.user_role_file WHERE file_id = ?0";
		Query<UserRoleFileEntity> query = setListParamsInHQL(session.createNativeQuery(sql, UserRoleFileEntity.class), fileId);
		return query.uniqueResult();
	}
	@Override
	public void deleteRoleByPath(String path, int fileId, int ownerId) {
		String sql =  "DELETE " 
					+ "FROM user_role_file urf "
					+ "WHERE "
					+ "EXISTS "
					+ "(SELECT * "
						+ "FROM file f "
						+ "WHERE f.path LIKE ?0 "
						+ "AND f.id = urf.file_id "
						+ "AND f.owner_id = urf.user_id)";
		Query<UserRoleFileEntity> query = session.createNativeQuery(sql, UserRoleFileEntity.class)
											.setParameter(0, path);
		query.executeUpdate();
	}
	
	
//	@Override
//	public List<UserRoleFileEntity> getFilesByUserIdAndNodeId(int ownerId, int nodeId,int page) {
//		return setListParamsInHQL(session.createQuery("SELECT a FROM UserRoleFileEntity a "
//				+ "LEFT JOIN FETCH a.file "
//				+ "where a.ownerId = ?0 and a.file.nodeId = ?1", UserRoleFileEntity.class), ownerId, nodeId)
//				.setFirstResult((page-1)*SystemConstant.MAX_PAGE_SIZE)
//				.setMaxResults(SystemConstant.MAX_PAGE_SIZE)
//				.getResultList();
//	}
//	@Override
//	public BigInteger countFilesByUserIdAndNodeId(int ownerId, int nodeId) {	
//		String sql = "SELECT count(*) "
//					+ "FROM user_role_file "
//					+ "INNER JOIN file ON user_role_file.file_id = file.id "
//					+ "where user_role_file.owner_id = ?0 and file.node_id = ?1";
//		Query query = session.createNativeQuery(sql)
//				.setParameter(0, ownerId)
//				.setParameter(1, nodeId);
//		return (BigInteger) query.uniqueResult();
//		
//	}

}
