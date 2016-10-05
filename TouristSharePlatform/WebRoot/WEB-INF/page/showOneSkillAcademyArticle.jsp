<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芳草寻源-技法学院-${userSkillArticle.skilltitle}-文章详情</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet">
<link href="css/showOneSkillAcademyArticle.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<!-- 分享部分实现 -->
<link href="css/smohan.pop&share.css" rel="stylesheet" />
<script src="js/smohan.pop&share.js"></script>
<!-- 热门话题组件 -->
<script src='js/tagscloud.js'></script>
<!-- 评论区效果 -->
<link href="css/sinaFaceAndEffec.css" rel="stylesheet">
<script src="js/commentMain.js"></script>
<script src="js/sinaFaceAndEffec.js"></script>
<link href="css/commentMain.css" rel="stylesheet">
<!-- 返回最上层按钮核心区 -->
<link href="css/rocketstyle.css" rel="stylesheet">
<script src="js/rocketscript.js"></script>
<!-- 文章解析区核心代码，不要随意修改，需要修改请咨询@Alan -->
<script src="ueditor.parse.js"></script>
<script src="js/showOneSkillAcademyArticle.js"></script>
<script src="js/jquery-sinaEmotion-2.1.0.min.js"></script>
 <link href="css/jquery-sinaEmotion-2.1.0.min.css" rel="stylesheet"/>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="content">
		<div class="content-left">
		<input type="hidden" value="${userSkillArticle.skilid}" class="skillAcademyid"/>
			<!-- @start 精选邮戳，需要进行判断显示该文章是否为精选 -->
			<s:if test="userSkillArticle.isessence==0">
				<div class="threadStamp" id="stamp81975318">
					<img
						src="${pageContext.request.contextPath}/images/bbsUpgradeTu.gif">
				</div>
			</s:if>
			<!-- @end 精选邮戳，需要进行判断显示该文章是否为精选 -->
			<h1 class="h2">
				<s:property value='userSkillArticle.skilltitle' />
			</h1>

			<!-- @start 标题下方显示作者信息以及操作按钮 -->
			<div class="info clearfix">

				<!-- 请注意：去掉 hover类为默认样式 -->
				<div class="shareBtn v-shareBtn pr" id="shareBtn">
					<a class="share" style="cursor: pointer;" rel="nofollow">分享<i></i></a>

				</div>
				<a href="#myComment"
					class="comment-num" id="comment-num">10</a> <span class="txt">
					<s:date name="userSkillArticle.skilldate"
						format="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp; 芳草寻源作者：<s:property
						value='userSkillArticle.user.username' />
				</span>
			</div>
			<div class="skillArticle-txt-wrap" style="font-size: 18px;">
				<!-- 这里显示文章主内容 -->
				<s:property value="userSkillArticle.skillcontent" escape="false" />
			</div>
			<!-- @end 标题下方显示作者信息以及操作按钮 -->
			<!-- @start评论区 -->
			<div class="myComment" id="myComment" style="margin-top: 60px;">

				<div id="content" style="width: 700px; height: auto;">

					<div class="wrap">
						<div class="comment">
							<div class="head-face">
								<img src="" class="messageHeaderImg" />
								<p></p>
							</div>
							<div class="messageContent">
								<div class="cont-box">
									<textarea class="text" placeholder="请输入..." id="commentContent"></textarea>
								</div>
								<div class="tools-box">
									<div class="operator-box-btn">
										<span class="face-icon">☺</span><span class="img-icon">▧</span>
									</div>
									<div class="submit-btn">
										<input type="button" onClick="out()" value="提交评论" />
									</div>
								</div>
							</div>
						</div>
						<div id="info-show">
							<ul></ul>
						</div>
					</div>
					<ul class="commentsList list-group">

					</ul>
				</div>
			</div>
			<!-- @end评论区 -->

			<!--@start 底部相关阅读区域 -->
			<div class="about-read">
				<div class="mod-title">
					<h4 class="h4">相关阅读</h4>
				</div>
				<div class="bd">
					<ul class="txt-list2-hot">
					<s:iterator value="relatedRead" var="rd">
						<li><span class="date"><s:date name="#rd.skilldate" format="yyyy-MM-dd"/></span>· <a
							href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#rd.skilid'/>"
							title="<s:property value='#rd.skilltitle'/> "><s:property value='#rd.skilltitle'/></a></li>
							</s:iterator>
					</ul>
					<ul class="pic-list">
						<s:iterator value="showbanners" var="sba">
							<li><a
								href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#sba.skillId'/>"
								class="pic" title="<s:property value='#sba.bannerTitle'/>"> <img
									src="<s:property value='#sba.bannerImage'/>"
									alt="<s:property value='#sba.bannerTitle'/>">
							</a>
								<p class="title">
									<a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#sba.skillId'/>"
										title="<s:property value='#sba.bannerTitle'/>"><s:property value='#sba.bannerTitle'/></a>
								</p></li>
								</s:iterator>
					</ul>
				</div>
			</div>
			<!-- @end 底部相关区域 -->
		</div>
		<div class="content-right">
			<!-- @start 热门话题滚动区 -->
			<div class="hot-topic">
				<div class="mod-title2">
					<h4 class="h4">相关热门话题</h4>
				</div>
				<div class="bd">
					<div id="tagscloud">
						<s:iterator value="relatedTopic" var="rt">
							<!-- TODO: -->
							<a
								href="${pageContext.request.contextPath}/skillacademy_?topicId=<s:property value='#rt.topicId'/>"
								class="<s:property value='#rt.randomNum'/>"
								title="<s:property value='#rt.topicName'/>"><s:property
									value='#rt.topicName' /></a>
						</s:iterator>
					</div>
				</div>
			</div>
			<!-- @end 热门话题滚动区 -->
			<!-- @start热门文章区 -->
			<div class="hot-article">
				<div class="mod-title3">
					<h4 class="h4">热门文章</h4>
				</div>
				<div class="bd">
					<ul class="txt-list2">
						<s:iterator value="hotAcademy" var="ha">
							<li><em class="hot"></em><a
								href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#ha.skilid'/>"
								title="<s:property value='#ha.skilltitle'/>"><s:property
										value='#ha.skilltitle' /></a></li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- @end热门文章区 -->
			<!-- @start大家在看 -->
			<div class="look">
				<div class="mod-title3">
					<span class="time">2小时内</span>
					<h4 class="h4">大家在看</h4>
				</div>
				<div class="bd">
					<ul class="look-list">
						<s:iterator value="everyOneLookAcademy" var="ela">
							<li class="">
								<p class="title">
									<a
										href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#ela.skillId'/>"
										title="<s:property value='#ela.bannerTitle'/>"><s:property
											value='#ela.bannerTitle' /></a>
								</p>
								<div class="pic-summary">
									<a
										href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#ela.skillId'/>"
										class="pic" title="<s:property value='#ela.bannerTitle'/>">
										<img src="<s:property value='#ela.bannerImage'/>"
										alt="<s:property value='#ela.bannerTitle'/>">
									</a>
									<p class="summary">
										<s:property value='#ela.bannerDescription' />
									</p>
								</div>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- @end大家在看 -->
		</div>
	</div>

	<!-- 火箭回到顶层 -->
	<div style="display: none;" id="rocket-to-top">
		<div style="opacity: 0; display: block;" class="level-2"></div>
		<div class="level-3"></div>
	</div>
</body>
</html>