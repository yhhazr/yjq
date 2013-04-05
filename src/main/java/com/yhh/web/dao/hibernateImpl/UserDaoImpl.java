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
import com.yhh.web.dao.UserDao;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.user.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public User getUserByName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", userName));
		User user = (User)cri.uniqueResult();
		return user;
	}

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		
	}

	@Override
	public List<User> getAllUsers(PageInfo pageInfo) {
		List<User> userList = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(User.class);
		cri.setFirstResult(pageInfo.getStartRow());
		cri.setMaxResults(pageInfo.getPageSize());
		cri.addOrder(Order.desc("createDate"));
		userList = cri.list();
		return userList;
	}

	@Override
	public int getAllUsersCount(){
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(User.class);
		count = cri.list().size();
		return count;
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (User)session.get(User.class, id);
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

}
