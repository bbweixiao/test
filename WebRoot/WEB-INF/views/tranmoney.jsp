<%@page import="com.kaishengit.util.StringUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<style type="text/css">
		body{
			padding-top:70px;
		}
	</style>
  </head>
  
  <body>
    
  	<%@ include file="include/top.jsp" %>
  	
  	<div class="container">
  		<div class="row">
  			<div class="span2">
  				<div class="well" style="padding:10px 0px;">
		            <ul class="nav nav-list">
		              <li class="nav-header">操作菜单</li>
		              <li ><a href="main.jspx">首页</a></li>
		              <li><a href="#">取款</a></li>
		              <li><a href="#">存款</a></li>
		              <li class="active"><a href="account.jspx?m=tran">转账</a></li>
		              <li><a href="#">操作记录</a></li>
		             
		              <li class="nav-header">Sidebar</li>
		              <li><a href="#">Link</a></li>
		              <li><a href="#">Link</a></li>
		              <li><a href="#">Link</a></li>
		            </ul>
	           </div>
  			
  			</div>
  			<div class="span10">
  				<%
  					String msg = (String)session.getAttribute("msg");
  					if(StringUtil.isNotEmpty(msg)) {
  						session.removeAttribute("msg");
  					
  				%>
  					<div class="alert alert-error">
					  <a class="close" data-dismiss="alert">×</a>
					  <p><%=msg %></p>
					</div>
  				<%} 
  					String state = request.getParameter("state");
  					if("10000".equals(state)) {
  				%>
  					<div class="alert alert-success">
					  <a class="close" data-dismiss="alert">×</a>
					  <p>转账成功！</p>
					</div>
  				<%} %>
  				
  				<form class="form-horizontal" action="account.jspx?m=tran" method="post">
		        <fieldset>
		          <legend>转账</legend>
		          <div class="control-group">
		            <label for="input01" class="control-label">账号</label>
		            <div class="controls">
		              <input type="text" id="input01" class="input-xlarge" name="id">
		              <p class="help-block">请输入有效的账号</p>
		            </div>
		          </div>
		          <div class="control-group">
		            <label for="input01" class="control-label">金额</label>
		            <div class="controls">
		              <input type="text" id="input01" class="input-xlarge" name="money">
		              <p class="help-block">金额不能为0或者负数</p>
		            </div>
		          </div>
		          <div class="form-actions">
		            <button class="btn btn-primary" type="submit">保存</button>
		          </div>
		        </fieldset>
		      </form>
  				
  				
  				
  			</div>
  		</div>
  	</div>
  	
    
    
    
  </body>
</html>
