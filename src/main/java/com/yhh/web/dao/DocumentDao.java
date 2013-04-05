package com.yhh.web.dao;

import java.util.List;

import com.yhh.web.model.file.Document;

public interface DocumentDao {

	public void addDoc(Document doc);
	
	public List<Document> getAllDoc(int workId);
	
	public Document getDocById(int id);
	
	public void deleteDoc(Document doc);
	
	public void deleteDocByWorkId(int workId);
}
