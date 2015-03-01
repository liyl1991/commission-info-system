<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<title>提成信息系统</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=path %>/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="<%=path %>/css/fullcalendar.css" />	
		<link rel="stylesheet" href="<%=path %>/css/unicorn.main.css" />
		<link rel="stylesheet" href="<%=path %>/css/unicorn.blue.css" class="skin-color" />
	<body>
		
		<jsp:include page="/jsp/sidebar.jsp"></jsp:include>
		
		<div id="content">
			<!-- 导航条 -->
			<div id="breadcrumb">
				<a href="<%=path %>/jsp/index.jsp" title="回到首页" class="tip-bottom"><i class="icon-home"></i> 首页</a>
				<a href="#" class="current">个人信息</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
				</div>
			</div>
		</div>
		

            <script src="<%=path %>/js/excanvas.min.js"></script>
            <script src="<%=path %>/js/jquery.min.js"></script>
            <script src="<%=path %>/js/jquery.ui.custom.js"></script>
            <script src="<%=path %>/js/bootstrap.min.js"></script>
            <script src="<%=path %>/js/jquery.flot.min.js"></script>
            <script src="<%=path %>/js/jquery.flot.resize.min.js"></script>
            <script src="<%=path %>/js/jquery.peity.min.js"></script>
            <script src="<%=path %>/js/fullcalendar.min.js"></script>
            <script src="<%=path %>/js/unicorn.js"></script>
            <script src="<%=path %>/js/unicorn.dashboard.js"></script>
            <script src="<%=path %>/js/unicorn.tables.js"></script>
	</body>
	
</html>
