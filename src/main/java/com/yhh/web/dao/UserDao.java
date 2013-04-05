package com.yhh.web.dao;

import java.util.List;

import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.user.User;

public interface UserDao {

	public User getUserByName(String userName); 
	
	public void addUser(User user);
	
	public List<User> getAllUsers(PageInfo pageInfo);
	
	public int getAllUsersCount();
	
	public void deleteUser(User user);
	
	public User getUserById(int id);
	
	public void updateUser(User user);
}
