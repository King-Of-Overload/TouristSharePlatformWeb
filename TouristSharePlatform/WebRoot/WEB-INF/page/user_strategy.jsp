<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta charset="UTF-8">
<title>芳草寻源-攻略&&游记</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet">
<link href="css/user_strategy.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="libs/EpicEditor/js/epiceditor.min.js"></script>
<script src="js/base.js"></script>
<script src="js/user_strategy.js"></script>
<!-- 反转图片 -->
<script src="js/jquery.easing.min.js"></script>
<script type="text/javascript" src="js/modernizr.min.js"></script>
<!-- 评论区相关脚本 -->
<script src="js/jquery.pause.min.js"></script>
<!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<script type="text/javascript">
$(function(){
//@start 图片翻转效果初始化
if ( $('html').hasClass('csstransforms3d') ) {
	  $('.artGroup').removeClass('slide').addClass('flip');
	  $('.artGroup.flip').on('mouseenter',
	    function () {
	      $(this).find('.artwork').addClass('theFlip');
	    });
	  $('.artGroup.flip').on('mouseleave',
	    function () {
	      $(this).find('.artwork').removeClass('theFlip');
	    });
	} else {
	  $('.artGroup').on('mouseenter',
	    function () {
	      $(this).find('.detail').stop().animate({bottom:0}, 500, 'easeOutCubic');
	    });
	  $('.artGroup').on('mouseleave',
	    function () {
	      $(this).find('.detail').stop().animate({bottom: ($(this).height() + -1) }, 500, 'easeOutCubic');
	    });
	}
});
//@end 图片翻转效果初始化
</script>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	
	<div class="content">
		<div class="content-left">
			<!--搜索框-->
			<div class=" input-group input-group-lg">
				<input type="text" class="searchVal form-control"
					placeholder="按作者或者关键字搜攻略..." /> <span class="input-group-btn">
					<input type="button" class="search btn btn-default web-style"
					value="点我,么么哒" />
				</span>
			</div>
			<!-- 筛选条件-->
			<ul class="list-group search-condition">
				<li class="filterByType list-group-item">
				<span>类型:</span>
				<s:if test="strategyTag==null||strategyTag=='全部'">
				<span class="active"><a href="#">全部</a></span> 
				<s:iterator value="tags" var="t">
				<span><a href="javascript:void(0);"><s:property value="#t.ustrategytagname"/></a></span>
				</s:iterator>
				</s:if> 
				<s:else>
				<span><a href="javascript:void(0);">全部</a></span> 
					<s:iterator value="tags" var="t">
					<s:if test="#t.ustrategytagname==strategyTag">
					<span class="active"><a href="#"><s:property value="#t.ustrategytagname"/></a></span>
					</s:if>
					<s:else>
					<span><a href="javascript:void(0);"><s:property value="#t.ustrategytagname"/></a></span>
					</s:else>
					</s:iterator>
				</s:else>
						</li>
				<li class="filterByTime list-group-item"><span>时间：</span>
				<s:if test="timeChosed=='all'">
				<span class="active" data-time="any"><a href="javascript:void(0);">全部</a></span> 
				<span data-time="day"><a href="javascript:void(0);">一天以内</a></span> 
				<span data-time="week"><a href="javascript:void(0);">一周以内</a></span>
				<span data-time="month"><a href="javascript:void(0);">一月以内</a></span>
				</s:if>
				<s:elseif test="timeChosed=='day'">
				<span data-time="any"><a href="javascript:void(0);">全部</a></span> 
				<span  class="active" data-time="day"><a href="javascript:void(0);">一天以内</a></span> 
				<span data-time="week"><a href="javascript:void(0);">一周以内</a></span>
				<span data-time="month"><a href="javascript:void(0);">一月以内</a></span>
				</s:elseif> 
				<s:elseif test="timeChosed=='week'">
				<span data-time="any"><a href="javascript:void(0);">全部</a></span> 
				<span data-time="day"><a href="javascript:void(0);">一天以内</a></span> 
				<span  class="active" data-time="week"><a href="javascript:void(0);">一周以内</a></span>
				<span data-time="month"><a href="javascript:void(0);">一月以内</a></span>
				</s:elseif>
				<s:elseif test="timeChosed=='month'">
				<span data-time="any"><a href="javascript:void(0);">全部</a></span> 
				<span data-time="day"><a href="javascript:void(0);">一天以内</a></span> 
				<span data-time="week"><a href="javascript:void(0);">一周以内</a></span>
				<span class="active" data-time="month"><a href="javascript:void(0);">一月以内</a></span>
				</s:elseif>
				</li>
					<li class="recommand list-group-item">
					<span>本站推荐：</span> 
					<s:if test="recommandTag==null||recommandTag=='全部'">
					<span class="active"><a href="javascript:void(0);">全部</a></span> 
					<span><a href="javascript:void(0);">精品用户</a></span>
				    <span><a href="javascript:void(0);">优质精华</a></span>
					</s:if>
					<s:elseif test="recommandTag=='精品用户'">
					<span><a href="javascript:void(0);">全部</a></span> 
					<span class="active"><a href="javascript:void(0);">精品用户</a></span>
				    <span><a href="javascript:void(0);">优质精华</a></span>
					</s:elseif>
					<s:elseif test="recommandTag=='优质精品'">
					<span><a href="javascript:void(0);">全部</a></span> 
					<span><a href="javascript:void(0);">精品用户</a></span>
				    <span class="active"><a href="javascript:void(0);">优质精华</a></span>
					</s:elseif>
					<s:else>
					<span class="active"><a href="javascript:void(0);">全部</a></span> 
					<span><a href="javascript:void(0);">精品用户</a></span>
				    <span><a href="javascript:void(0);">优质精华</a></span>
					</s:else>
					</li>
			</ul>
			<!--内容列表-->
			<div class="strategy-list-header">
				<div class="strategy-list-sort">
					<a href="javascript:void(0);">按热度<i class=" icon-arrow-down"></i></a> <a href="javascript:void(0);">最新发表<i
						class=" icon-arrow-down"></i></a>
				</div>
				<ol class="breadcrumb">
					<li>一天以内</li>
					<li>一天以内</li>
					<li>一天以内</li>
				</ol>
			</div>
			<div class="strategy-list">
			<!-- 这里放置最新的攻略 -->
				<!--文章modal-->
	<div id="strategy-modal">
	<s:iterator value="displayStrategies" var="sg">
		<div class="strategy-list-item">
			<div class="content-header">
				<ul class="content-info">
					<li><i class="icon-time"></i><span class="time"><s:property value="#sg.ustrategydate"/></span></li>
					<li><i class="icon-eye-open"></i><span class="watched"><s:property value="#sg.uclickednum"/></span></li>
					<li><i class="icon-thumbs-up"></i><span class="up"><s:property value="#sg.ulikecount"/></span></li>
					<li><i class=" icon-comment"></i><span class="comment">99</span></li>
				</ul>
				<h3>
					<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=<s:property value='#sg.ustrategyid'/>" target="_blank"><s:property value="#sg.ustrategyname"/></a>
				</h3>
			</div>
			<!-- @start 用户发表的攻略图文缩略图 -->
			<div class="userStrategyImages">
			<s:iterator value="#sg.imagesList" var="imgList">
			<div class="singleImage">
			<div class="artGroup slide">
			<div class="artwork">
			<a href="#"><img alt="img" src='<s:property value='#imgList'/>' width="204" height="135"/></a>
			<div class="detail">
			<h3><s:property value="#sg.ustrategyname"/></h3>
						<p><s:property value='#sg.ustrategycoverstory'/></p>
			</div></div></div>
			</div>
			</s:iterator>
			</div>
			<!-- @end 用户发表的攻略图文缩略图-->
			<div class="authorArticle">
			<s:property value="#sg.ustrategyplaintext"/>
			</div>
			<ul class="authorInfo">
				<li class="author-headImg"><a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='#sg.tripUser.userid'/>&page=1&requestType=strategy" target="_blank"><img
						src='<s:property value='#sg.tripUser.headerimage'/>' alt="作者头像" /></a>&nbsp;&nbsp;</li>
				<li><span class="authorName"><s:property value='#sg.tripUser.username'/></span>
					&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li><s:property value="#sg.tag"/>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li>一周以内</li>
			</ul>
			<div class="content-body"></div>
		</div>
		</s:iterator>
		
	</div>
	<!-- @end 攻略 -->
			</div>
			<nav>
				<ul class="pagination">
				<s:if test="page!=1">
					<li><a href="javascript:void(0)" id="1">首页</a></li>
					<li><a href="javascript:void(0)" id="<s:property value='page-1'/>">上一页</a></li>
					</s:if>
					<s:if test="page!=totalPage">
					<li><a href="javascript:void(0)" id="<s:property value='page+1'/>">下一页</a></li>
					<li><a href="javascript:void(0)" id="<s:property value='totalPage'/>">尾页</a></li>
					</s:if>
					<li><span>当前页数/总页数：<s:property value="page"/>/<s:property value="totalPage"/></span></li>
				</ul>
			</nav>
		</div>
		<div class="content-right">
		<!-- @start 最新评论 -->
		<div id="box_title">大家正在说</div>
<div id="con">
	<div class="bottomcover" style="z-index:2;"></div>
	<ul>
	<s:if test="comments!=null">
	<s:iterator value="comments" var="ct">
	<li>
			<div class="div_left"><a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=${ct.fromuser.userid}&page=1&requestType=strategy"><img src="${ct.fromuser.headerimage}" title="Jarvis_风" width="50" height="50"></a></div>
			<div class="div_right">
				<a href="http://www.htmleaf.com/" target="_blank">${ct.fromuser.username}</a>：${ct.commentcontent}
				<div class="twit_item_time"><s:date format="yyyy-MM-dd HH:mm:ss" name="#ct.commentdate"/></div>
			</div>
		</li>
	</s:iterator>
	</s:if>
	</ul>
</div>
		<!-- @end 最新评论 -->
		</div>
	</div>


</body>
</html>