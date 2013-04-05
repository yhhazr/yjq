package com.yhh.web.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhh.web.common.util.MD5Util;
import com.yhh.web.dao.UserDao;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.model.user.User;
import com.yhh.web.service.UserManageService;

@Service
public class UserManageServiceImpl implements UserManageService {

	@Resource
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		user.setPassword(MD5Util.getMD5String(user.getPassword()));
		user.setCreateDate(new Date());
		userDao.addUser(user);
	}

	@Override
	public PaginationResult<User> getUserList(PageInfo pageInfo) {
		PaginationResult<User> paginationResult = new PaginationResult<User>(userDao.getAllUsersCount(), userDao.getAllUsers(pageInfo));
		return paginationResult;
	}

	@Override
	public boolean checkUserName(User user) {
		boolean result = false;
		User user1 = userDao.getUserByName(user.getUserName());
		if(user1 == null){
			result = true;
		}else{
			if(user1.getId() == user.getId()){
				result = true;
			}
		}
		return result;
	}

	@Override
	public void deleteUser(int id) {
		User user = userDao.getUserById(id);
		userDao.deleteUser(user);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public void updateUser(User user) {
		User old = userDao.getUserById(user.getId());
		if(user.getPassword().equals("123456")){
			user.setPassword(old.getPassword());
		}else{
			user.setPassword(MD5Util.getMD5String(user.getPassword()));
		}
		user.setCreateDate(old.getCreateDate());
		userDao.updateUser(user);
	}
}
