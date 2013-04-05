package com.yhh.web.dao.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.yhh.web.dao.WorkDao;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.work.Work;

@Repository
@Transactional
public class WorkDaoImpl implements WorkDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public List<Work> getWorksByUserId(PageInfo pageInfo,int userId) {
		List<Work> workList = new ArrayList<Work>();
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Work.class);
		cri.add(Restrictions.eq("userId", userId));
		cri.setFirstResult(pageInfo.getStartRow());
		cri.setMaxResults(pageInfo.getPageSize());
		cri.addOrder(Order.desc("createDate"));
		workList = cri.list();
		return workList;
	}

	@Override
	public void addUser(Work work) {
		Session session = sessionFactory.getCurrentSession();
		session.save(work);
	}

	@Override
	public int getUserWorksCount(int userId) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Work.class);
		cri.add(Restrictions.eq("userId", userId));
		count = cri.list().size();
		return count;
	}

	@Override
	public Work getWorkById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Work work = (Work)session.get(Work.class, id);
		return work;
	}

	@Override
	public void deleteWork(Work work) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(work);
	}

	@Override
	public void updateWork(Work work) {
		Session session = sessionFactory.getCurrentSession();
		session.update(work);
	}

	@Override
	public List<Work> getAllWorks(PageInfo pageInfo) {
		List<Work> workList = new ArrayList<Work>();
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Work.class);
		cri.setFirstResult(pageInfo.getStartRow());
		cri.setMaxResults(pageInfo.getPageSize());
		cri.addOrder(Order.desc("createDate"));
		workList = cri.list();
		return workList;
	}

	@Override
	public int getAllWorksCount() {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Work.class);
		count = cri.list().size();
		return count;
	}

}
