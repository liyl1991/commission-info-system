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
		<title>新员工录入</title>
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
							新员工录入
							<small>
								<i class="icon-double-angle-right"></i>
								您可以在这里录入新员工信息。所有新员工密码默认为'<%=defaultPwd %>'。
							</small>
						</h1>
					</div>
					<div class="page-content">
						<form id="inputUserForm" action="<%=path%>/userMgr/doInputUser" class="form-horizontal" role="form" method="post">
							<div class="form-group" style="height: 34px;">
								<label class="col-md-3 control-label no-padding-right" for="form-field-name"><span class="required-flag">*</span> 姓名 </label>
								<div class="col-md-5">
									<input type="text" name="name" id="form-field-name" maxlength="32" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right" for="form-field-sex"> 性别 </label>
								<div class="col-md-5">
								  <div class="col-md-3">
									<label>
										<input type="radio" class="ace" name="sex" value="1" checked="checked"/>
										<span class="lbl"> 男</span>
									</label>
								  </div>
								  <div class="col-md-3">
									<label>
										<input type="radio" class="ace" name="sex" value="2"/>
										<span class="lbl"> 女</span>
									</label>
								  </div>
								  <div class="col-md-5">
									<label>
										<input type="radio" class="ace" name="sex" value="3"/>
										<span class="lbl"> 保密</span>
									</label>
								  </div>
								</div>
							</div>
							<div class="form-group" style="height: 34px;">
								<label class="col-md-3 control-label no-padding-right" for="form-field-idcard"><span class="required-flag">*</span>身份证 </label>
								<div class="col-md-5">
									<input type="text" name="idCard" id="form-field-idcard" maxlength="18" class="col-md-5 form-control" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right" for="form-field-level"> 等级 </label>
								<div class="col-md-5">
									<select class="form-control" id="form-field-level" name="level">
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
										<option value="E" selected="selected">E</option>
										<option value="X">X</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right" for="form-field-upline"> 员工上级 </label>
								<div class="col-md-5">
									<div class="col-md-12 no-padding-left">
									<select name="uplineUser" class="col-md-12 chosen-select" id="form-field-upline" data-placeholder="">
										<c:forEach items="${uplineCandidate }" var="candidate">
											<option value="${candidate.userId }" <c:if test="${user.uplineUser == candidate.userId}">selected="selected"</c:if>>${candidate.name }(${candidate.level }级)</option>
										</c:forEach>
									</select>
									</div>
								</div>
							</div>
							<!-- <div class="form-group">
								<label class="col-md-3 control-label no-padding-right" for="form-field-birth"> 出生时间 </label>
								<div class="col-md-5">
									<input type="text" value="1970年1月1日" data-date-format="yyyy年mm月dd" id="form-field-birth" class="form-control date-picker" />
									<input type="hidden" name="birthday"/>
								</div>
							</div> -->
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right" for="form-field-addr"> 住址 </label>
								<div class="col-md-5">
									<input type="text" name="address" id="form-field-addr" class="form-control" maxlength="255"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label no-padding-right" for="form-field-career"> 职业 </label>
								<div class="col-md-5">
									<input type="text" name="career" id="form-field-career" class="form-control" maxlength="128"/>
								</div>
							</div>
						</form>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-primary submitBtn" type="button">
									<i class="icon-ok bigger-110"></i>
									确认
								</button>
								<button class="btn btn-light" type="button" onclick="history.go(-1)">
									<i class="icon-reply"></i>
									返回
								</button>
							</div>
						</div>
					<!-- 内容结束 -->
					</div>
				</div>
		   </div>
		</div>
		<!-- modal start -->
		<div id="existDeletedUserDlg" class="modal" tabindex="-1">
			<div class="modal-dialog margin-top-55" style="width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="blue bigger">您输入的身份证已存在，该用户已被删除，您可以选择恢复使用</h3>
					</div>
					<div class="modal-body overflow-visible">
						<div class="success-content" style="height: 255px;">
						  <div class="table-responsive col-md-offset-2 col-md-8">
							<table id="successInputedUserTable" class="table table-striped table-bordered table-hover">
							  <tr>
							    <td class="bg-info text-primary" width="30%">姓名</td>
							    <td width="70%" prop-name="name"></td>
							  </tr>
							  <tr>
							    <td class="bg-info text-primary">身份证</td>
							    <td prop-name="idCard"></td>
							  </tr>
							  <tr>
							    <td class="bg-info text-primary">等级</td>
							    <td prop-name="level"></td>
							  </tr>
							  <tr>
							    <td class="bg-info text-primary">上级</td>
							    <td prop-name="upline"></td>
							  </tr>
							  <tr>
							    <td class="bg-info text-primary">住址</td>
							    <td prop-name="address"></td>
							  </tr>
							  <tr>
							    <td class="bg-info text-primary">职业</td>
							    <td prop-name="career"></td>
							  </tr>
							</table>
						  </div>
						</div>
					</div>

					<div class="modal-footer">
						<button class="btn btn-sm" data-dismiss="modal">
							<i class="icon-remove"></i>
							取消
						</button>
						<button class="btn btn-sm btn-primary recoverUserBtn">
							<i class="icon-retweet"></i>
							恢复
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- modal end -->
		<!-- basic scripts -->
		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script src="<%=path %>/assets/js/chosen.jquery.min.js"></script>
		<script src="<%=path %>/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script src="<%=path %>/assets/js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=path %>/jsp/mgr/user/adminInputUser.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(".chosen-select").chosen({no_results_text: "未找到匹配项"});
			var iu = new InputUser();
		</script>
</body>
</html>

