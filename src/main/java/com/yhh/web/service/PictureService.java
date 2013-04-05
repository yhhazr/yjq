package com.yhh.web.service;

import java.util.List;

import com.yhh.web.model.file.Picture;
import com.yhh.web.model.pagination.PageInfo;

public interface PictureService {

	public void addPic(Picture pic);
	
	public List<Picture> getAllPic( int workId);
	
	public void deletePic(int picId);
}
