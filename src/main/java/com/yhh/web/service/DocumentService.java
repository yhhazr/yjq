package com.yhh.web.service;

import java.util.List;

import com.yhh.web.model.file.Document;

public interface DocumentService {

	public void addDoc(Document doc);
	
	public List<Document> getAllDoc( int workId);
	
	public void deleteDoc(int docId);
}
