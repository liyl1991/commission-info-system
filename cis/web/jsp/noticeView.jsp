<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
	<head>
		<title>公告-${notice.title }</title>
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
							<li class="active"><a href="<%=path%>/notice/goNoticeList">公告列表</a></li>
							<li class="active">公告</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="space-4"></div>
						<div class="page-header">
							<h1>
								${notice.title }
								<small>
									<i class="icon-double-angle-right"></i>
									发布时间:<fmt:formatDate value="${notice.createDate}" pattern="yyyy年MM月dd日"/>
								</small>
							</h1>
						</div><!-- /.page-header -->
						<div id="noticeContainer">${notice.content }</div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->
		
		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
</body>
</html>

