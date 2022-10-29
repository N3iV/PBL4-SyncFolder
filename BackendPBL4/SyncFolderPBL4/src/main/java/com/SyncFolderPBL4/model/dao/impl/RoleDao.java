package com.SyncFolderPBL4.model.dao.impl;

import com.SyncFolderPBL4.model.dao.IRoleDao;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public class RoleDao extends AbstractDao<UserRoleFileEntity> implements IRoleDao {

	public RoleDao(Class<UserRoleFileEntity> clazz) {
		super(clazz);
	}
	@Override
	public Integer save(UserRoleFileEntity obj) {
		return ((RoleID)session.save(obj)).getUserId();
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
