package com.yhh.web.action.work;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sina.sae.storage.SaeStorage;
import com.yhh.web.model.file.Document;
import com.yhh.web.model.file.Picture;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.model.work.Work;
import com.yhh.web.service.DocumentService;
import com.yhh.web.service.PictureService;
import com.yhh.web.service.WorkManageService;

public class WorkAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Resource
	private WorkManageService workService;
	
	@Resource
	private PictureService picService;
	
	@Resource
	private DocumentService docService;
	
	private Work work;
	
	private String pageSize;
	
	private String startRow;
	
	private String echo;
	
	private String result;
	
	private String workId;
	
	private File picture;
	
	private File doc;
	
	private String docFileName;
	
	private String pictureFileName;
	
	private List<Picture> picList;
	
	private List<Document> docList;
	
	public String workManage(){
		return SUCCESS;
	}
	
	public String listWork(){
		PageInfo pageInfo = new PageInfo(Integer.parseInt(startRow), Integer.parseInt(pageSize), echo);
		Integer userId = (Integer)ServletActionContext.getRequest().getSession().getAttribute("id");
		PaginationResult<Work> pageinaResult = workService.getWorksByUserId(pageInfo,userId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sEcho", echo);
		jsonObject.put("aaData", pageinaResult.getResultList());
		jsonObject.put("iTotalRecords", pageinaResult.getTotal());
		jsonObject.put("iTotalDisplayRecords", pageinaResult.getTotal());
		result = JSONObject.fromObject(jsonObject).toString();
		return SUCCESS;
	}
	
	public String listAllWork(){
		PageInfo pageInfo = new PageInfo(Integer.parseInt(startRow), Integer.parseInt(pageSize), echo);
		PaginationResult<Work> pageinaResult = workService.getAllWorks(pageInfo);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sEcho", echo);
		jsonObject.put("aaData", pageinaResult.getResultList());
		jsonObject.put("iTotalRecords", pageinaResult.getTotal());
		jsonObject.put("iTotalDisplayRecords", pageinaResult.getTotal());
		result = JSONObject.fromObject(jsonObject).toString();
		return SUCCESS;
	}
	
	public String addWork(){
		return SUCCESS;
	}
	
	public String addWorkSubmit(){
		Integer userId = (Integer)ServletActionContext.getRequest().getSession().getAttribute("id");
		work.setUserId(userId);
		workService.addUser(work);
		result = "true";
		return SUCCESS;
	}
	
	public String deleteWork(){
		result = "false";
		if(workId != null){
			workService.deleteWork(Integer.parseInt(workId));
			result = "true";
		}
		return SUCCESS;
	}
	
	public String deletePic(){
		result = "false";
		if(workId != null){
			picService.deletePic(Integer.parseInt(workId));
			result = "true";
		}
		return SUCCESS;
	}

	public String deleteDoc(){
		result = "false";
		if(workId != null){
			docService.deleteDoc(Integer.parseInt(workId));
			result = "true";
		}
		return SUCCESS;
	}
	
	public String editWork(){
		result = "false";
		if(workId != null && !workId.equals("")){
			work = workService.getWorkById(Integer.parseInt(workId));
			result = "true";
		}
		return SUCCESS;
	}
	
	public String editWorkSubmit(){
		workService.updateWork(work);
		result = "true";
		return SUCCESS;
	}
	
	public String viewWork(){
		if(workId != null){
			picList = picService.getAllPic(Integer.parseInt(workId));
			docList = docService.getAllDoc(Integer.parseInt(workId));
		}
		return SUCCESS;
	}
	
	public String uploadPic() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream inputStream = request.getInputStream();
		String extName = null;
        if(pictureFileName.lastIndexOf(".")>=0){  
            extName = pictureFileName.substring(pictureFileName.lastIndexOf("."));  
        } 
        String picName = new Date().getTime() + extName;
        SaeStorage storage = new SaeStorage();
		FileInputStream fileInput = new FileInputStream(picture);
		byte[] fileInByte = new byte[fileInput.available()];
		fileInput.read(fileInByte);
		fileInput.close();
		storage.write("test", picName, fileInByte);
        if(workId != null && !"".equals(workId)){
        	Picture pic = new Picture(Integer.parseInt(workId), storage.getUrl("test", picName), pictureFileName, new Date());
        	picService.addPic(pic);
        }
		return null;
	}
	public String uploadDoc() throws Exception{
		String saveRealFilePath = ServletActionContext.getServletContext().getRealPath("/upload");    
		File fileDir = new File(saveRealFilePath);    
		if (!fileDir.exists()) {    
			fileDir.mkdirs();    
		}   
		File savefile;   
		String extName = null;
		if(docFileName.lastIndexOf(".")>=0){  
			extName = docFileName.substring(docFileName.lastIndexOf("."));  
		} 
		String docName = new Date().getTime() + extName;
		savefile = new File(saveRealFilePath + "/" + docName); 
		FileUtils.copyFile(doc,savefile);  
		if(workId != null && !"".equals(workId)){
			Document doc = new Document(Integer.parseInt(workId), docName, docFileName, new Date());
			if(".doc".equals(extName) || ".docx".equals(extName)){
				doc.setExt("word.png");
			}else if(".xls".equals(extName) || ".xlsx".equals(extName)){
				doc.setExt("excel.png");
			}else if(".ppt".equals(extName) || ".pptx".equals(extName)){
				doc.setExt("powerpoint.png");
			}else if(".txt".equals(extName)){
				doc.setExt("txt.png");
			}else{
				doc.setExt("other");
			}
			docService.addDoc(doc);
		}
		return null;
	}

	public void validateEditWorkSubmit(){
		if(work.getWorkName().length() == 0 || work.getWorkName().equals("")){
			this.addFieldError("work.workName", "工作名称不能为空");
		}
	}
	
	public void validateAddWorkSubmit(){
		if(work.getWorkName().length() == 0 || work.getWorkName().equals("")){
			this.addFieldError("work.workName", "工作名称不能为空");
		}
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	public String getEcho() {
		return echo;
	}

	public void setEcho(String echo) {
		this.echo = echo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public List<Picture> getPicList() {
		return picList;
	}

	public void setPicList(List<Picture> picList) {
		this.picList = picList;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public List<Document> getDocList() {
		return docList;
	}

	public void setDocList(List<Document> docList) {
		this.docList = docList;
	}
	

}
