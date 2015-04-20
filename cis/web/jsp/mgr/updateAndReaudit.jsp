<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>员工管理</title>
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
							<li><a href="<%=path%>/auditInfo/goAuditUser">用户审核</a></li>
							<li class="active">修改重审</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-content">
							<div class="page-header">
								<h1>
									修改重审
									<small>
										<i class="icon-double-angle-right"></i>
										该用户申请已经被驳回，您可以修改它的资料，并重过申请。
									</small>
								</h1>
							</div><!-- /.page-header -->
							<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
									<div class="row alterContent"></div>
									<form id="updateUserForm" class="form-horizontal" role="form" method="post">
										<input type="hidden" name="userId" value="${user.userId}"/>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-idcard">身份证号 </label>
	
											<div class="col-sm-9">
												<input type="text" name="idCard" value="${user.idCard}" id="form-field-idcard" class="col-xs-10 col-sm-5" />
											</div>
										</div>
	
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-name"> 姓名 </label>
	
											<div class="col-sm-9">
												<input type="text" name="name" value="${user.name}" id="form-field-name" class="col-xs-10 col-sm-5" />
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-sex"> 性别 </label>
	
											<div class="col-sm-9">
											  <div class="col-sm-1">
												<label>
													<input type="radio" class="ace" name="sex" value="1" <c:if test="${user.sex eq '1'}">checked</c:if>/>
													<span class="lbl"> 男</span>
												</label>
											  </div>
											  <div class="col-sm-1">
												<label>
													<input type="radio" class="ace" name="sex" value="2" <c:if test="${user.sex eq '2'}">checked</c:if>/>
													<span class="lbl"> 女</span>
												</label>
											  </div>
											  <div class="col-sm-2">
												<label>
													<input type="radio" class="ace" name="sex" value="3" <c:if test="${user.sex eq '3'}">checked</c:if> />
													<span class="lbl"> 保密</span>
												</label>
											  </div>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-birth"> 出生时间 </label>
	
											<div class="col-sm-9">
												<input type="text" value="<fmt:formatDate value="${user.birthday }" pattern="yyyy年MM月dd日"/>" data-date-format="yyyy年mm月dd" id="form-field-birth" class="col-xs-10 col-sm-5 date-picker" />
												<input type="hidden" name="birthday" value="${user.birthday }"/>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-addr"> 住址 </label>
	
											<div class="col-sm-9">
												<input type="text" name="address" value="${user.address}" id="form-field-addr" class="col-xs-10 col-sm-5" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="form-field-career"> 职业 </label>
	
											<div class="col-sm-9">
												<input type="text" name="career" value="${user.career}" id="form-field-career" class="col-xs-10 col-sm-5" />
											</div>
										</div>
									</form>
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-primary submitUpdateBtn" type="button">
												<i class="icon-pencil bigger-110"></i>
												修改
											</button>
											<button class="btn btn-success submitUpdateAndPassBtn" type="button">
												<i class="icon-check bigger-110"></i>
												修改并通过
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script src="<%=path %>/assets/js/chosen.jquery.min.js"></script>
		<script src="<%=path %>/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script src="<%=path %>/assets/js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=path %>/jsp/mgr/updateAndReaudit.js" type="text/javascript"></script>
		<script type="text/javascript">
			var userId = ${user.userId};
			var AuditUser = new AuditUser(userId);
			$('.date-picker').datepicker({autoclose:true}).on('changeDate', function(ev){
				var dt = new Date(ev.date.valueOf());
				$('input[name="birthday"]').val(dt);
			});
			 //userDetail.showUserInfo();
		</script>
</body>
</html>

