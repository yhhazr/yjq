package com.yhh.web.action.login;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.yhh.web.dao.BulletinDao;
import com.yhh.web.model.bulletin.Bulletin;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.user.User;
import com.yhh.web.service.LoginService;
import com.yhh.web.service.UserManageService;



public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String userName;

	private String password;
	
	@Resource
	private LoginService loginService;
	
	public String login() {
		return SUCCESS;
	}
	
	public String loginSubmit() {
		String result = INPUT;
		if (loginService.checkLogin(userName, password)) {
			User user = loginService.getUserByName(userName);
			ServletActionContext.getRequest().getSession().setAttribute("name", user.getName());
			ServletActionContext.getRequest().getSession().setAttribute("id", user.getId());
			ServletActionContext.getRequest().getSession().setAttribute("isAdmin", user.getIsAdmin());
			result = SUCCESS;
		} else {
			this.addActionError("用户名或密码错误！");
			result = INPUT;
		}
		return result;
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().setAttribute("name", "");
		ServletActionContext.getRequest().getSession().setAttribute("id", "");
		ServletActionContext.getRequest().getSession().setAttribute("isAdmin", "");
		return SUCCESS;
	}

	public void validateLoginSubmit() {
		if (userName.equals("") || userName == null) {
			this.addFieldError("userName", "用户名不能为空");
		}
		if (password.equals("") || password == null) {
			this.addFieldError("password", "密码不能为空");
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
