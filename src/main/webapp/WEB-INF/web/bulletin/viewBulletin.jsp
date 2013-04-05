<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
	<s:iterator value="bulList" id="bul">
		<s:if test="#bul.degree == '一般'">
			<div class="alert alert-info">
    			<h3 style="text-align:center;"><s:property value='#bul.title'/></h3>
    						<s:property value='#bul.content'/>
    			<h5 style="text-align: right;">发布人：<s:property value='#bul.userName'/></h5>
    			<h5 style="text-align: right;">日期：<s:date format="yyyy年MM月dd日" name="#bul.createDate"/></h5>
    		</div>
		</s:if>
		<s:else>
			<div class="alert alert-error">
    			<h3 style="text-align:center;"><s:property value='#bul.title'/></h3>
    						<s:property value='#bul.content'/>
    			<h5 style="text-align: right;">发布人：<s:property value='#bul.userName'/></h5>
    			<h5 style="text-align: right;">日期：<s:date format="yyyy年MM月dd日" name="#bul.createDate"/></h5>
    		</div>
		</s:else>
	</s:iterator>