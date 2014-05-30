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

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

    
    
    <!-- 弹出提示框 -->
    <!-- include stylesheets here... -->
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/build/css/messenger.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/build/css/messenger-spinner.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/build/css/messenger-theme-air.css">
    
    <!-- Demo page code -->
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
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
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
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
            <h1 class="page-title">窗帘一级分类设置</h1>
        </div>
        
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/menu!homepage.action">首页</a> <span class="divider">/</span></li>
            <li class="active">窗帘一级分类设置</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<s:if test="#request.yearlist==null">
   <div class="row-fluid">
	<!--  第一次使用前的提示 -->
    <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <strong>提示:</strong> 杂志的结构为&nbsp;&nbsp;&nbsp;窗帘一级分类->窗帘二级分类->二级分类内所有图片->每张图片描述。所以必须先定义好窗帘一级分类!
    </div>
</div>
</s:if>


<div class="row-fluid">

						<div class="span4">
						
						<form class="form-inline" method="post" 
		action="${pageContext.request.contextPath }/magazine!addYear.action">
									
							<label class="control-label" for="input01">增加分类编号</label><br />
							<input type="text" class="x-large" id="yearname" name="year.yearname" />
							<p>格式为阿拉伯四位数字年份，如 2013</p>
							
							<label class="control-label" for="input01">增加窗帘一级分类名称</label><br />
							<input type="text" class="x-large" id="yeartitle" name="year.title" />
							<p>输入窗帘的一级分类，如 田园风格 </p>
							
							<div style="color:red;font-size:1.5ex;">${addYearError }</div>
							<input name="submit" value="增加" class="btn" id="addyear" type="submit" />
						</form>
							
						</div>
						
						<div class="span8">
						
							<p>窗帘一级分类列表</p>
							
							<table class="table table-striped table-bordered ">
								<colgroup>
									<col class="span2">
									<col class="span2">
									<col class="span2">
									<col class="span2">
									
								</colgroup>
								<thead>
									<tr>
										<th>分类编号</th>
										<th>分类名称</th>
										<th>操作</th>
									</tr>
									
								</thead>
								<tbody>
									<!-- 如果 集合对象中没有任何数据，提示用户，当前没有数据 -->
       								<s:if test="#request.yearlist==null">
       									<td colspan="3">
         									抱歉当前数据库中没有一级分类信息，请增加! 
            								</td>	
       								</s:if>
       								
									<s:iterator value="#request.yearlist" var="info">
        								<tr>
            								<td><s:property value="yearname" /></td>
            								<td><s:property value="title" /></td>
            								<s:if test="#info.statu==0">
            								<td>
            									<!-- 状态为被删除的，还原 -->
              								<a href="${pageContext.request.contextPath }/magazine!updateYear.action?yid=<s:property value="id" />"><i class="icon-ok"></i></a><span style="font-size:1.5ex;">还原删除的分类</span>
            								</td>
            								</s:if>
            								<s:if test="#info.statu==1">
            								<td>
            									<!-- 编辑 -->
            									<a href="${pageContext.request.contextPath }/magazine!toEditYear.action?yid=<s:property value="id" />"><i class="icon-pencil"></i></a>&nbsp;&nbsp;
              								<!-- 删除 -->
              								<a href="${pageContext.request.contextPath }/magazine!deleteYear.action?yid=<s:property value="id" />"><i class="icon-remove"></i></a>
            								</td>
            								</s:if>
          							</tr>
									</s:iterator>
								</tbody>
							</table>
					
						</div>
			
</div>


<jsp:include page="footer.jsp"></jsp:include>
                    
            </div>
        </div>
    </div>
    


    <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
	<!-- 弹出提示框  -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/build/js/messenger.js"></script>
  	<script type="text/javascript">
		$(document).ready(function(){
			//选择好弹出框的样式
			$._messengerDefaults = {
				extraClasses: 'messenger-fixed messenger-theme-air messenger-on-top'
			};
			
			//$.globalMessenger().post("请输入用户名和密码!");
			/*
			$.globalMessenger().post({
		    	message: '请输入年份!',
		    	type: 'error',
		    	hideAfter: 6,
		    	showCloseButton: true
			});
			*/
			
			$("#addyear").click(function(){
				var name = $("#yearname").val();
				var title = $("#yeartitle").val();
				if(name == ""){
					$.globalMessenger().post({
						message: "分类编号不能为空!",
					    hideAfter: 4,
					    type: 'error',
					});
					return false;
				}
				if(title == ""){
					$.globalMessenger().post({
						message: "分类名称不能为空!",
					    hideAfter: 4,
					    type: 'error',
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
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>
