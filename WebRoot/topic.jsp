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
	href="lib/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

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
			<h1 class="page-title">主题管理</h1>
		</div>

		<ul class="breadcrumb">
			<li><a
				href="${pageContext.request.contextPath}/menu!homepage.action">首页</a>
				<span class="divider">/</span>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/menu!periodicalpage.action">期刊</a>
				<span class="divider">/</span>
			</li>
			<li class="active">增加期刊</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			
				<div class="btn-toolbar">
						<button class="btn btn-primary disabled" onClick="window.history.back();">
							<i class="icon-plus"></i>后退
						</button>
					<div class="btn-group"></div>
				</div>
			

				<div class="block">
					<p class="block-heading">
						<s:property value="#request.pmonth" />
						第
						<s:property value="#request.pernum" />
						期刊主题
					</p>
					
					<div class="block-body gallery">
						<s:if test="">
							<div class="alert alert-info">
								<button type="button" class="close" data-dismiss="alert">×</button>
								<strong>提示:</strong> 所选期刊 目前没有主题图片，请先增加，再查看!
							</div>
						</s:if>
						<s:else>
						<div class="row">	
							<s:iterator value="#request.alltopics" var="peris">
								<div class="span3">
      								<div class="alert alert-info">
      								<!--  第二版，点击后，跳转到新的页面，填写所选图片的 详细描述 -->
      								<!-- 在同一个页面，弹出一个 输入框 -->
									<img src="<s:property value="#peris.column2" />" class="img-polaroid" /> 
									<!--  
									<button class="btn" id="add-comment" type="button"><i class="icon-edit"></i>编辑</button>	
									-->
									<!--带 "赞"
									<a class="btn btn-small" href="#" onclick="return post('periodical!approveTopic.action', {periodicalcoverthumb:'<s:property
												value="#request.periodicalcoverthumb" />',topicThunbname:'<s:property value="#peris.topicThumbname" />'});">
												<i class="icon-thumbs-up"></i><s:property value="#peris.column4" /></a>
									-->
									
									<!--跳转到 "评论" -->
									<a class="btn btn-small" href="#" onclick="return post('periodical!commentTopic.action', {periodicalcoverthumb:'<s:property
												value="#request.periodicalcoverthumb" />',topicThunbname:'<s:property value="#peris.topicThumbname" />'});">
												<i class="icon-edit"></i>编辑<s:property value="#peris.column4" /></a>
									
																		
									</div>
      							</div>   
							</s:iterator>
							
								</div>
						</s:else>

						<div class="clearfix"></div>
					</div>
				</div>

				<jsp:include page="footer.jsp"></jsp:include>
				<script type="text/javaScript">
											function post(URL, PARAMS) {
												var temp = document
														.createElement("form");
												temp.action = URL;
												temp.method = "post";
												temp.style.display = "none";
												for ( var x in PARAMS) {
													var opt = document
															.createElement("input");
													opt.name = x;
													opt.value = PARAMS[x];
													// alert(opt.name)        
													temp.appendChild(opt);
												}
												document.body.appendChild(temp);
												temp.submit();
												return temp;
											}
										</script>
			</div>
		</div>
	</div>


	<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="lib/bootstrap/js/bootstrap.js"></script>
	
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

