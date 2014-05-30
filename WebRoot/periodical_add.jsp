<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="武汉华苑艺龙公司,刘立立,窗帘,ipad,iphone,ios,华苑艺龙" />
<meta name="description"
	content="本系统是由刘立立，为武汉华苑艺龙公司开发的 《武汉华苑艺龙公司 App 》后台管理系统，联系方式 836131325@qq.com." />

<title>武汉华苑艺龙公司App后台管理系统</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

<!-- 日期选择器 -->
<link
	href="${pageContext.request.contextPath}/datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">


<!-- Demo page code -->
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->


	<jsp:include page="header.jsp"></jsp:include>

	<jsp:include page="menu.jsp"></jsp:include>




	<div class="content">

		<div class="header">

			<h1 class="page-title">窗帘二级分类</h1>
		</div>

		<ul class="breadcrumb">
			<li><a
				href="${pageContext.request.contextPath}/menu!homepage.action">首页</a>
				<span class="divider">/</span>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/menu!periodicalpage.action">窗帘二级分类</a>
				<span class="divider">/</span>
			</li>
			<li class="active">增加二级分类</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="well">
					<!-- 
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">Profile</a>
						</li>
						<li><a href="#profile" data-toggle="tab">Password</a></li>
					</ul>
					 -->
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">


							<form id="tab" method="post" 
		action="${pageContext.request.contextPath }/periodical!add.action" enctype="multipart/form-data" >
								<label>分类索引时间</label>
								<!-- 这里更换成下拉菜单。现实已添加的一级分类 -->
								<!-- 选择一个时间后，这里做 Ajax 异步请求，得到这个时间对应的 标题 -->
								<div class="controls input-append date form_date" data-date=""
									data-date-format="yyyy-mm-dd" data-link-field="dtp_input2"
									data-link-format="yyyy-mm-dd">
									<input size="16" type="text" value="" id="selectYear" name="magazine.month" readonly /> <span
										class="add-on"><i class="icon-remove"></i> </span> <span
										class="add-on"><i class="icon-th"></i> </span>
									<div style="color:red;font-size:2.5ex;">${addYearError }</div>	
								</div>
								 
								<label>一级分类标题</label>
								<input type="text" value="" id="yeartitle" name="year.title" placeholder="一级分类标题"
									class="input-xlarge" readonly /> 
								
								  	
								<label>二级分类标题</label> <input type="text" value="" name="magazine.title" placeholder="二级分类标题"
									class="input-xlarge" /> 
								
								
								<label>描述</label> <textarea value="Smith" rows="3" name="magazine.synopsis" placeholder="本期描述" class="input-xlarge" ></textarea> 
									
								<label>封面图片</label> <input
									type="file" value="" class="input-xlarge" name="file1" placeholder="封面图片" />
								<!-- 
								<label>目录图片</label>
								<input
									type="file" value="" class="input-xlarge" name="myFile" placeholder="封面图片" />
								 -->
								 <br /><br /><br />
							   <div>
									<input name="submit" value="增加" class="btn btn-primary" id="add" type="submit" />
								</div>
							</form>
						</div>
						
						<!-- 
						<div class="tab-pane fade" id="profile">
							<form id="tab2">
								<label>New Password</label> <input type="password"
									class="input-xlarge">
								<div>
									<button class="btn btn-primary">Update</button>
								</div>
							</form>
						</div>
						 -->

					</div>



				</div>



				<jsp:include page="footer.jsp"></jsp:include>

			</div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/jquery-1.8.1.min.js"
		type="text/javascript" charset="UTF-8"></script>

	<!-- 选择一个时间后，这里做 Ajax 异步请求，得到这个时间对应的 标题 -->
	<script type="text/javascript">
			//日历框值改变，触发 ajax 请求
			$("#selectYear").change(function(){
				var yearvalue = $("#selectYear").val();
				$("#yeartitle").val(yearvalue);
				//${pageContext.request.contextPath }/periodical!selectYear.action
				var url ="periodical!selectYear.action";
				var getdata = {yearmonth:yearvalue};
				//异步请求，简洁版
				$.get(url,getdata,function(data){
					//alert("服务器返回数据:"+data);
					$("#yeartitle").val(data); 
    				});
				
				/*
				$.ajax({url:"periodical!selectYear.action",
					type:"POST",
					data:{yearmonth:yearvalue},
					dataType:"html",
					timeout: 1000,
					error: function(){alert("所选择的年份不存在，请重新选择");},
					success: function(result){alert(result);}
					});
				*/
			});
	</script>

	<!-- 日历 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/datetimepicker/js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			format : "yyyy.mm",
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	</script>
	
	
	<!-- 日历 
<link rel="stylesheet" href="${pageContext.request.contextPath}/jqueryui/themes/base/jquery.ui.all.css">
	<script src="${pageContext.request.contextPath}/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="${pageContext.request.contextPath}/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="${pageContext.request.contextPath}/jqueryui/ui/jquery.ui.datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/jqueryui/jquery.ui.datepicker-zh-TW.js"></script>
	
	<script>
	$(function() {
		$( "#datepicker" ).datepicker( $.datepicker.regional[ "zh-TW" ] );
       
	});
	</script>
	-->

	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>

</body>
</html>

