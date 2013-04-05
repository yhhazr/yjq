package com.yhh.web.service;

import com.yhh.web.model.bulletin.Bulletin;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;

public interface BulletinService {

	public void addBulletin(Bulletin bul);
	
	public void deleteBulletin(int id);
	
	public void updateBulletin(Bulletin bul);
	
	public PaginationResult<Bulletin> getAllBulletins(PageInfo pageInfo);
	
	public Bulletin getBulletinById(int id);
}
