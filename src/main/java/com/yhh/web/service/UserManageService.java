package com.yhh.web.service;

import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.model.user.User;

public interface UserManageService {

	public void addUser(User user);
	
	public PaginationResult<User> getUserList(PageInfo pageInfo);
	
	public boolean checkUserName(User user);
	
	public void deleteUser(int id);
	
	public User getUserById(int id);
	
	public void updateUser(User user);
}
