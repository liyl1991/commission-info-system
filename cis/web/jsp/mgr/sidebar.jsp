<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<ul class="nav nav-list">
		<li class="active">
			<a href="<%=path%>/login/goIndex">
				<i class="icon-flag"></i>
				<span class="menu-text"> 员工管理 </span>
			</a>
		</li>

		<li class="">
			<a href="<%=path%>/user/goDownline">
				<i class="icon-group"></i>
				<span class="menu-text"> 用户审核</span>
				<span class="badge badge-primary downline-count"></span>
			</a>
		</li>
	</ul><!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>