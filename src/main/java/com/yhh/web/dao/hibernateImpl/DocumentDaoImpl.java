package com.yhh.web.dao.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhh.web.dao.DocumentDao;
import com.yhh.web.model.file.Document;
import com.yhh.web.model.file.Picture;

@Repository
@Transactional
public class DocumentDaoImpl implements DocumentDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void addDoc(Document doc) {
		Session session = sessionFactory.getCurrentSession();
		session.save(doc);

	}

	@Override
	public List<Document> getAllDoc(int workId) {
		List<Document> docList = new ArrayList<Document>();
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Document.class);
		cri.addOrder(Order.desc("createDate"));
		cri.add(Restrictions.eq("workId", workId));
		docList = cri.list();
		return docList;
	}

	@Override
	public Document getDocById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Document)session.get(Document.class, id);
	}

	@Override
	public void deleteDoc(Document doc) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(doc);
	}

	@Override
	public void deleteDocByWorkId(int workId) {
		Session session = sessionFactory.getCurrentSession();
		String hql="delete Document where workId=:workId";
		Query query=session.createQuery(hql);
		query.setParameter("workId", workId);
		query.executeUpdate();
	}

}
