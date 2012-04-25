<%@page import="com.kaishengit.entity.Recode"%>
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
		.green{
			color:green;
		}
		.red{
			color:red;
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
		              <li class="active"><a href="#">首页</a></li>
		              <li><a href="#">取款</a></li>
		              <li><a href="#">存款</a></li>
		              <li><a href="#">转账</a></li>
		              <li><a href="#">操作记录</a></li>
		             
		              <li class="nav-header">Sidebar</li>
		              <li><a href="#">Link</a></li>
		              <li><a href="#">Link</a></li>
		              <li><a href="#">Link</a></li>
		            </ul>
	           </div>
  			
  			</div>
  			<div class="span10">
  				<div class="page-header">
  					<h3>操作记录</h3>
  				</div>
  				<table class="table table-striped">
			        <thead>
			          <tr>
			            <th width="5%">#</th>
			            <th width="20%">日期</th>
			            <th width="15%" class="green">存</th>
			            <th width="15%" class="red">取</th>
			            <th>备注</th>
			            <th width="15%">余额</th>
			          </tr>
			        </thead>
			        <tbody>
			        
			        	<%
			        		List<Recode> list = (List<Recode>)request.getAttribute("list");
			        		for(int i = 0;i < list.size();i++) {
			        			Recode r = list.get(i);
			        	%>
			        
			          <tr>
			            <td><%=i+1 %></td>
			            <td><%=r.getCreatetime() %></td>
			            <td class="green"><%=r.getInmoney() == 0 ? "" : "+" + r.getInmoney() %></td>
			            <td class="red"><%=r.getOutmoney() == 0 ? "" : "-" + r.getOutmoney() %></td>
			            <td><%=r.getNote() == null ? "" : r.getNote()%></td>
			            <td>￥<%=r.getBalance() %></td>
			          </tr>
			         <%} %>
			         
			        </tbody>
			      </table>
  				<script type="text/javascript">
  					var id = <%=account.getPassword()%>;
  				</script>
  				
  			</div>
  		</div>
  	</div>
  	
    
    
    
  </body>
</html>
