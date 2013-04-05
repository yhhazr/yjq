package com.yhh.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhh.web.dao.PictureDao;
import com.yhh.web.model.file.Picture;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.service.PictureService;

@Service
public class PictureManageService implements PictureService {

	@Resource
	private PictureDao picDao;
	
	@Override
	public void addPic(Picture pic) {
		picDao.addPic(pic);
	}

	@Override
	public List<Picture> getAllPic(int workId) {
		return picDao.getAllPic(workId);
	}

	@Override
	public void deletePic(int picId) {
		Picture pic = picDao.getPicById(picId);
		picDao.deletePic(pic);
	}

}
