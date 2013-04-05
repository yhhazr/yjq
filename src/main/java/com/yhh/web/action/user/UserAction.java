package com.yhh.web.action.user;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.model.user.User;
import com.yhh.web.service.UserManageService;

public class UserAction extends ActionSupport {
	
	
	private static final long serialVersionUID = 1L;

	private String pageSize;
	
	private String startRow;
	
	private String echo;
	
	private String result;
	
	private User user;
	
	private String userId;
	
	private String confirmPassword;
	
	@Resource
	private UserManageService userService;
	
	public String userManage(){
		return SUCCESS;
	}
	
	public String listUser(){
		PageInfo pageInfo = new PageInfo(Integer.parseInt(startRow), Integer.parseInt(pageSize), echo);
		PaginationResult<User> pageinaResult = userService.getUserList(pageInfo);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sEcho", echo);
		jsonObject.put("aaData", pageinaResult.getResultList());
		jsonObject.put("iTotalRecords", pageinaResult.getTotal());
		jsonObject.put("iTotalDisplayRecords", pageinaResult.getTotal());
		result = JSONObject.fromObject(jsonObject).toString();
		return SUCCESS;
	}
	
	public String addUser(){
		return SUCCESS;
	}
	
	public String addUserSubmit(){
		userService.addUser(user);
		result = "true";
		return SUCCESS;
	}
	
	public String deleteUser(){
		result = "false";
		if(userId != null && !userId.equals("")){
			userService.deleteUser(Integer.parseInt(userId));
			result = "true";
		}
		return SUCCESS;
	}
	
	public String editUser(){
		if(userId != null && !userId.equals("")){
			user = userService.getUserById(Integer.parseInt(userId));
		}
		return SUCCESS;
	}
	
	public String editUserSubmit(){
		userService.updateUser(user);
		result = "true";
		return SUCCESS;
	}
	
	public void validateEditUserSubmit(){
		if(user.getName().length() == 0 || user.getName().equals("")){
			this.addFieldError("user.name", "姓名不能为空");
		}
		if(user.getPhone().length() == 0 || user.getPhone().equals("")){
			this.addFieldError("user.phone", "电话不能为空");
		}
		if(user.getEmail().length() == 0 || user.getEmail().equals("")){
			this.addFieldError("user.email", "邮箱不能为空");
		}
		if(user.getPassword().length() != 0 && !user.getPassword().equals("") && !user.getPassword().equals(confirmPassword)){
			this.addFieldError("confirmPassword", "两次密码不一致");
		}
		if(user.getUserName().length() != 0 && !user.getUserName().equals("") && !userService.checkUserName(user)){
			this.addFieldError("user.userName", "该用户名已被使用");
		}
	}
	
	public void validateAddUserSubmit(){
		if(user.getName().length() == 0 || user.getName().equals("")){
			this.addFieldError("user.name", "姓名不能为空");
		}
		if(user.getPhone().length() == 0 || user.getPhone().equals("")){
			this.addFieldError("user.phone", "电话不能为空");
		}
		if(user.getEmail().length() == 0 || user.getEmail().equals("")){
			this.addFieldError("user.email", "邮箱不能为空");
		}
		if(user.getPassword().length() != 0 && !user.getPassword().equals("") && !user.getPassword().equals(confirmPassword)){
			this.addFieldError("confirmPassword", "两次密码不一致");
		}
		if(user.getUserName().length() != 0 && !user.getUserName().equals("") && !userService.checkUserName(user)){
			this.addFieldError("user.userName", "该用户名已被使用");
		}
	}


	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	public String getEcho() {
		return echo;
	}

	public void setEcho(String echo) {
		this.echo = echo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
