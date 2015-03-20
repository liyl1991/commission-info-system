<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>500错误</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<jsp:include page="inc.jsp"></jsp:include>
	</head>

	<body>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->

					<div class="error-container">
						<div class="well">
							<h1 class="grey lighter smaller">
								<span class="blue bigger-125">
									<i class="icon-random"></i>
									500
								</span>
								服务器内部发生错误
							</h1>

							<hr />
							<h3 class="lighter smaller">
								我们将尽快
								<i class="icon-wrench icon-animated-wrench bigger-125"></i>
								修复!
							</h3>

							<hr />
							<div class="space"></div>

							<div class="center">
								<a href="javascript:history.go(-1);" class="btn btn-grey">
									<i class="icon-arrow-left"></i>
									返回
								</a>
							</div>
						</div>
					</div>

					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
</body>
</html>
