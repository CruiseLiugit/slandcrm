<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!-- 登录判断 -->
<div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    <!-- 
                    <li><a href="${pageContext.request.contextPath}/menu!magazinepage.action" class="hidden-phone visible-tablet visible-desktop" role="button"><span class="badge badge-error">1</span>杂志设置</a></li>
                     -->
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i><s:property value="#session.username" />
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">我的账户</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" class="visible-phone" href="${pageContext.request.contextPath}/#"></a></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="${pageContext.request.contextPath}/userinfo!logout.action">退出</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="#"><span class="first">武汉华苑艺龙有限公司</span>--<span class="second">华苑艺龙 App 后台管理系统</span></a>
        </div>
    </div>
    
