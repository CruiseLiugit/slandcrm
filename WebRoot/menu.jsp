<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



    <div class="sidebar-nav">
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>工作台</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="${pageContext.request.contextPath}/menu!homepage.action">首页</a></li>
            <li ><a href="${pageContext.request.contextPath}/menu!magazinepage.action"><span class="badge badge-warning">0</span>窗帘一级分类</a></li>
            <li ><a href="${pageContext.request.contextPath}/menu!periodicalpage.action"><span class="badge badge-warning">1</span>窗帘二级分类</a></li>
            <!-- 
            <li ><a href="${pageContext.request.contextPath}/menu!topicpage.action"><span class="badge badge-success">3</span>主题</a></li>
            <li ><a href="${pageContext.request.contextPath}/comments!findUnCheckComment.action"><span class="badge badge-info">3</span>评论</a></li>
        		-->
        </ul>

        <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>账户管理</a>
        <ul id="accounts-menu" class="nav nav-list collapse">
            <li ><a href="${pageContext.request.contextPath}/menu!register.action">注册新用户</a></li>
            <li ><a href="${pageContext.request.contextPath}/menu!modifyPwd.action">修改密码</a></li>
        </ul>

		<!-- ${pageContext.request.contextPath}/faq.html
        <a href="#" class="nav-header" ><i class="icon-question-sign"></i>使用说明</a>
        -->
    </div>
    