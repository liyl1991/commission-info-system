<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>
<div class="navbar navbar-default" id="navbar">
	<!-- <script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script> -->

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<i class="icon-leaf"></i>
					后台管理系统
				</small>
			</a><!-- /.brand -->
		</div><!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="grey">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="icon-qrcode"></i>
					</a>

					<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="icon-ok"></i>
							客户端下载
						</li>

						<li>
							<img src="<%=path %>/assets/images/ewm.png" width="220" height="220"/>
						</li>
					</ul>
				</li>
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<span class="user-info">
							<small>欢迎光临,</small>
							${loginedUser.name }
						</span>

						<i class="icon-caret-down"></i>
					</a>

					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="<%=path%>/jsp/passwordMgr.jsp">
								<i class="icon-lock"></i>
								密码管理
							</a>
						</li>

						<li>
							<a href="<%=path %>/user/goPersonInfo">
								<i class="icon-user"></i>
								个人资料
							</a>
						</li>

						<li class="divider"></li>

						<li>
							<a href="<%=path%>/login/doLogout">
								<i class="icon-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>
			</ul><!-- /.ace-nav -->
		</div><!-- /.navbar-header -->
	</div><!-- /.container -->
</div>