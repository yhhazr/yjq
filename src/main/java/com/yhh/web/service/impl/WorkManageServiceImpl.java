package com.yhh.web.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhh.web.dao.DocumentDao;
import com.yhh.web.dao.PictureDao;
import com.yhh.web.dao.UserDao;
import com.yhh.web.dao.WorkDao;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.model.user.User;
import com.yhh.web.model.work.Work;
import com.yhh.web.service.WorkManageService;

@Service
public class WorkManageServiceImpl implements WorkManageService {

	@Resource
	private WorkDao workDao;
	
	@Resource
	private PictureDao picDao;
	
	@Resource
	private DocumentDao	docDao;
	
	@Resource
	private UserDao userDao;
	
	@Override
	public void addUser(Work work) {
		work.setCreateDate(new Date());
		User user = userDao.getUserById(work.getUserId());
		work.setUserName(user.getName());
		workDao.addUser(work);

	}

	@Override
	public PaginationResult<Work> getWorksByUserId(PageInfo pageInfo,int userId) {
		PaginationResult<Work> paginationResult = new PaginationResult<Work>(workDao.getUserWorksCount(userId), workDao.getWorksByUserId(pageInfo,userId));
		return paginationResult;
	}

	@Override
	public void deleteWork(int id) {
		Work work = workDao.getWorkById(id);
		workDao.deleteWork(work);
		picDao.deletePicByWorkId(id);
		docDao.deleteDocByWorkId(id);
	}

	@Override
	public Work getWorkById(int id) {
		return workDao.getWorkById(id);
	}

	@Override
	public void updateWork(Work work) {
		Work old = workDao.getWorkById(work.getId());
		work.setCreateDate(old.getCreateDate());
		work.setUserId(old.getUserId());
		work.setUserName(old.getUserName());
		workDao.updateWork(work);
	}

	@Override
	public PaginationResult<Work> getAllWorks(PageInfo pageInfo) {
		PaginationResult<Work> paginationResult = new PaginationResult<Work>(workDao.getAllWorksCount(), workDao.getAllWorks(pageInfo));
		return paginationResult;
	}

}
