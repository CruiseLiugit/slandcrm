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

<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

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
				<span class="divider">/</span></li>
			<li class="active">窗帘二级分类</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			
				<div class="btn-toolbar">
					<!-- periodical_add.jsp -->
					<s:if test="#request.yearSize==0">
						<button class="btn btn-primary disabled">
							<i class="icon-plus"></i>新增窗帘一级分类
						</button>
					</s:if>
					<s:else>
						<a
							href="${pageContext.request.contextPath}/periodical!toadd.action">
							<button class="btn btn-primary">
								<i class="icon-plus"></i>新增窗帘二级分类
							</button> </a>
					</s:else>

					<div class="btn-group"></div>
				</div>


				<div class="well">
					
					<div class="accordion" id="accordion2">
						<s:if test="#request.yearSize==0">
							<div class="alert alert-info">
								<button type="button" class="close" data-dismiss="alert">×</button>
								<strong>提示:</strong> 目前没有窗帘一级分类，无法增加窗帘二级分类，请先在 “窗帘一级分类”模块中设置一级分类名称!
							</div>
						</s:if>
						<s:else>

							<s:if test="#request.detaillistsize==0">
								<s:iterator value="#request.periodicallist" var="peris">
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												onclick="return post('periodical!findAll.action', {cm1:'<s:property
												value="yearmonth" />'}); ;"
												data-parent="#accordion2"
												href="#<s:property value="month" />"> <s:property
													value="yearmonth" />-<s:property value="title" /> </a>
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

										<div id="<s:property value="month" />"
											class="accordion-body collapse">
											<!-- <div id="collapseOne" class="accordion-body collapse in">默认展开 -->
											<div class="accordion-inner">
												<div class="block span12">
													<div class="row-fluid">
														&nbsp;&nbsp; <a href="#"><i
															class="icon-pencil"></i> </a>&nbsp;&nbsp; <a href="#myModal"
															role="button" data-toggle="modal"><i
															class="icon-remove"></i> </a>
														<div class="btn-group"></div>
													</div>
													<div class="block-body gallery">

														<img src="images/170x170.gif" class="img-polaroid">
														<img src="images/170x170.gif" class="img-polaroid">
														<img src="images/170x170.gif" class="img-polaroid">
														<img src="images/170x170.gif" class="img-polaroid">

														<div class="clearfix"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</s:iterator>
							</s:if>
							<s:else>
								<!-- 点击过某一期，看明细 -->
								<s:iterator value="#request.periodicallist" var="peris">
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse"
												onclick="return post('periodical!findAll.action', {cm1:'<s:property
												value="yearmonth" />'}); ;"
												data-parent="#accordion2"
												href="#<s:property value="month" />"> <s:property
													value="yearmonth" />-<s:property value="title" /> </a>
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

										<s:if test="#request.detailmonth == #peris.yearmonth">
											<div id="collapseOne" class="accordion-body collapse in">
												<div class="accordion-inner">
													<div class="block span12">
														<div class="row-fluid">
															&nbsp;&nbsp; <a href="#" onclick="return post('periodical!findByPid.action', {pmonth:'<s:property
												value="yearmonth" />'}); ;"><i
																class="icon-pencil"></i> </a>&nbsp;&nbsp; 
																
															<!--  删除按钮	
															<a href="#myModal"
																role="button" data-toggle="modal"><i
																class="icon-remove"></i> </a>
															-->
															<div class="btn-group"></div>
														</div>
														<div class="block-body gallery">


															<!-- 期刊缩略图列表 -->
															<s:iterator value="#request.detaillist" var="peris">
																<img src="<s:property
												value="thumimage" />"
																	class="img-polaroid" width="170" height="170"
																	title="<s:property
												value="title" />">
															</s:iterator>

															<div class="clearfix"></div>
														</div>
													</div>
												</div>
											</div>

										</s:if>
										<s:else>
											<div id="<s:property value="month" />"
												class="accordion-body collapse">
												<div class="accordion-inner">
													<div class="block span12">
														<div class="row-fluid">
															&nbsp;&nbsp; <a href="user.html"><i
																class="icon-pencil"></i> </a>&nbsp;&nbsp; <a href="#myModal"
																role="button" data-toggle="modal"><i
																class="icon-remove"></i> </a>
															<div class="btn-group"></div>
														</div>
														<div class="block-body gallery">

															<img src="images/170x170.gif" class="img-polaroid">
															<img src="images/170x170.gif" class="img-polaroid">
															<img src="images/170x170.gif" class="img-polaroid">
															<img src="images/170x170.gif" class="img-polaroid">

															<div class="clearfix"></div>
														</div>
													</div>
												</div>
											</div>

										</s:else>
										<!-- <div id="collapseOne" class="accordion-body collapse in">默认展开 -->



									</div>
								</s:iterator>
							</s:else>

						</s:else>


					</div>


				</div>

				<!-- 
				<s:if test="#request.yearSize!=0">
					<div class="pagination">
						<ul>
							<li><a href="#">上一页</a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">下一页</a></li>
						</ul>
					</div>
				</s:if>
 				-->
 
				<div class="modal small hide fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">删除提示</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i>您确定要删除该窗帘二级分类以及附带的所有内容?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<button class="btn btn-danger" data-dismiss="modal">删除</button>
					</div>
				</div>

				<jsp:include page="footer.jsp"></jsp:include>

			</div>
		</div>
	</div>



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

