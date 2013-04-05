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

import com.yhh.web.dao.PictureDao;
import com.yhh.web.model.file.Picture;

@Repository
@Transactional
public class PictureDaoImpl implements PictureDao {
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void addPic(Picture pic) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pic);
	}

	@Override
	public List<Picture> getAllPic(int workId) {
		List<Picture> picList = new ArrayList<Picture>();
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Picture.class);
		cri.add(Restrictions.eq("workId", workId));
		cri.addOrder(Order.desc("createDate"));
		picList = cri.list();
		return picList;
	}

	@Override
	public Picture getPicById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Picture pic = (Picture)session.get(Picture.class, id);
		return pic;
	}

	@Override
	public void deletePic(Picture pic) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(pic);
	}

	@Override
	public void deletePicByWorkId(int workId) {
		Session session = sessionFactory.getCurrentSession();
		String hql="delete Picture where workId=:workId";
		Query query=session.createQuery(hql);
		query.setParameter("workId", workId);
		query.executeUpdate();
		
	}

}
