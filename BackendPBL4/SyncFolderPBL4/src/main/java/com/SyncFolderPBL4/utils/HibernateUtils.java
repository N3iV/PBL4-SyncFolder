package com.SyncFolderPBL4.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.SyncFolderPBL4.model.dao.impl.AbstractDao;

public class HibernateUtils {

  private static final SessionFactory sessionFactory = buildSessionFactory();

  // Hibernate 5:
  private static SessionFactory buildSessionFactory() {
      try {
          ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
                  .configure("hibernate.cfg.xml").build();      
          Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
          return metadata.getSessionFactoryBuilder().build();
      } catch (Throwable ex) {
          System.err.println("Initial SessionFactory creation failed." + ex);
          throw new ExceptionInInitializerError(ex);
      }
  }

  public static SessionFactory getSessionFactory() {
      return sessionFactory;
  }

  public static void shutdown() {
      getSessionFactory().close();
  }
  
  public static void setListSession(Object...objects)
  {
	  for(Object i : objects)
	  {
		  if (i instanceof AbstractDao)
		  {
			  ((AbstractDao) i).setSession(HibernateUtils.getSessionFactory().getCurrentSession());
		  }
	  }
  }
  public static void startTrans(Object...objects)
  {
	  HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
	  HibernateUtils.setListSession(objects);
  }
  public static void commitTrans()
  {
	  HibernateUtils.getSessionFactory().getCurrentSession().getTransaction().commit();
  }
}