package com.yhh.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhh.web.dao.BulletinDao;
import com.yhh.web.model.bulletin.Bulletin;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.service.BulletinService;

@Service
public class BulletinServiceImpl implements BulletinService {

	@Resource
	private BulletinDao bulDao;
	
	@Override
	public void addBulletin(Bulletin bul) {
		bulDao.addBulletin(bul);
	}

	@Override
	public void deleteBulletin(int id) {
		Bulletin bul = bulDao.getBulletinById(id);
		bulDao.deleteBulletin(bul);
	}

	@Override
	public void updateBulletin(Bulletin bul) {
		Bulletin old = bulDao.getBulletinById(bul.getId());
		bul.setCreateDate(old.getCreateDate());
		bul.setUserName(old.getUserName());
		bulDao.updateBulletin(bul);
	}

	@Override
	public PaginationResult<Bulletin> getAllBulletins(PageInfo pageInfo) {
		PaginationResult<Bulletin> paginationResult = new PaginationResult<Bulletin>(bulDao.getAllBulletinsCount(), bulDao.getAllBulletins(pageInfo));
		return paginationResult;
	}

	@Override
	public Bulletin getBulletinById(int id) {
		Bulletin bul = bulDao.getBulletinById(id);
		return bul;
	}

}
