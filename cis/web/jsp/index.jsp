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
		<link rel="stylesheet" href="<%=path %>/css/unicorn.main.css" />
		<link rel="stylesheet" href="<%=path %>/css/unicorn.blue.css" class="skin-color" />
	<body>
		
		<jsp:include page="/jsp/sidebar.jsp"></jsp:include>
		
		<!-- <div id="style-switcher">
			<i class="icon-arrow-left icon-white"></i>
			<span>Style:</span>
			<a href="#grey" style="background-color: #555555;border-color: #aaaaaa;"></a>
			<a href="#blue" style="background-color: #2D2F57;"></a>
			<a href="#red" style="background-color: #673232;"></a>
		</div> -->
		
		<div id="content">
			<!-- 导航条 -->
			<div id="breadcrumb">
				<a href="<%=path %>/jsp/index.jsp" title="回到首页" class="tip-bottom"><i class="icon-home"></i> 首页</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12 center" style="text-align: center;">					
						<ul class="stat-boxes">
							<li>
								<div class="left peity_bar_good"><span>100,88,98,78,120,107,172</span>+20%</div>
								<div class="right">
									<strong>36094</strong>
									上月业绩
								</div>
							</li>
							<li>
								<div class="left peity_bar_neutral"><span>20,15,18,14,10,9,9,9</span>0%</div>
								<div class="right">
									<strong>1433</strong>
									上月收入
								</div>
							</li>
							<li>
								<div class="left peity_bar_bad"><span>3,5,9,7,12,20,10</span>-50%</div>
								<div class="right">
									<strong>8650</strong>
									总业绩
								</div>
							</li>
							<li>
								<div class="left peity_line_good"><span>12,6,9,23,14,10,17</span>+70%</div>
								<div class="right">
									<strong>8650</strong>
									总收入
								</div>
							</li>
						</ul>
					</div>	
				</div>
				<div class="row-fluid">
				    <div class="span2"></div>
					<div class="span8">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>各月信息</h5>
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered data-table">
									<thead>
										<tr>
											<th>月份</th>
											<th>业绩</th>
											<th>收入</th>
											<th>是否达标</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>2014年2月</td>
											<td>129929.8</td>
											<td>12992</td>
											<td><i class="icon-ok"></i></td>
										</tr>
										<tr>
											<td>2015年1月</td>
											<td>129929.8</td>
											<td>12992</td>
											<td><i class="icon-ok"></i></td>
										</tr>
										<tr>
											<td>2014年12月</td>
											<td>129929.8</td>
											<td>12992</td>
											<td><i class="icon-ok"></i></td>
										</tr>
										<tr>
											<td>2014年11月</td>
											<td>129929.8</td>
											<td>12992</td>
											<td><i class="icon-ok"></i></td>
										</tr>
										<tr>
											<td>2014年10月</td>
											<td>1299.8</td>
											<td>129</td>
											<td><i class="icon-remove"></i></td>
										</tr>
										<tr>
											<td>2014年9月</td>
											<td>129929.8</td>
											<td>12992</td>
											<td><i class="icon-ok"></i></td>
										</tr>
									</tbody>
								</table>							
							</div>
						</div>
					</div>
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
            <script src="<%=path %>/js/unicorn.js"></script>
            <script src="<%=path %>/js/unicorn.dashboard.js"></script>
            <script src="<%=path %>/js/unicorn.tables.js"></script>
	</body>
	
</html>
