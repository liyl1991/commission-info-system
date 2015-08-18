<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="cn.haohao.cis.utils.Constants"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<% 
	String path = request.getContextPath();
	String defaultPwd = Constants.DEFAULT_PASSWORD;
%>
<!DOCTYPE html>
<html>
	<head>
		<title>操作成功</title>
		<jsp:include page="/common/inc.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=path %>/assets/css/jquery.gritter.css" />
	</head>

	<body>
		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<jsp:include page="/jsp/sidebar.jsp"></jsp:include>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li><a href="<%=path%>/userMgr/goUserMgr">员工管理</a></li>
							<li class="active">新增员工</li>
						</ul><!-- .breadcrumb -->
					</div>
					<!-- 内容始 -->
					<div class="page-header">
						<h1>
							新员工录入成功
						</h1>
					</div>
					<div class="page-content success-content">
					  <div class="table-responsive col-md-offset-2 col-md-8">
						<table id="successInputedUserTable" class="table table-striped table-bordered table-hover">
						  <tr>
						    <td class="bg-info text-primary" width="30%">姓名</td>
						    <td width="70%">${user.name }</td>
						  </tr>
						  <tr>
						    <td class="bg-info text-primary">身份证</td>
						    <td>${user.idCard }</td>
						  </tr>
						  <tr>
						    <td class="bg-info text-primary">等级</td>
						    <td>${user.level }</td>
						  </tr>
						  <tr>
						    <td class="bg-info text-primary">上级</td>
						    <td><a href="<%=path%>/userMgr/goUserDetail/${upline.userId}">${upline.name }(${upline.level })</a></td>
						  </tr>
						  <c:if test="${not empty user.address }">
							  <tr>
							    <td class="bg-info text-primary">住址</td>
							    <td>${user.address }</td>
							  </tr>
						  </c:if>
						  <c:if test="${not empty user.career }">
							  <tr>
							    <td class="bg-info text-primary">职业</td>
							    <td>${user.career }</td>
							  </tr>
						  </c:if>
						</table>
						<div>
						  <span>您可以：</span>
						  <a href="<%=path%>/userMgr/goInputUser" class="btn btn-success">继续添加</a>
						  <a href="<%=path%>/userMgr/goUserMgr" class="btn btn-success">去员工列表看看</a>
						</div>
					  </div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
</body>
</html>

