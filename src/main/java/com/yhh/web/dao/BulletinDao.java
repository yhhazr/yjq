package com.yhh.web.dao;

import java.util.List;

import com.yhh.web.model.bulletin.Bulletin;
import com.yhh.web.model.pagination.PageInfo;

public interface BulletinDao {
	
	public void addBulletin(Bulletin bul);
	
	public List<Bulletin> getAllBulletins(PageInfo pageInfo);
	
	public int getAllBulletinsCount();
	
	public Bulletin getBulletinById(int id);
	
	public void updateBulletin(Bulletin bul);
	
	public void deleteBulletin(Bulletin bul);
}
