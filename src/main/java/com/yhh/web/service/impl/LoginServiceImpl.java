package com.yhh.web.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhh.web.common.util.MD5Util;
import com.yhh.web.dao.UserDao;
import com.yhh.web.model.user.User;
import com.yhh.web.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	private UserDao userDao;
	
	@Override
	public boolean checkLogin(String userName, String password) {
		boolean result = false;
		User user = userDao.getUserByName(userName);
		if(user != null){
			if(user.getPassword().equals(MD5Util.getMD5String(password))){
				result = true;
			}
		}
		return result;
	}

	@Override
	public User getUserByName(String userName) {
		User user = userDao.getUserByName(userName);
		return user;
	}

}
