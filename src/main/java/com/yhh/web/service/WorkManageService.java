package com.yhh.web.service;

import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.model.work.Work;

public interface WorkManageService {

	public void addUser(Work work);

	public PaginationResult<Work> getWorksByUserId(PageInfo pageInfo, int userId);
	
	public PaginationResult<Work> getAllWorks(PageInfo pageInfo);

	public void deleteWork(int id);
	
	public Work getWorkById(int id);
	
	public void updateWork(Work work);
}
