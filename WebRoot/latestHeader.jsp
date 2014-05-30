<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<div class="stats">
	<p class="stat">
		<span class="number"><s:property value="#session.topicsnum" />
		</span>图片数
	</p>
	<p class="stat">
		<span class="number"><s:property value="#session.magazinesnum" />
		</span>二级分类数
	</p>
</div>
