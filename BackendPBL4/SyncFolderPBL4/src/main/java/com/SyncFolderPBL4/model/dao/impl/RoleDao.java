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
	@Override
	public UserRoleFileEntity getParentRole(int userId, String path) {
		String sql = "SELECT * "
					+"FROM user_role_file "
					+"WHERE user_id = ?0 "
					+ "AND file_id IN ( "
						+ "SELECT id "
						+ "FROM file "
						+ "WHERE path LIKE ?1 "
					+ ")";
		UserRoleFileEntity role = setListParamsInHQL(session.createNativeQuery(sql, UserRoleFileEntity.class), userId,path)
									.uniqueResult();
		return role;
	}
	
	

}
