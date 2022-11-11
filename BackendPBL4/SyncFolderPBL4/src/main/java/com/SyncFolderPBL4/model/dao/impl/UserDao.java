package com.SyncFolderPBL4.model.dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import com.SyncFolderPBL4.constant.SystemConstant;
import com.SyncFolderPBL4.model.dao.IUserDao;
import com.SyncFolderPBL4.model.entities.UserEntity;

public class UserDao extends AbstractDao<UserEntity> implements IUserDao {

	public UserDao(Class<UserEntity> clazz) {
		super(clazz);
		
	}
	@Override
	public Boolean checkEmail(String email) {
		UserEntity user = setListParamsInHQL(session.createNamedQuery(UserEntity.Q_GET_EMAIL, UserEntity.class), 
							email)
							.uniqueResult();
		return (user == null) ? true : false;
	}
	@Override
	public UserEntity findOneByEmailPassword(String email, String password) {
		UserEntity user = setListParamsInHQL(session.createNamedQuery(UserEntity.Q_FIND_ONE_BY_EMAIL_PASSWORD, UserEntity.class), 
				email,password)
				.uniqueResult();
		return user;
	}
	@Override
	public Long countAllUser(int userId) {
		String hql = "select count(u) FROM UserEntity u WHERE u.id <> ?0";
		Query<Long> query = session.createQuery(hql,Long.class) 
								   .setParameter(0, userId);
		return query.uniqueResult();
	}
	@Override
	public List<UserEntity> getAllUser(int userId, int page) {
		String hql = "FROM UserEntity u WHERE u.id <> ?0";
		Query<UserEntity> query = session.createQuery(hql,UserEntity.class) 
									.setParameter(0, userId)
									.setFirstResult((page-1) * SystemConstant.MAX_PAGE_SIZE)
									.setMaxResults(SystemConstant.MAX_PAGE_SIZE);
		return query.getResultList();
	}
}
