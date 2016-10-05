<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta property="wb:webmaster" content="8eccc07f4c5140c0" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<title>芳草寻源首页-おはようございます</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
<link href="css/rocketstyle.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="js/jbase64.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<script src="js/home.js"></script>
<script src="js/wow.js"></script>
<script src="js/rocketscript.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<!--<script src="js/jquery.mousewheel.js"></script>-->

<script src="js/jquery.contentcarousel.js"></script>
<link href="css/animate.css" rel="stylesheet">
<!-- 轮播图相关 -->
<link rel="stylesheet" href="css/indexrollpicture.css">
<script type="text/javascript" src="js/jquery.cxslide.min.js"></script>
<!-- 菜单相关 -->
<script src="js/megamenu.js"></script> 
<style type="text/css">.menuOuter{background-color: #f0f0f0;}</style>
<link rel="stylesheet" href="css/menustyle.css">
<!-- 动态超链接效果 -->
<script src="js/anchorHoverEffect.js"></script>
<link rel="stylesheet" href="css/anchorHoverEffect.css">
<script type="text/javascript">
window.onload=function(){
	var wow = new WOW({
	    boxClass: 'wow',
	    animateClass: 'animated',
	    offset: 0,
	    mobile: true,
	    live: true
	});
	wow.init();
};     

jQuery(document).ready(function(){
	if( $('.cd-stretchy-nav').length > 0 ) {
		var stretchyNavs = $('.cd-stretchy-nav');
		
		stretchyNavs.each(function(){
			var stretchyNav = $(this),
				stretchyNavTrigger = stretchyNav.find('.cd-nav-trigger');
			
			stretchyNavTrigger.on('click', function(event){
				event.preventDefault();
				stretchyNav.toggleClass('nav-is-visible');
			});
		});

		$(document).on('click', function(event){
			( !$(event.target).is('.cd-nav-trigger') && !$(event.target).is('.cd-nav-trigger span') ) && stretchyNavs.removeClass('nav-is-visible');
		});
	}
});
</script>
<!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!-- 兼容IE -->
<script src="js/promise.js"></script>
</head>
<body data-spy="scroll" data-target="#navigation" data-offset="100">

	<nav class="navbar navbar-default navbar-fixed-top " id="navigation">

		<div class="container ">
		
			<div class="navbar-header">
				<!--网站Logo-->
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index.php" style="padding: 0px;"><img src="images/weblogo.png" width="52px" height="52px" style="float:left;padding-top: 3px;"><div style="float: left;margin-top: 15px;"><font>芳草寻源旅游共享平台</font></div></a>
			</div>
			<ul class="nav navbar-nav navbar-left">

				<li><a href="#carousel">精彩推荐</a></li>
				<li><a href="#strategy">攻略&&游记</a></li>
				<li><a href="#imageWall">相册墙</a></li>
				<li><a href="#photoSkill">摄影素材</a></li>
				<li><a href="#photoEqup">旅游装备</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right user-info">
			<!-- 弹出菜单 -->

				<li><a href="${pageContext.request.contextPath}/tripuser_loginPage.go">登录</a></li>
				<li><a href="${pageContext.request.contextPath}/tripuser_registerPage.do">注册</a></li>
	
			</ul>
		
			<form class="navbar-form navbar-right" method="post" action="${pageContext.request.contextPath}/goToSearch.php" target="_blank" onsubmit="return toVaild()">

				<div class="form-group">
					<input type="text" class="form-control" name="searchKey"/>
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>

		</div>
		

		<div class="menuOuter">
		<div class="menu-container">
        <div class="menu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.php">首页君</a></li>
                <li><a href="javascript:void(0);">攻略&&游记</a>
                <ul>
                      <h4>攻略&&游记</h4>
                      <li><a href="${pageContext.request.contextPath}/userstrategy_goToStrategyEdit.php" target="_blank">发表攻略</a></li>
                                <li><a href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php" target="_blank">官方攻略</a></li>
                                <li><a href="${pageContext.request.contextPath}/userstrategy_goToUserStrategy.php?page=1" target="_blank">攻略大全</a></li>
                                </ul></li>
                               
                <li><a href="javascript:void(0);">美丽の瞬间</a>
                    <ul>
                    <li><h4>美丽の瞬间</h4></li>
                    <li><a href="${pageContext.request.contextPath}/photo_goToUploadImage.php" target="_blank">创建相册</a></li>
                    <li><a href="${pageContext.request.contextPath}/photo_goToPhoto.php" target="_blank">精彩相册</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0);">沙发客</a>
                    <ul>
                    <li><h4>沙发客</h4></li>
                                <li><a href="${pageContext.request.contextPath}/product_goToProductIndex.php" target="_blank">旅游装备</a></li>
                                <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php" target="_blank">技法学院与素材</a></li>
                                <li><a href="${pageContext.request.contextPath}/lovecard_goToLoveCard.php?page=1" target="_blank">爱の明信片</a></li> 
                                <li><a href="${pageContext.request.contextPath}/FlipboardPageLayout/magazine.html" target="_blank">寻源周报</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    </div>						   
	<div id="navigations">
						<ul>
						    <li><a href="#home">寻源首页</a></li>
						    <li><a href="${pageContext.request.contextPath}/downloadPage/download.html" target="_blank">Android客户端</a></li>
						    <li><a href="${pageContext.request.contextPath}/downloadPage/download.html" target="_blank">UWP客户端</a></li>
						    <li><a href="javascript:void(0);">关于我们</a></li>
						</ul>
					</div>
	</nav>
	<div class="content scroll-example" data-offset="100" style="padding-top: 40px;">
		<!--轮播器-->
		<div id="carousel" data-offset="100">
		<div id="slide_fade" class="slide_fade">
	<div class="box">
		<ul class="list">
		<s:iterator value="beanList" var="blist">
		<s:if test="#blist.type=='strategy'">
		<li><a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${blist.id}" target="_blank">
				<img src="${blist.coverImage}" width="960px" height="500px">
				<div class="txt">
					<h3>${blist.name}</h3>
					<p>${blist.p1}</p>
					<p>${blist.p2}</p>
					<p>${blist.p3}</p>
					<p>${blist.p4}</p>
				</div>
			</a></li>
		</s:if>
		<s:elseif test="#blist.type=='album'">
		<li><a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${blist.id}" target="_blank">
				<img src="${blist.coverImage}" width="960px" height="500px">
				<div class="txt">
					<h3>${blist.name}</h3>
					<p>${blist.p1}</p>
					<p>${blist.p2}</p>
					<p>${blist.p3}</p>
					<p>${blist.p4}</p>
				</div>
			</a></li>
		</s:elseif>
		<s:elseif test="#blist.type=='skillacademy'">
		<li><a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=${blist.id}" target="_blank">
				<img src="${blist.coverImage}" width="960px" height="500px">
				<div class="txt">
					<h3>${blist.name}</h3>
					<p>${blist.p1}</p>
					<p>${blist.p2}</p>
					<p>${blist.p3}</p>
					<p>${blist.p4}</p>
				</div>
			</a></li>
		</s:elseif>
		</s:iterator>
		</ul>
	</div>
	<ul class="btn clearfix">
	<s:iterator value="beanList" var="blist">
			<li>
			<a href="javascript:void(0);">
				<img src="${blist.coverImage}" width="150" height="42">
				<h3>${blist.name}</h3>
			</a>
		</li>
	</s:iterator>
	</ul>
</div>
</div>
<!--@end----------------轮播器---------------------------> 
		<!--各种攻略-->
		<div id="strategy" data-offset="100">
			<div class="strategy-header">
				<div class="strategy-tool">
					<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyEdit.php" target="_blank"><i class=" icon-edit"></i>发表攻略</a>
				</div>
				<h3 class="title-blue">
					<a href="${pageContext.request.contextPath}/userstrategy_goToUserStrategy.php?page=1" target="_blank">攻略&&游记</a><small>你的生活指南针</small>
				</h3>
			</div>
			<div class="strategy-tab">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#food"
						aria-controls="food" role="tab" data-toggle="tab">美食</a></li>
					<li role="presentation"><a href="#travel"
						aria-controls="travel" role="tab" data-toggle="tab">旅行</a></li>
					<li role="presentation"><a href="#clothes"
						aria-controls="clothes" role="tab" data-toggle="tab">衣服搭配</a></li>
					<li role="presentation"><a href="#learn" aria-controls="learn"
						role="tab" data-toggle="tab">学习</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="food">
					<s:if test="foodList!=null">
					<s:subset source="foodList" var="fList" start="0" count="1">
					<s:iterator value="#attr.fList" var="flt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank"><img src="${flt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank">
								<p>${flt.ustrategyname}</p> <small>作者：${flt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="foodList!=null">
					<s:subset source="foodList" var="fList" start="1" count="4">
					<s:iterator value="#attr.fList" var="flt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank"><img src="${flt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank">
									<p>${flt.ustrategyname}</p> <small>作者:${flt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					<s:if test="foodList!=null">
					<s:subset source="foodList" var="fList" start="5" count="1">
					<s:iterator value="#attr.fList" var="flt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank"><img src="${flt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank">
								<p>${flt.ustrategyname}</p> <small>作者：${flt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="foodList!=null">
					<s:subset source="foodList" var="fList" start="6" count="4">
					<s:iterator value="#attr.fList" var="flt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank"><img src="${flt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${flt.ustrategyid}" target="_blank">
									<p>${flt.ustrategyname}</p> <small>作者:${flt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @start 旅行部分 -->
					<div role="tabpanel" class="tab-pane " id="travel">
					<s:if test="travelList!=null">
					<s:subset source="travelList" var="tList" start="0" count="1">
					<s:iterator value="#attr.tList" var="tlt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank"><img src="${tlt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank">
								<p>${tlt.ustrategyname}</p> <small>作者：${tlt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="travelList!=null">
					<s:subset source="travelList" var="tList" start="1" count="4">
					<s:iterator value="#attr.tList" var="tlt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank"><img src="${tlt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank">
									<p>${tlt.ustrategyname}</p> <small>作者:${tlt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					<s:if test="travelList!=null">
					<s:subset source="travelList" var="tList" start="5" count="1">
					<s:iterator value="#attr.tList" var="tlt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank"><img src="${tlt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank">
								<p>${tlt.ustrategyname}</p> <small>作者：${tlt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="travelList!=null">
					<s:subset source="travelList" var="tList" start="6" count="4">
					<s:iterator value="#attr.tList" var="tlt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank"><img src="${tlt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${tlt.ustrategyid}" target="_blank">
									<p>${tlt.ustrategyname}</p> <small>作者:${tlt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @end 旅行部分 -->
					<!-- @start 衣服搭配部分 -->
					<div role="tabpanel" class="tab-pane " id="clothes">
					<s:if test="clothesList!=null">
					<s:subset source="clothesList" var="coList" start="0" count="1">
					<s:iterator value="#attr.coList" var="colt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank"><img src="${colt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank">
								<p>${colt.ustrategyname}</p> <small>作者：${colt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="clothesList!=null">
					<s:subset source="clothesList" var="coList" start="1" count="4">
					<s:iterator value="#attr.coList" var="colt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank"><img src="${colt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank">
									<p>${colt.ustrategyname}</p> <small>作者:${colt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					<s:if test="clothesList!=null">
					<s:subset source="clothesList" var="coList" start="5" count="1">
					<s:iterator value="#attr.coList" var="colt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank"><img src="${colt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank">
								<p>${colt.ustrategyname}</p> <small>作者：${colt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="clothesList!=null">
					<s:subset source="clothesList" var="coList" start="6" count="4">
					<s:iterator value="#attr.coList" var="colt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank"><img src="${colt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${colt.ustrategyid}" target="_blank">
									<p>${colt.ustrategyname}</p> <small>作者:${colt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @end 衣服搭配部分 -->
					<!-- @start 学习部分 -->
					<div role="tabpanel" class="tab-pane " id="learn">
					<s:if test="studyList!=null">
					<s:subset source="studyList" var="stList" start="0" count="1">
					<s:iterator value="#attr.stList" var="stlt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank"><img src="${stlt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank">
								<p>${stlt.ustrategyname}</p> <small>作者：${stlt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="studyList!=null">
					<s:subset source="studyList" var="stList" start="1" count="4">
					<s:iterator value="#attr.stList" var="stlt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank"><img src="${stlt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank">
									<p>${stlt.ustrategyname}</p> <small>作者:${stlt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					<s:if test="studyList!=null">
					<s:subset source="studyList" var="stList" start="5" count="1">
					<s:iterator value="#attr.stList" var="stlt">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank"><img src="${stlt.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank">
								<p>${stlt.ustrategyname}</p> <small>作者：${stlt.tripUser.username}</small>
							</a>
						</div>
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="studyList!=null">
					<s:subset source="studyList" var="stList" start="6" count="4">
					<s:iterator value="#attr.stList" var="stlt">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank"><img src="${stlt.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=${stlt.ustrategyid}" target="_blank">
									<p>${stlt.ustrategyname}</p> <small>作者:${stlt.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @end 学习部分 -->
				</div>

			</div>
		</div>
		<!--相册墙-->
		<div id="imageWall">
			<div class="sofa-header">
				<h3 class="title-blue">
					<a href="${pageContext.request.contextPath}/photo_goToPhoto.php" target="_blank">爱の相册墙</a><small>让感动常在
					<a href="${pageContext.request.contextPath}/photo_goToUploadImage.php" target="_blank" style="float:right;margin-top:20px;"><i class=" icon-edit"></i>上传相册</a>
					</small>
				</h3>
				
			</div>
			<div class="sofa-tab">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#one"
						aria-controls="one" role="tab" data-toggle="tab" class="albumOne">夏末观星摄影篇</a></li>
					<li role="presentation"><a href="#two" aria-controls="two"
						role="tab" data-toggle="tab" class="albumTwo">镜头之下的世界</a></li>
					<li role="presentation"><a href="#three"
						aria-controls="three" role="tab" data-toggle="tab" class="albumThree">秋夜吃饱暖笠笠</a></li>
					<li role="presentation"><a href="#four"
						aria-controls="four" role="tab" data-toggle="tab" class="albumFour">带着宝贝去旅行</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
				<!-- @start 第一个相册集 -->
					<div role="tabpanel" class="tab-pane active" id="one">
					<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="0" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="0" end="0">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
								<p>${as.albumname}</p> <small>作者：${as.tripUser.username}</small>
							</a>
						</div>
						</div>
						</s:iterator>
						</s:iterator>
						</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="0" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="1" end="4">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
									<p>${as.albumname}</p> <small>作者:${as.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @end 第一个相册集 -->
					<!-- @start 第二个相册集 -->
					<div role="tabpanel" class="tab-pane " id="two">
					<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="1" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="0" end="0">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
								<p>${as.albumname}</p> <small>作者：${as.tripUser.username}</small>
							</a>
						</div>
						</div>
						</s:iterator>
						</s:iterator>
						</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="1" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="1" end="4">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
									<p>${as.albumname}</p> <small>作者:${as.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @end 第二个相册集 -->
					<!-- @start 第三个相册集 -->
					<div role="tabpanel" class="tab-pane " id="three">
						<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="2" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="0" end="0">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
								<p>${as.albumname}</p> <small>作者：${as.tripUser.username}</small>
							</a>
						</div>
						</div>
						</s:iterator>
						</s:iterator>
						</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="2" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="1" end="4">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
									<p>${as.albumname}</p> <small>作者:${as.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @end 第三个相册集 -->
					<!-- @start 第四个相册集 -->
					<div role="tabpanel" class="tab-pane " id="four">
						<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="3" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="0" end="0">
					<div class="wow slideInLeft" data-wow-duration="1s">
						<div class="tab-content-left">
							<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
								class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
								<p>${as.albumname}</p> <small>作者：${as.tripUser.username}</small>
							</a>
						</div>
						</div>
						</s:iterator>
						</s:iterator>
						</s:subset>
					</s:if>
					<div class="wow slideInRight" data-wow-duration="1s">
					<div class="tab-content-right">
					<s:if test="albumBeans!=null">
					<s:subset source="albumBeans" var="abBeans" start="3" count="1">
					<s:iterator value="#attr.abBeans" var="abs">
					<s:iterator value="#abs.albums" var="as" begin="1" end="4">
					<div class="tab-item">
								<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank"><img src="${as.coverImage}"></a> <a
									class="strategy-info" href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${as.albumid}" target="_blank">
									<p>${as.albumname}</p> <small>作者:${as.tripUser.username}</small>
								</a>
							</div>
					</s:iterator>
					</s:iterator>
					</s:subset>
					</s:if>
					</div></div>
					</div>
					<!-- @end 第四个相册集 -->
				</div>
			</div>
		</div>

		<!--摄影技巧与素材-->
		<div id="photoSkill">
			<div class="photoSkill-header">
			
				<h3 class="title-blue">
					<a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php" target="_blank">摄影技巧与素材</a><small>告别剪刀手，走向文艺照	
			 <a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyEdit.php" style="float:right;margin-top: 20px;" target="_blank"><i class=" icon-edit"></i>发表技法</a>
				</small>
		
				</h3>
			</div>
			
			<!-- 资讯轮播图 -->
						<div id="ca-container" class="ca-container">
				<div class="ca-wrapper">
				<s:if test="academyList!=null">
				<s:iterator value="academyList" var="alist">
				<div class="ca-item ca-item-1">
				<div class="ca-item-main">
							<div class="ca-icon" style="background-image: url('${alist.coverImage}');"></div>
							<h3>${alist.skilltitle}</h3>
							<h4>
								<span class="ca-quote">&ldquo;</span>
								<span><s:property value="#alist.skillplaintext.substring(0,26)+'……'"/></span>
							</h4>
								<a href="javascript:void(0);" class="ca-more">更多</a>
						</div>
						<div class="ca-content-wrapper">
							<div class="ca-content">
								<h6>${alist.skilltitle}</h6>
								<a href="javascript:void(0);" class="ca-close" title="关闭" style="background:#fff url('${alist.coverImage}') no-repeat center center;">关闭</a>
								<div class="ca-content-text">
									<p><s:property value="#alist.skillplaintext.substring(0,300)+'……'"/></p>
								</div>
								<ul>
									<li><a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=${alist.skilid}" target="_blank">查看全文</a></li>
								</ul>
							</div>
						</div>
					</div>
					</s:iterator>
				</s:if>
				</div>
			</div>
			<!-- @end  -->
		</div>

		<!--热门旅游摄影器材-->
		<div id="photoEqup">
			<div class="photoEqup-header">
				<h3 class="title-blue">
					<a href="${pageContext.request.contextPath}/product_goToProductIndex.php" target="_blank">热门旅游装备</a><small>工欲善其事，必先利其器</small>
				</h3>
			</div>
			
			<!-- 摄影器材新添加 -->
					<div class="wrap">
			<div class="container index-content">
				<div class="title">
				 <ul class="title-list">
				<li class="active" data-tab="tab_01"><a href="javascript:void(0);">旅行鞋袜</a></li>
				<li data-tab="tab_02"><a href="javascript:void(0);">登山攀岩</a></li>
				<li data-tab="tab_03"><a href="javascript:void(0);">数码</a></li>
					</ul>
				</div>
				<div class="tab-body">
					<div class="row wrap-item active" id="tab_01">
					<s:iterator value="clothePList" var="cpList">
					<div class="col-sm-4">
							<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${cpList.pid}" alt="" class="line-item" target="_blank">
								<div class="img-box">
									<img class="lazy" src="${cpList.coverImage}" alt="${cpList.pname}">
								</div>
								<div class="hot-box">
									<h6>${cpList.pname}</h6>
								</div>
								<div class="foot row">
									<div class="col-xs-12">
										<strong><span>¥</span>${cpList.marketprice}</strong>
									</div>
								</div>
							</a>
						</div>
					</s:iterator>
					</div>
					<div class="row wrap-item" id="tab_02">
					<s:iterator value="campPList" var="cpList">
					<div class="col-sm-4">
							<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${cpList.pid}" alt="" class="line-item" target="_blank">
								<div class="img-box">
									<img class="lazy" src="${cpList.coverImage}" alt="${cpList.pname}">
								</div>
								<div class="hot-box">
									<h6>${cpList.pname}</h6>
								</div>
								<div class="foot row">
									<div class="col-xs-12">
										<strong><span>¥</span>${cpList.marketprice}</strong>
									</div>
								</div>
							</a>
						</div>
					</s:iterator>
					</div>
					<div class="row wrap-item" id="tab_03">
					<s:iterator value="elecPList" var="epList">
					<div class="col-sm-4">
							<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${epList.pid}" alt="" class="line-item" target="_blank">
								<div class="img-box">
									<img class="lazy" src="${epList.coverImage}" alt="${epList.pname}">
								</div>
								<div class="hot-box">
									<h6>${epList.pname}</h6>
								</div>
								<div class="foot row">
									<div class="col-xs-12">
										<strong><span>¥</span>${epList.marketprice}</strong>
									</div>
								</div>
							</a>
						</div>
					</s:iterator>
					</div>
				</div>
			</div>
		</div>
			<!-- @end  -->
		</div>
	</div>
	
	
						 <div class="homefooter">
	<div class="container">
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
</div>

		
	<!-- 火箭回到顶层 -->
	<div style="display:none;" id="rocket-to-top">
	<div style="opacity:0;display:block;" class="level-2"></div>
	<div class="level-3"></div>
      </div>

  
</body>
</html>