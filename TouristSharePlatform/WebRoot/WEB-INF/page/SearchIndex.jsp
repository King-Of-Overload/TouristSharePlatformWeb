<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="/struts-tags" prefix="s"%>
   <!DOCTYPE html>
   <html>
   <head>
   <link href="images/favicon.ico" rel="icon" type="image/x-icon" />
   	<title>芳草寻源-搜索主页</title>
   	<link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="libs/highlight/styles/default.css" rel="stylesheet" />
    <link href="css/base.css" rel="stylesheet">
    <script src="libs/jquery.min.js"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="js/base.js"></script>
   	<link rel="stylesheet" type="text/css" href="css/searchindex/searchindex.css">
   	<script src="js/jquery.cxscroll.js"></script>
   	<script src="js/searchindex/searchindex.js"></script>
   </head>
   <body>
   <%@ include file="navbar.jsp"%>
   <!--@start 主内容区-->
   <div class="search-content container">
   <!--@start 左内容区-->
   	<div class="content-left">
   	   <div class="main-title">
   	   <input type="hidden" value="${searchKey}" class="searchKey"/>
<span class="title">旅游相册</span>
<s:if test="albums!=null">
<span class="more">全部<s:property value="albums.size()"/>个相册</span>
</s:if>
<s:else>
<span class="more">全部0个相册</span>
</s:else>
<a href="${pageContext.request.contextPath}/photo_goToUploadImage.php" target="_blank" class="create"><i class="globel-iconfont glyphicon glyphicon-pencil"></i>发相册</a>
</div>
   		<div id="pic_list_1" class="scroll_horizontal">
		<div class="box">
			<ul class="list">
			<s:if test="albums!=null">
			<s:iterator value="albums" var="ab">
			<li>
					<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${ab.albumid}" class="pic-box" target="_blank">
					<s:if test="#ab.isessence==0">
						<div class="praise-box">
							<span class="pic-praise icon-good">优</span>
						</div>
						</s:if>
						<img src="${ab.coverImage}" width="260px" height="145px">
					</a>
					<div class="pic-info-box">
						<a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=${ab.tripUser.userid}&page=1&requestType=strategy" target="_blank">
							<img src="${ab.tripUser.headerimage}" height="50" width="50">
						</a>
						<span class="pictravel-title"><s:property value="#ab.albumname"/></span>
						<div class="pic-info-bottom">
							<spam class="create-time"><s:date name="#ab.uploadtime" format="yyyy/MM/dd"/>上传</spam>
							<span class="view-count">
								<i class="globel-iconfont icon-eye-open"></i>${ab.clickednum}
							</span>
						</div>
					</div>
				</li>
				</s:iterator>
			</s:if>
			<s:else>
			<span>没有搜到任何相册哟</span>
			</s:else>
			</ul>
		</div>
	</div>
	
	<!-- @start 技法学院部分 -->
	  <div class="main-title" style="margin-top: 20px;">
<span class="title">旅游摄影技法大全</span>
<s:if test="academies!=null">
<span class="more">全部<s:property value="academies.size()"/>篇文章</span>
</s:if>
<s:else>
<span class="more">全部0篇文章</span>
</s:else>
<a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyEdit.php" target="_blank" class="create"><i class="globel-iconfont glyphicon glyphicon-pencil"></i>发技法</a>
</div>
   		<div id="academy-list" class="scroll_horizontal">
		<div class="box">
			<ul class="list">
			<s:if test="academies!=null">
			<s:iterator value="academies" var="ad">
			<li>
					<a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=${ad.skilid}" class="pic-box" target="_blank">
					<s:if test="#ad.isessence==0">
					<div class="praise-box">
							<span class="pic-praise icon-good">优</span>
						</div>
					</s:if>
						<img src="${ad.coverImage}" width="260px" height="145px">
					</a>
					<div class="pic-info-box">
						<a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=${ad.user.userid}&page=1&requestType=strategy" target="_blank">
							<img src="${ad.user.headerimage}" height="50" width="50">
						</a>
						<span class="pictravel-title"><s:property value="#ad.skilltitle"/></span>
						<div class="pic-info-bottom">
							<spam class="create-time"><s:date name="#ad.skilldate" format="yyyy/MM/dd"/>发表</spam>
							<span class="view-count">
								<i class="globel-iconfont icon-eye-open"></i>${ad.clickednum}
							</span>
						</div>
					</div>
				</li>
				</s:iterator>
			</s:if>
			<s:else>
			<span>没有搜到任何技法学院素材哟</span>
			</s:else>
			</ul>
		</div>
	</div>
	<!-- @end 技法学院部分 -->
	<!--@start 攻略游记部分-->
	<div class="strategy main-mod main-notes" style="margin-top: 45px;">
		 <div class="main-title">
<span class="title">游记&&攻略</span>
<span class="more">全部1585篇</span>
<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyEdit.php" target="_blank" class="create"><i class="globel-iconfont glyphicon glyphicon-pencil"></i>写攻略</a>
</div>
<div class="related-notes">
<div class="wonder-notes-bd" id="notes">
<div id="J-wonderNotesWrap">
<div class="notes-box">
<div class="notes-pic">
<a target="_blank" href="" class="strategyHref">
<img alt="" src="images/searchindex/temp_pic_1.jpg" width="215" height="145">
</a>
</div>
<div class="notes-status">
<span class="praised-icon icon-praised" title="">精</span>
</div>
<div class="notes-info">
<a id="notes-title" target="_blank" title="" href="">行摄吃喝在扬州--附扬州最强美食攻略!</a>
<span class="comment-count"><i class="globel-iconfont icon-comment"></i>126</span>
<span class="view-cont"><i class="globel-iconfont icon-eye-open"></i>25708</span>
<p class="notes-content">..扬州，烟花三月下扬州吃炒饭，正值元宵佳节，第一次吃到蔬菜馅儿的汤圆儿关于汤圆和元宵南方宝宝的解释是，大的有馅儿的是汤圆，小的没馅儿的是元宵...东...</p>
</div>
</div>
</div>
<div class="pagelist-wrapper" id="J_pagelist-wrapper">
			<nav>
				<ul class="pagination">
					<li><a href="javascript:void(0)" id="1">首页</a></li>
					<li><a href="javascript:void(0)" id="<s:property value='page-1'/>">上一页</a></li>
					<li><a href="javascript:void(0)" id="<s:property value='page+1'/>">下一页</a></li>
					<li><a href="javascript:void(0)" id="<s:property value='totalPage'/>">尾页</a></li>
					<li><span>当前页数/总页数：1/6</span></li>
				</ul>
			</nav>
</div>
</div>
</div>
	</div>
	<!--@end 攻略游记部分-->
   	</div>
   	<!--@end 左内容区-->
   	<!--@start 右内容区-->
   	<div class="content-right">
   	   		<div class="rai-dlod _j_search_section">
   		   		<div class="ser-title" style="border-bottom: 0 none;">
   		   		<h2><a href="javascript:void(0);">热门攻略下载</a></h2>
   		   		<a href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php" target="_blank">更多攻略</a>
   		   		</div>
   		   		<ul style="width: 100%;">
   		   		<s:if test="recommands!=null">
   		   		<s:iterator value="recommands" var="rd">
   		   		<li>
   		   		<div class="flt1">
   		   		<a href="javascript:void(0);" target="_blank">
   		   		<img alt="${rd.ostrategyname}" src="${rd.ostrategyimage}" width="90" height="130">
   		   		</a>
   		   		</div>
   		   		<div class="dwn-nr">
   		   		<h3><a href="javascript:void(0);">
   		   		<span class="sr-keyword">${rd.ostrategyname}</span>
   		   		</a></h3>   
   		   		<p>已下载:${rd.clickednum}次</p>
   		   		<a href="${pageContext.request.contextPath}/pdfReader/web/documents/pdf/${rd.ostrategyurl}" target="_blank" class="down-btn _j_search_link">免费下载</a>		   		
   		   		</div>
   		   		</li>
   		   		</s:iterator>
   		   		</s:if>
   		   		</ul>
   		</div>
   		<div class="rai-dlod _j_search_section">
   		   		<div class="ser-title" style="border-bottom: 0 none;">
   		   		<h2><a href="javascript:void(0);">${searchKey}相关攻略下载</a></h2>
   		   		<a href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php" target="_blank">更多攻略</a>
   		   		</div>
   		   		<ul style="width: 100%;">
   		   		<s:if test="ostrategies!=null">
   		   		<s:iterator value="ostrategies" var="os">
   		   		<li>
   		   		<div class="flt1">
   		   		<a href="javascript:void(0);" target="_blank">
   		   		<img alt="${searchKey}" src="${os.ostrategyimage}" width="90" height="130">
   		   		</a>
   		   		</div>
   		   		<div class="dwn-nr">
   		   		<h3><a href="javascript:void(0);">
   		   		<span class="sr-keyword">${os.ostrategyname}</span>
   		   		</a></h3>   
   		   		<p>已下载:${os.clickednum}次</p>
   		   		<a href="${pageContext.request.contextPath}/pdfReader/web/documents/pdf/${os.ostrategyurl}" target="_blank" class="down-btn _j_search_link">免费下载</a>		   		
   		   		</div>
   		   		</li>
   		   		</s:iterator>
   		   		</s:if>
   		   		<s:else>
   		   		<li><span>小源没有找到相关官方攻略哦</span></li>
   		   		</s:else>
   		   		</ul>
   		</div>
   	</div>
   	<!--@end 右内容区-->
   </div>
   <!--@end 主内容区-->
   
      <!-- @start footer区 -->
     <div class="searchfooter">
		<ul class="footer_nav">
		  <li><a href="${pageContext.request.contextPath}/index.php">芳草寻源首页</a></li>
		  <li><a href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php">官方攻略</a></li>
		  <li><a href="${pageContext.request.contextPath}/userstrategy_goToUserStrategy.php?page=1">攻略游记大全</a></li>
		  <li><a href="${pageContext.request.contextPath}/photo_goToPhoto.php">精美相册</a></li>
		  <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php">技法学院与素材</a></li>
		  <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php">旅游装备</a></li>
		  <li><a href="${pageContext.request.contextPath}/downloadPage/download.html">芳草寻源客户端</a></li>
		</ul>
		<p class="copy">Copyright &copy; 2016.芳草寻源小组倾情出品 </p>
   <p class="copy">Copyright &copy; 2016.Team of the beauty-from-the-Fragrant-grass All rights reserved</p>
   <p class="copy">Copyright &copy; 2016. 芳香 のある 源 すべての権利が確保した</p>

</div> 
   <!-- @end footer区 -->
   </body>
   </html>