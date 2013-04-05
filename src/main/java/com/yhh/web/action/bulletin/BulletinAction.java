package com.yhh.web.action.bulletin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.yhh.web.model.bulletin.Bulletin;
import com.yhh.web.model.pagination.PageInfo;
import com.yhh.web.model.pagination.PaginationResult;
import com.yhh.web.service.BulletinService;

public class BulletinAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String pageSize;
	
	private String startRow;
	
	private String echo;
	
	private String result;
	
	private Bulletin bulletin;
	
	private List<Bulletin> bulList;
	
	private String id;
	
	@Resource
	private BulletinService bulService;
	
	public String listBulletin(){
		PageInfo pageInfo = new PageInfo(Integer.parseInt(startRow), Integer.parseInt(pageSize), echo);
		PaginationResult<Bulletin> pageinaResult = bulService.getAllBulletins(pageInfo);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sEcho", echo);
		jsonObject.put("aaData", pageinaResult.getResultList());
		jsonObject.put("iTotalRecords", pageinaResult.getTotal());
		jsonObject.put("iTotalDisplayRecords", pageinaResult.getTotal());
		result = JSONObject.fromObject(jsonObject).toString();
		return SUCCESS;
	}
	
	public String addBulletinSubmit(){
		String userName = (String)ServletActionContext.getRequest().getSession().getAttribute("name");
		bulletin.setUserName(userName);
		bulletin.setCreateDate(new Date());
		bulService.addBulletin(bulletin);
		result = "true";
		return SUCCESS;
	}
	
	public String deleteBulletin(){
		result = "false";
		if(id != null && !id.equals("")){
			bulService.deleteBulletin(Integer.parseInt(id));
			result = "true";
		}
		return SUCCESS;
	}
	
	public String editBulletin(){
		if(id != null && !id.equals("")){
			bulletin = bulService.getBulletinById(Integer.parseInt(id));
		}
		return SUCCESS;
	}
	
	public String editBulletinSubmit(){
		bulService.updateBulletin(bulletin);
		result = "true";
		return SUCCESS;
	}
	
	public String viewBulletin(){
		bulList = bulService.getAllBulletins(new PageInfo(0, 10, "")).getResultList();
		return SUCCESS;
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

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Bulletin> getBulList() {
		return bulList;
	}

	public void setBulList(List<Bulletin> bulList) {
		this.bulList = bulList;
	}
	
	
}
