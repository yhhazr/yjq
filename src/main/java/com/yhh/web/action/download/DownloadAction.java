package com.yhh.web.action.download;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhh.web.dao.DocumentDao;
import com.yhh.web.model.file.Document;
public class DownloadAction extends ActionSupport {

	private String id;
	
	private String fileName;
	
	private Document doc;
	
	@Resource
	private DocumentDao docService;
	
	@Override
	public String execute() throws Exception {
		if(id != null){
			doc = docService.getDocById(Integer.parseInt(id));
			this.fileName = new String(doc.getRealName().getBytes(),"ISO8859-1");
		}
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public InputStream getInputStream() throws UnsupportedEncodingException, FileNotFoundException{
        InputStream is = ServletActionContext.getServletContext().getResourceAsStream("/upload/" + doc.getDocName());
        return is;
        
    }

}
