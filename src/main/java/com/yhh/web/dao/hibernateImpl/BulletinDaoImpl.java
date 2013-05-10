package com.yhh.web.dao.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhh.web.dao.BulletinDao;
import com.yhh.web.model.bulletin.Bulletin;
import com.yhh.web.model.pagination.PageInfo;

@Repository
@Transactional
public class BulletinDaoImpl implements BulletinDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void addBulletin(Bulletin bul) {
		Session session = sessionFactory.getCurrentSession();
		session.save(bul);
	}

	@Override
	public List<Bulletin> getAllBulletins(PageInfo pageInfo) {
		List<Bulletin> bulList = new ArrayList<Bulletin>();
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Bulletin.class);
		cri.setFirstResult(pageInfo.getStartRow());
		cri.setMaxResults(pageInfo.getPageSize());
		cri.addOrder(Order.desc("createDate"));
		bulList = cri.list();
		return bulList;
	}

	@Override
	public int getAllBulletinsCount() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Bulletin.class);
		count = cri.list().size();
		return count;
	}

	@Override
	public Bulletin getBulletinById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Bulletin bul = (Bulletin)session.get(Bulletin.class, id);
		return bul;
	}

	@Override
	public void updateBulletin(Bulletin bul) {
		Session session = sessionFactory.getCurrentSession();
		session.update(bul);
	}

	@Override
	public void deleteBulletin(Bulletin bul) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(bul);
	}

}
