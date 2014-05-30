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
<!-- 消息提示框，样式... -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/build/css/messenger.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/build/css/messenger-spinner.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/build/css/messenger-theme-air.css">
	

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
			<li class="active">增加每张图片的描述</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			
					<div class="btn-toolbar">
						<button class="btn btn-primary disabled" onClick="window.history.back();">
							<i class="icon-plus"></i>后退
						</button>
						<div class="btn-group"></div>
					</div>
				 
					 <!-- 设置单张图片的信息 -->
					<div class="block" id="add-comment-form">
                			<p class="block-heading">设置该张图片详细信息</p>
                			<div class="block-body">
                			    <div class="row-fluid">
                				
                    				<p>请填写该窗帘图片的相关信息(所有输入框都必须填写内容).</p>
                    				<div style="color:red;font-size:2.5ex;">${addCommentError }</div>
                    				<img alt="所修改的图片" src="./<s:property value='#request.topicimgPath' />" width="240" height="180" />
                    				
                    				
                    				<s:if test="#request.commentEntity==null">
                    					<form id="tab" class="form-inline" method="post" 
		action="${pageContext.request.contextPath }/periodical!addComment.action">
									<!-- 两个隐藏表单，保存上一页传递过来的两个值 -->
									<input type="hidden" name="topicThunbname" 
										value="<s:property value='#request.topicThunbname' />" /><br />
									<input type="hidden" name="periodicalcoverthumb" 
										value="<s:property value='#request.periodicalcoverthumb' />" /><br />
		
                    					<label>名称</label><br/>
									<input type="text" value="" id="commtitle" name="comm.column1" placeholder="窗帘名称" class="input-xlarge" /><br/>
								
									<label>淘宝链接</label><br/>
									<input type="text" value="" id="commurl" name="comm.column2" placeholder="淘宝链接" class="input-xlarge" /><br/> 
								
									<label>描述</label><br/>
									<textarea value="Smith" rows="3" id="commdesc" name="comm.comment" placeholder="产品描述" class="input-xlarge" ></textarea><br/> 
									<br /><br />
									<div>
										<input name="submit" value="增加" class="btn btn-primary" id="addcomment" type="submit" />
									</div>
           
                					</form>
                    				</s:if>
                    				<s:else>
                    					<form id="tab" class="form-inline" method="post" 
		action="${pageContext.request.contextPath }/periodical!editComment.action">
									<!-- 两个隐藏表单，保存上一页传递过来的两个值 -->
									<input type="hidden" name="topicThunbname" 
										value="<s:property value='#request.topicThunbname' />" /><br />
									<input type="hidden" name="periodicalcoverthumb" 
										value="<s:property value='#request.periodicalcoverthumb' />" /><br />
									<input type="hidden" name="editcommentid" value="<s:property value='#request.commentEntity.id' />" />
		
                    					<label>名称</label><br/>
									<input type="text"  id="commtitle2" name="comm.column1" placeholder="窗帘名称" value="<s:property value='#request.commentEntity.column1' />" class="input-xlarge" /><br/>
								
									<label>淘宝链接</label><br/>
									<input type="text"  id="commurl2" name="comm.column2" placeholder="淘宝链接" value="<s:property value='#request.commentEntity.column2' />" class="input-xlarge" /><br/> 
								
									<label>描述</label><br/>
									<textarea  rows="3" id="commdesc2" name="comm.comment" placeholder="产品描述" class="input-xlarge" ><s:property value='#request.commentEntity.comment' /></textarea><br/> 
									<br /><br />
									<div>
										<input name="submit" value="修改" class="btn btn-primary" id="editcomment" type="submit" />
									</div>
           
                					</form>
                    				</s:else>
                    				
                    				
                    
                    
                    
                    
                    
                    
                    				<div class="clearfix"></div>
                    			</div>
                			</div>
            			</div>
            			<!-- ----------------- -->
				
				<jsp:include page="footer.jsp"></jsp:include>

			</div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<!-- 弹出提示框  -->
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/build/js/messenger.js"></script>
    <script type="text/javascript">
		$(document).ready(function(){
			//选择好弹出框的样式
			$._messengerDefaults = {
				extraClasses: 'messenger-fixed messenger-theme-air messenger-on-top'
			};
			
			$("#addcomment").click(function(){
				var commdec = $("#commdesc").val();
				var title = $("#commtitle").val();
				var url = $("#commurl").val();
				if(title == ""){
					$.globalMessenger().post({
						message: "该款窗帘名称不为空!",
					    hideAfter: 3,
					    type: 'message',
					});
					return false;
				}
				if(url == ""){
					$.globalMessenger().post({
						message: "淘宝链接不能为空!",
					    hideAfter: 3,
					    type: 'message',
					});
					return false;
				}
				if(commdec == ""){
					$.globalMessenger().post({
						message: "该款窗帘描述不为空!",
					    hideAfter: 3,
					    type: 'message',
					});
					return false;
				}
				return true;
			});
			
			
			
			//修改评论
			$("#editcomment").click(function(){
				var commdec = $("#commdesc2").val();
				var title = $("#commtitle2").val();
				var url = $("#commurl2").val();
				if(title == ""){
					$.globalMessenger().post({
						message: "该款窗帘名称不为空!",
					    hideAfter: 3,
					    type: 'message',
					});
					return false;
				}
				if(url == ""){
					$.globalMessenger().post({
						message: "淘宝链接不能为空!",
					    hideAfter: 3,
					    type: 'message',
					});
					return false;
				}
				if(commdec == ""){
					$.globalMessenger().post({
						message: "该款窗帘描述不为空!",
					    hideAfter: 3,
					    type: 'message',
					});
					return false;
				}
				return true;
			});
			
			
			
			
		});
  </script>
  
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

