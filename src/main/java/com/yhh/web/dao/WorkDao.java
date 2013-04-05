package com.yhh.web.dao;

import java.util.List;

import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.work.Work;

public interface WorkDao {

	public List<Work> getWorksByUserId(PageInfo pageInfo,int userId);
	
	public List<Work> getAllWorks(PageInfo pageInfo);
	
	public int getAllWorksCount();

	public void addUser(Work work);
	
	public int getUserWorksCount(int userId);
	
	public Work getWorkById(int id);
	
	public void deleteWork(Work work);
	
	public void updateWork(Work work);
}
