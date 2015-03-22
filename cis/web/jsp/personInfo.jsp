<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>个人信息</title>
		<jsp:include page="/common/inc.jsp"></jsp:include>
		
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
							<li class="active">个人信息</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-content">
							<div class="page-header">
								<h1>
									个人信息
									<small>
										<i class="icon-double-angle-right"></i>
										您的个人信息如下
									</small>
								</h1>
							</div><!-- /.page-header -->
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">身份证号 </label>

										<div class="col-sm-9">
											<input type="text" value="${loginedUser.idCard }" readonly id="form-field-1" class="col-xs-10 col-sm-5" />
										</div>
									</div>

									<div class="space-2"></div>
								
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 姓名 </label>

										<div class="col-sm-9">
											<input type="text" value="${loginedUser.name }" readonly id="form-field-1" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别 </label>

										<div class="col-sm-9">
											<input type="text" value="<c:if test="${loginedUser.sex eq '1'}">男</c:if><c:if test="${loginedUser.sex eq '2'}">女</c:if><c:if test="${loginedUser.sex eq '3'}">保密</c:if>" 
												readonly id="form-field-1" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									
									<div class="space-2"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 等级 </label>

										<div class="col-sm-9">
											<input type="text" value="${loginedUser.level }" readonly id="form-field-1" class="col-xs-10 col-sm-5" />
										</div>
									</div>

									<div class="space-2"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 出生时间 </label>

										<div class="col-sm-9">
											<input type="text" value="<fmt:formatDate value="${loginedUser.birthday }" pattern="yyyy年MM月dd日"/>" readonly id="form-field-1" class="col-xs-10 col-sm-5" />
										</div>
									</div>

									<div class="space-2"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 住址 </label>

										<div class="col-sm-9">
											<input type="text" value="${loginedUser.address }" readonly id="form-field-1" class="col-xs-10 col-sm-5" />
										</div>
									</div>

									<div class="space-2"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职业 </label>

										<div class="col-sm-9">
											<input type="text" value="会计" id="form-field-1" readonly class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<c:if test="${not empty uplineUser }">
									<div class="space-2"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 我的上级 </label>

										<div class="col-sm-9">
											<input type="text" value="${uplineUser.name }" id="form-field-1" readonly class="col-xs-10 col-sm-5" />
										</div>
									</div>
									</c:if>
								</form>
							</div>
						</div>
					</div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>		
		<script type="text/javascript">
		$(function(){
			//initPagination();
		});
		</script>

</body>
</html>

