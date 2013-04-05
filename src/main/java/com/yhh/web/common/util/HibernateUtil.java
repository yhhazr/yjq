package com.yhh.web.common.util;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 
 * @author hai.yuan
 *
 */

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private static ServiceRegistry serviceRegistry; 
	
	private HibernateUtil(){
	}
	
	static{
		Configuration cfg = new AnnotationConfiguration();
		cfg.configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();        
	    sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void add(Object obj){
		Session s = null;
		Transaction tr = null;
		try{
			s = HibernateUtil.getSession();
			tr = s.beginTransaction();
			s.save(obj);
			tr.commit();
		}finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	public static void delete(Object obj){
		Session s = null;
		Transaction tr = null;
		try{
			s = HibernateUtil.getSession();
			tr = s.beginTransaction();
			s.delete(obj);
			tr.commit();
		}finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	public static void update(Object obj){
		Session s = null;
		Transaction tr = null;
		try{
			s = HibernateUtil.getSession();
			tr = s.beginTransaction();
			s.update(obj);
			tr.commit();
		}finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	public static Object get(Class clazz, Serializable id){
		Session s = null;
		try{
			s = HibernateUtil.getSession();
			Object obj = s.get(clazz, id);
			return obj;
		}finally{
			if(s != null){
				s.close();
			}
		}
	}
	
	
}
