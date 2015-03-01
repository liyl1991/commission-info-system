<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<div id="header">
	<h1><a href="./dashboard.html">提成信息</a></h1>		
</div>

<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav btn-group">
        <li class="btn btn-inverse dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-user"></i> <span class="text">张三</span><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="<%=path %>/jsp/personInfo.jsp">个人信息</a></li>
                <li><a class="sInbox" title="" href="#">密码管理</a></li>
            </ul>
        </li>
        <li class="btn btn-inverse"><a title="" href="<%=path %>/jsp/login.jsp"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
    </ul>
</div>

<div id="sidebar">
	<a href="#" class="visible-phone"><i class="icon icon-home"></i> 个人信息</a>
	<ul>
		<li class="active"><a href="index.html"><i class="icon icon-tasks"></i> <span>收入信息</span></a></li>
		<li><a href="buttons.html"><i class="icon icon-user"></i> <span>个人信息</span></a></li>
		<li><a href="buttons.html"><i class="icon icon-list-alt"></i> <span>下线员工</span></a></li>
	</ul>
</div>
