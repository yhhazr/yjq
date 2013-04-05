package com.yhh.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhh.web.dao.DocumentDao;
import com.yhh.web.model.file.Document;
import com.yhh.web.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Resource
	private DocumentDao docDao;
	
	@Override
	public void addDoc(Document doc) {
		docDao.addDoc(doc);
	}

	@Override
	public List<Document> getAllDoc(int workId) {
		List<Document> docList = docDao.getAllDoc(workId);
		return docList;
	}

	@Override
	public void deleteDoc(int docId) {
		Document doc = docDao.getDocById(docId);
		docDao.deleteDoc(doc);
	}

}
