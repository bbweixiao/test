<%@page import="com.kaishengit.entity.Account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  				Account account = (Account)session.getAttribute("session_account");
%>
<div class="navbar navbar-fixed-top">
  		<div class="navbar-inner">
		    <div class="container">
		     	<a class="brand" href="#">网上银行</a>
		     	<ul class="nav pull-right">
                  <li class="dropdown" id="fat-menu">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#"><%=account.getUsername() %><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a data-toggle="modal" href="#myModal">账户设置</a></li>
                      <li class="divider"></li>
                      <li><a href="#">安全退出</a></li>
                    </ul>
                  </li>
                </ul>
		    </div>
	    </div>
  	</div>
 
    <div class="modal fade" id="myModal">
	    <div class="modal-header">
	    	<a class="close" data-dismiss="modal">×</a>
	    <h3>账号设置</h3>
	    </div>
	    <div class="modal-body">
	   		<form class="form-horizontal">
		        <fieldset>
		          <div class="control-group">
		            <label for="input01" class="control-label">原始密码</label>
		            <div class="controls">
		              <input type="text" id="input01" class="input-xlarge">
		            </div>
		          </div>
		          <div class="control-group">
		            <label for="input01" class="control-label">新密码</label>
		            <div class="controls">
		              <input type="text" id="input01" class="input-xlarge">
		            </div>
		          </div>
		          <div class="control-group">
		            <label for="input01" class="control-label">确认新密码</label>
		            <div class="controls">
		              <input type="text" id="input01" class="input-xlarge">
		            </div>
		          </div>
		        </fieldset>
		      </form>
	    </div>
	    <div class="modal-footer">
	    <a href="#" class="btn" data-dismiss="modal">Close</a>
	    <a href="#" class="btn btn-primary">Save changes</a>
	    </div>
    </div>
