<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芳草寻源-技法学院-所有文章</title>
<link href="css/skillacademylist/skillacademyList.css" rel="stylesheet">
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<!-- 热门话题组件 -->
<script src='js/tagscloud.js'></script>
<script src="js/skillacademylist/skillacademylist.js"></script>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="content">
<div class="main_nav clearfix">
    <div class="line">
      <ul>
      <s:iterator value="categories" var="c">
      <li><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyList.go?categoryId=<s:property value='#c.skillcategoryid'/>&page=1" target="_self"><s:property value='#c.skillcategoryname'/></a></li>
      </s:iterator>
      </ul>
    </div>
  </div>
<div class="content-left">
<!--@start 主文章区域 -->
<div class="section section_bg list mt">
      <div class="list_tit"><div class="list_titl"></div><h1 class="tit_style1"><s:property value="singleCategory.skillcategoryname"/></h1><div class="list_titr"></div></div>
      
        <dl class="list_news">
        <s:iterator value="allArticles.list" var="at">
        <dt>
        <span class="date"><s:date name="#at.skilldate" format="yyyy-MM-dd"/></span>
        <h3><a target="_blank" href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#at.skilid'/>"><s:property value='#at.skilltitle'/></a></h3></dt>
			<dd class="clearfix">
			<a target="_blank" href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#at.skilid'/>">
			<img src="<s:property value='#at.coverImage'/>" width="160px" height="107px" alt="<s:property value='#at.skilltitle'/>"></a>
			<p><s:property value='#at.skillplaintext.substring(0,200)'/>…………<a target="_blank" class="more" href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#at.skilid'/>"><br>查看全文&gt;&gt; </a></p>
			</dd>
			</s:iterator>
			</dl>       
     
      <div class="page_num">
      <s:if test="allArticles.page!=1">
       <a target="_self" class="pre" href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyList.go?categoryId=<s:property value='singleCategory.skillcategoryid'/>&page=<s:property value='allArticles.page-1'/>"><i></i>上一页</a>
      </s:if>
       
       <s:iterator begin="1" end="allArticles.totalPage" var="i">
       <s:if test="allArticles.page!=#i">
       <a target="_self" href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyList.go?categoryId=<s:property value='singleCategory.skillcategoryid'/>&page=<s:property value='#i'/>"><s:property value='#i'/></a>
       </s:if>
       <s:else>
       <span class="act"><s:property value="#i"/></span>
       </s:else>
       </s:iterator>
       <!-- <a target="_self" href="list_968_2.html">2</a>
       <a target="_self" href="list_968_3.html">3</a>
       <a target="_self" href="list_968_4.html">4</a>
       <a target="_self" href="list_968_5.html">5</a> ... 
       <a target="_self" href="list_968_381.html">381</a> -->
       <s:if test="allArticles.page!=allArticles.totalPage">
        <a target="_self" class="next" href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyList.go?categoryId=<s:property value='singleCategory.skillcategoryid'/>&page=<s:property value='allArticles.page+1'/>">下一页<i></i></a>
       </s:if>
           
      </div>
      
    </div>
<!-- @end 主文章区域 -->
</div>
<div class="content-right">
			<!-- @start 热门话题滚动区 -->
			<div class="section mt">
      <div class="section_tit"><i class="tit_ico">&nbsp;</i><h2>热点标签</h2></div>
      <div class="hot_tags clearfix">
      	<div id="tagscloud">
							<!-- TODO: -->
							<s:iterator value="hotTags" var="ht">
							<a
								href="${pageContext.request.contextPath}/skillacademy_clickHotTagsToSkillAcademyList.go?hotTagId=<s:property value='#ht.hotTagId'/>"
								class="<s:property value='#ht.randomClassName'/>"
								title="<s:property value='#ht.tagName'/>"><s:property value='#ht.tagName'/></a>
								</s:iterator>
					</div>
        </div>
    </div>
			<!-- @end 热门话题滚动区 -->
			<!-- @start 热点推荐区域 -->
			<div class="section mt">
      <div class="section_tit"> <i class="tit_ico">&nbsp;</i><strong>热点推荐</strong></div>
    <ul class="hot_photo clearfix">
    <s:iterator value="hotBean" var="hb">
    <li><div class="pic_box">
    <a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#hb.skillId'/>" title="<s:property value='#hb.bannerTitle'/>" class="pic">
    <img src='<s:property value='#hb.bannerImage'/>' alt="<s:property value='#hb.bannerTitle'/>" title="<s:property value='#hb.bannerTitle'/>" width="120" height="90">
    </a>
    </div>
    <a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#hb.skillId'/>" title="<s:property value='#hb.bannerTitle'/>"><s:property value='#hb.bannerTitle'/></a>
    </li>
    </s:iterator>
    </ul>
    </div>
			<!-- @end 热点推荐区域 -->
			<!-- @start 热门文章排行 -->
			<div class="section mt">
      <div class="section_tit"> <i class="tit_ico">&nbsp;</i><h2>热门文章排行</h2></div>
       <ul class="top_list"><li class="top_tit"><span>排行</span>文章标题</li>
       <s:iterator value="hotArticles" var="ha">
       <li><em class=""></em><a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#ha.skilid'/>" title="<s:property value='#ha.skilltitle'/>"><s:property value='#ha.skilltitle'/></a></li>
       </s:iterator>
       </ul>
       </div>
			<!-- @end 热门文章排行 -->
</div>

</div>
<!-- @start底部footer -->
<div class="wrapper sub-nav" style="margin:0 auto;margin-left: 200px;">
	<div class="inner clearfix">
		<ul>
			<li>
				<h3 class="h3"><a target="_blank" href="http://qicai.fengniao.com/">攻略&&游记</a></h3>
				<div class="txt clearfix">
					<a target="_blank" href="http://qicai.fengniao.com/list_1437.html">官方攻略</a>
					<a target="_blank" href="http://qicai.fengniao.com/list_1436.html">攻略大全</a>
					<a target="_blank" href="http://qicai.fengniao.com/list_1438.html">美食</a>
					<a target="_blank" href="http://qicai.fengniao.com/list_1440.html">旅游</a>
					<a target="_blank" href="http://qicai.fengniao.com/list_1441.html">衣服搭配</a>
					<a target="_blank" href="http://qicai.fengniao.com/list_1439.html">学习</a>
					<a target="_blank" href="http://qicai.fengniao.com/list_1439.html">亲子</a>
				</div>
			</li>
			<li>
				<h3 class="h3"><a target="_blank" href="http://academy.fengniao.com/">美丽の瞬间</a></h3>
				<div class="txt clearfix">
					<a target="_blank" href="http://academy.fengniao.com/list_968.html">创建相册</a>
					<a target="_blank" href="http://academy.fengniao.com/list_1510.html">精彩相册</a>
					<a target="_blank" href="http://academy.fengniao.com/list_967.html">美丽风光</a>
					<a target="_blank" href="http://academy.fengniao.com/list_969.html">精美人像</a>
				</div>
			</li>
			<li>
				<h3 class="h3"><a target="_blank" href="http://travel.fengniao.com/">沙发客</a></h3>
				<div class="txt clearfix">
					<a target="_blank" href="http://travel.fengniao.com/list_1345.html">旅游装备</a>
					<a target="_blank" href="http://travel.fengniao.com/list_1463.html">技法学院与素材</a>
					<a target="_blank" href="http://travel.fengniao.com/list_1464.html">寻缘周报</a>
				</div>
			</li>
			<li>
				<h3 class="h3"><a target="_blank" href="http://auto.fengniao.com/">技法学院</a></h3>
				<div class="txt clearfix">
				<s:iterator value="categories" var="c">
					<a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyList.go?categoryId=<s:property value='#c.skillcategoryid'/>&page=1"><s:property value="#c.skillcategoryname"/></a>
					</s:iterator>
				</div>
			</li>
			<li>
				<h3 class="h3"><a target="_blank" href="http://image.fengniao.com/">摄影作品</a></h3>
				<div class="txt clearfix">
					<a target="_blank" href="http://image.fengniao.com/list_1422.html">影像资讯</a>
					<a target="_blank" href="http://image.fengniao.com/list_1586.html">图说天下</a>
					<a target="_blank" href="http://image.fengniao.com/list_1587.html">视觉盛宴</a>
					<a target="_blank" href="http://image.fengniao.com/instant/">精彩瞬间</a>
				</div>
			</li>
		</ul>
	</div>
</div>
<!-- @end 底部footer -->
</body>
</html>