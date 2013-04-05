package com.yhh.web.service;

import com.yhh.web.model.user.User;

public interface LoginService {

	public boolean checkLogin(String userName, String password);
	
	public User getUserByName(String userName);
}
