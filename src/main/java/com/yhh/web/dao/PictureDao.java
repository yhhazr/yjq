package com.yhh.web.dao;

import java.util.List;

import com.yhh.web.model.file.Picture;
import com.yhh.web.model.pagination.PageInfo;

public interface PictureDao {

	public void addPic(Picture pic);
	
	public List<Picture> getAllPic(int workId);
	
	public Picture getPicById(int id);
	
	public void deletePic(Picture pic);
	
	public void deletePicByWorkId(int workId);
	
}
