package com.SyncFolderPBL4.model.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import com.SyncFolderPBL4.model.dao.IRoleDao;
import com.SyncFolderPBL4.model.entities.FileEntity;
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
	public void delete(UserRoleFileEntity role) {
		String sql =  "DELETE "
					+ "FROM user_role_file  "
					+ "WHERE file_id = ?0 ";
		setListParamsInHQL(
				session.createNativeQuery(sql,UserRoleFileEntity.class), role.getRoleIds().getFileId())
				.executeUpdate();
	}
	@Override
	public UserRoleFileEntity getRoleByRoleId(RoleID roleId) {
		String sql = " SELECT * "
					+ "FROM user_role_file "
					+ "WHERE file_id = ?0 and user_id = ?1 ";
		return setListParamsInHQL
				(session.createNativeQuery(sql, UserRoleFileEntity.class), roleId.getFileId(),roleId.getUserId())
				.uniqueResult();
	}
	@Override
	public void deleteRoleByPath(String path, int ownerId) {
		String sqlSubQuery = "SELECT f.id "
							+ "FROM file f "
							+ "WHERE f.path LIKE ?0 AND f.owner_id = ?1";
		String sql = "DELETE FROM UserRoleFileEntity urf WHERE urf.roleIds.fileId IN :ids";
		@SuppressWarnings("unchecked")
		List<Long> ids = session.createNativeQuery(sqlSubQuery)
				.setParameter(0, path)
				.setParameter(1, ownerId)
				.getResultList();
		
		session.createQuery(sql)
			  .setParameterList("ids", ids)
			  .executeUpdate();

												
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
