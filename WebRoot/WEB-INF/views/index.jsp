<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
  <head>
    <title>网上银行-系统登录</title>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  </head>
  
  <body>
    
    
    <div class="container" style="margin-top:100px;">
	   <form class="well" action="index.jspx" method="post" style="margin-left:auto;margin-right:auto;width:220px">
	   	  <input type="hidden" name="m" value="login"/>
		  <label>账户</label>
		  <input type="text" class="span3"  name="name"/>
		  <label>密码</label>
		  <input type="password" class="span3"  name="password"/>
		  <br/>
		  <button type="submit" class="btn btn-primary">进入系统</button>
		</form>
    </div>
    
    
    
  </body>
</html>
