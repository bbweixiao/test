<%@page import="com.kaishengit.entity.Account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
  <head>
    <title>网上银行</title>
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
		              <li class="active"><a href="main.jspx">首页</a></li>
		              <li><a href="account.jspx?m=get">取款</a></li>
		              <li><a href="account.jspx?m=save">存款</a></li>
		              <li><a href="account.jspx?m=tran">转账</a></li>
		              <li><a href="recode.jspx">操作记录</a></li>
		             
		              <li class="nav-header">Sidebar</li>
		              <li><a href="#">Link</a></li>
		              <li><a href="#">Link</a></li>
		              <li><a href="#">Link</a></li>
		            </ul>
	           </div>
  			
  			</div>
  			
  			<div class="span10">
  				欢迎您，<%=account.getUsername() %>!你的账户余额为￥<%=account.getMoney() %>元
  			</div>
  		</div>
  	</div>
  	
    
    
    
  </body>
</html>
