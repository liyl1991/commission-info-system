<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<ul class="nav nav-list">
	  <c:if test="${loginedUser.userRole==1 }">
		<li class="${indexActive }">
			<a href="<%=path%>/userIncome/goIncomeInfo">
				<i class="icon-flag"></i>
				<span class="menu-text"> 收入与业绩 </span>
			</a>
		</li>

		<li class="${downlineActive }">
			<a href="<%=path%>/user/goDownline">
				<i class="icon-group"></i>
				<span class="menu-text"> 下线人员</span>
				<span class="badge badge-primary downline-count"></span>
			</a>
		</li>
	  </c:if>
	  <c:if test="${loginedUser.userRole==2 }">
		<li class="${adminMgrActive}">
			<a href="<%=path%>/userMgr/goUserMgr">
				<i class="icon-flag"></i>
				<span class="menu-text"> 员工管理 </span>
			</a>
		</li>

		<li class="${auditUserActive}">
			<a href="<%=path%>/auditInfo/goAuditUser">
				<i class="icon-group"></i>
				<span class="menu-text"> 用户审核</span>
				<span class="badge badge-primary unaudit-count"></span>
			</a>
		</li>
		
		<li class="${noticeMgrActive}">
			<a href="<%=path%>/noticeMgr/goNoticeMgr">
				<i class="icon-bullhorn"></i>
				<span class="menu-text"> 公告管理</span>
			</a>
		</li>
		
		<li class="${incomeMgrActive}">
			<a href="<%=path%>/userIncomeMgr/goIncomeMgr">
				<i class="icon-inbox"></i>
				<span class="menu-text"> 员工收入</span>
			</a>
		</li>
		
		<li class="${ruleMgrActive}">
			<a href="<%=path%>/incomeRuleMgr/goIncomeRuleMgr">
				<i class="icon-briefcase"></i>
				<span class="menu-text"> 提成规则</span>
			</a>
		</li>
	  </c:if>
	</ul><!-- /.nav-list -->

	<!-- <div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script> -->
</div>