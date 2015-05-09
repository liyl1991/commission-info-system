<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<c:if test="${loginedUser.userRole==1 }">
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
		$.ajax({
			url:path+'/notice/getNoticeListWithStatus',
			type:"get",
			dataType:"json",
			success:function(r){
				$('.notice-nav .unread-count').text(r.result.length);
				for(var i=0;i<r.result.length;i++){
					var htm = 
						'<li>'+
						'	<a href="'+path+'/notice/goNoticeView/'+r.result[i].id+'">'+
						'		<span class="msg-body">'+
						'			<span class="msg-title">'+
						'				<span class="blue">'+r.result[i].title+'</span>'+
						'			</span>'+
						'			<span class="msg-time">'+
						'				<i class="icon-time"></i>'+
						'				<span>'+formatDate2(r.result[i].createDate)+'</span>'+
						'			</span>'+
						'		</span>'+
						'	</a>'+
						'</li>';
					$('.notice-nav .dropdown-navbar .dropdown-header').after(htm);
				}
				function formatDate2(str){
					if(str){
						var dt = new Date(str);
					  	var yy = dt.getFullYear();
					  	var mm= dt.getMonth()+1<10?'0'+(dt.getMonth()+1):(dt.getMonth()+1);
					  	var dd = dt.getDate()<10?'0'+dt.getDate():dt.getDate();
					  	var hh = dt.getHours()<10?'0'+dt.getHours():dt.getHours();
					  	var mi = dt.getMinutes()<10?'0'+dt.getMinutes():dt.getMinutes();
					  	return yy+"年"+mm+"月"+dd+"日";
					}else{
						return '';
					}
				}
			},
			error:function(){
				
			}
		});
	});
</script>
</c:if>
<c:if test="${loginedUser.userRole==2 }">
<script type="text/javascript">
	//bootbox.setLocale("zh_CN");
	$(function(){
		$.ajax({
			url:path+'/auditInfo/getUnAtuditCount',
			type:"post",
			dataType:"json",
			success:function(r){
				$('.unaudit-count').text(r.unAtuditCount);
			},
			error:function(){
				
			}
		});
	});
</script>
</c:if>