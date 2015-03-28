<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>

<!--[if !IE]> -->

<script type="text/javascript">
	window.jQuery || document.write("<script src='"+path+"/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='"+path+"/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

<script type="text/javascript">
	if("ontouchend" in document) document.write("<script src='"+path+"/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
<script src="<%=path %>/assets/js/bootstrap.min.js"></script>
<script src="<%=path %>/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="<%=path %>/assets/js/excanvas.min.js"></script>
<![endif]-->

<script src="<%=path %>/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="<%=path %>/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%=path %>/assets/js/jquery.slimscroll.min.js"></script>
<script src="<%=path %>/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="<%=path %>/assets/js/jquery.sparkline.min.js"></script>
<script src="<%=path %>/assets/js/flot/jquery.flot.min.js"></script>
<script src="<%=path %>/assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="<%=path %>/assets/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->

<script src="<%=path %>/assets/js/ace-elements.min.js"></script>
<script src="<%=path %>/assets/js/ace.min.js"></script>
<%-- <script src="<%=path %>/assets/js/bootbox.min.js"></script> --%>
<script type="text/javascript">
	//bootbox.setLocale("zh_CN");
	$(function(){
		$.ajax({
			url:path+'/user/getDownlineCount',
			type:"post",
			dataType:"json",
			success:function(r){
				$('.downline-count').text(r.downlineCount);
			},
			error:function(){
				
			}
		});
	});
</script>