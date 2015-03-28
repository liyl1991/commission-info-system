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
							<li><a href="<%=path%>/admin/goAdminMgr">员工管理</a></li>
							<li class="active">新增员工</li>
						</ul><!-- .breadcrumb -->
					</div>
					<!-- 内容始 -->
					<div class="page-header">
						<h1>
							新员工录入
							<small>
								<i class="icon-double-angle-right"></i>
								您可以在这里录入新员工信息。所有新员工密码默认为'abc123'。
							</small>
						</h1>
					</div>
					<form id="inputUserForm" class="form-horizontal" role="form" method="post">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-idcard">身份证号 </label>

							<div class="col-sm-9">
								<input type="text" name="idCard" id="form-field-idcard" class="col-xs-10 col-sm-5" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-name"> 姓名 </label>

							<div class="col-sm-9">
								<input type="text" name="name" id="form-field-name" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-sex"> 性别 </label>

							<div class="col-sm-9">
							  <div class="col-sm-1">
								<label>
									<input type="radio" class="ace" name="sex" value="1" checked="checked"/>
									<span class="lbl"> 男</span>
								</label>
							  </div>
							  <div class="col-sm-1">
								<label>
									<input type="radio" class="ace" name="sex" value="2"/>
									<span class="lbl"> 女</span>
								</label>
							  </div>
							  <div class="col-sm-2">
								<label>
									<input type="radio" class="ace" name="sex" value="3"/>
									<span class="lbl"> 保密</span>
								</label>
							  </div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-level"> 等级 </label>
							<div class="col-sm-9">
								<select class="col-sm-5" id="form-field-level" name="level">
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E" selected="selected">E</option>
									<option value="X">X</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-upline"> 员工上级 </label>
							<div class="col-sm-9">
								<%-- <input type="text" name="uplineName" value="${uplineUser.name }(${uplineUser.level }级)" id="form-field-1" class="col-xs-10 col-sm-5" /> --%>
								<select name="uplineUser" class="col-sm-4 chosen-select" id="form-field-upline" data-placeholder="">
									<c:forEach items="${uplineCandidate }" var="candidate">
										<option value="${candidate.userId }" <c:if test="${user.uplineUser == candidate.userId}">selected="selected"</c:if>>${candidate.name }(${candidate.level }级)</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-birth"> 出生时间 </label>

							<div class="col-sm-9">
								<input type="text" value="1970年1月1日" data-date-format="yyyy年mm月dd" id="form-field-birth" class="col-xs-10 col-sm-5 date-picker" />
								<input type="hidden" name="birthday"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-addr"> 住址 </label>

							<div class="col-sm-9">
								<input type="text" name="address" id="form-field-addr" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-career"> 职业 </label>

							<div class="col-sm-9">
								<input type="text" name="career" id="form-field-career" class="col-xs-10 col-sm-5" />
							</div>
						</div>
					</form>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-primary submitBtn" type="button">
								<i class="icon-ok bigger-110"></i>
								确认
							</button>
						</div>
					</div>
					<!-- 内容结束 -->
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script src="<%=path %>/assets/js/chosen.jquery.min.js"></script>
		<script src="<%=path %>/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script src="<%=path %>/assets/js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=path %>/jsp/mgr/adminInputUser.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(".chosen-select").chosen({no_results_text: "未找到匹配项"});
			$('.date-picker').datepicker({autoclose:true}).on('changeDate', function(ev){
				var dt = new Date(ev.date.valueOf());
				$('input[name="birthday"]').val(dt);
			}); 
			var iu = new InputUser();
			 //userDetail.showUserInfo();
		</script>
</body>
</html>

