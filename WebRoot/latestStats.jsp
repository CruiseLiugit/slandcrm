<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<div class="block">
        <a href="#page-stats" class="block-heading" data-toggle="collapse">最新数据统计</a>
        <div id="page-stats" class="block-body collapse in">

            <div class="stat-widget-container">
                <div class="stat-widget">
                    <div class="stat-button">
                        <p class="title"><s:property value="#session.magazinesnum" /></p>
                        <p class="detail">总二级分类数</p>
                    </div>
                </div>

                <div class="stat-widget">
                    <div class="stat-button">
                        <p class="title"><s:property value="#session.topicsnum" /></p>
                        <p class="detail">总图片数</p>
                    </div>
                </div>
				<!-- 
                <div class="stat-widget">
                    <div class="stat-button">
                        <p class="title"><s:property value="#session.commentsnum" /></p>
                        <p class="detail">总评论数</p>
                    </div>
                </div>
				 -->
            </div>
        </div>
    </div>
