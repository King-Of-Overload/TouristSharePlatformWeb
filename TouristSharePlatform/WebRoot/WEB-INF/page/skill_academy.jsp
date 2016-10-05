<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
    <!DOCTYPE html>
<html lang="en">
  <head>
  <link href="images/favicon.ico" rel="icon" type="image/x-icon" />
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>芳草寻源-技法学院欢迎您</title>
    <meta name="author" content="Alan Lu">	
    <!-- Bootstrap -->
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
    <!-- Camera -->
    <link href="css/skillacademy/camera.css" rel="stylesheet">
    <!-- Template  -->
    <link href="css/skillacademy/templatemo_style.css" rel="stylesheet">
    <link href="css/skillacademy/skillacademy.css" rel="stylesheet">
        <script src="libs/jquery.min.js"></script>
     <script src="libs/bootstrap.min.js"></script>
       <script src="js/base.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/skillacademy/jquery.mobile.customized.min.js"></script>
    <script src="js/skillacademy/camera.min.js"></script>
    <script src="js/skillacademy/jquery.singlePageNav.min.js"></script>
    <script src="js/skillacademy/templatemo_script.js"></script>
    <script src="js/skillacademy/skillacademy.js"></script>
  </head>
<body>

  
<%@ include file="navbar.jsp" %>
    <div id="templatemo_banner_slide" class="container_wapper" style="margin-top:139px">
        <div class="camera_wrap camera_emboss" id="camera_slide">
            <!-- <div data-src="images/skillacademy/images/banner_slide_01.jpg">
                <div class="camera_caption fadeFromLeft camera_effected">
                    <div class="container">
                        <div class="col-md-6">
                            <h1>树下展现别样气质 青春貌美女子写真集</h1>
                            <p>这组作品来自网络，看到这组作品的时候就深深的被这个站在树下的气质女孩所吸引，作品中的女孩长相甜美、气质优雅，着实让人着迷。并且女孩身着白色服饰更显的楚楚动人</p>
                        </div>
                    </div>
                </div>
            </div>     -->
            <s:iterator value="bannerInfos" var="info">
            <div data-src="<s:property value='#info.bannerImage'/>">
                <div class="camera_caption fadeFromLeft camera_effected">
                    <div class="container">
                        <div class="col-md-6">
                            <h1><a href='${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#info.skillId'/>'><s:property value='#info.bannerTitle'/></a></h1>
                            <p><s:property value='#info.bannerDescription'/></p>
                        </div>
                    </div>
                </div>
            </div>
            </s:iterator>
        </div><!-- #camera_wrap_3 -->    
    </div>

    
    <div id="templatemo_upcomming_event" class="container_wapper">
        <div class="container">
            <div class="row">
       <div class="col-xs-12 section_header">
                    <h1>精华技巧</h1>
                </div>
           
               <s:iterator value="essenceAcademy" var="ea">
                <div class="col-sm-6 col-md-3">
                    <div class="event_box event_animate_left">
                        <a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#ea.skilid'/>"><img src='<s:property value='#ea.coverImage'/>' alt="Event 1" class="img-responsive" /></a>
                        <ul>
                            <li>
                                    <span class="glyphicon glyphicon-calendar"></span><s:property value='#ea.skilltitle'/><br />

                            </li>
                            <li>
                                    <span class="glyphicon glyphicon-time"></span><s:date name="#ea.skilldate" format="yyyy-MM-dd"/><br />
                            </li>
                            <li>
                                    <span class="glyphicon glyphicon-map-marker"></span><s:property value='#ea.skillplaintext.substring(0,48)'/><br />
                            </li>
                        </ul>
                    </div>
                </div>
                </s:iterator>
     
            </div>
        </div>
    </div>

    <div id="templatemo_blog" class="container_wapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 section_header">
                    <h1>用户精选</h1>
                </div>
                <s:iterator value="specialChosenSkill" var="sc">
                <div class="clearfix"></div>
                <div class="col-sm-4 event_animate_left">
                   <a href='${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#sc.skilid'/>'><img src="<s:property value='#sc.coverImage'/>" alt="Blog Post 1" class="img-responsive" /></a> 
                </div>
                <div class="col-sm-8 blog_text event_animate_right">
                    <h1><a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#sc.skilid'/>"><s:property value='#sc.skilltitle'/></a></h1>
                    <p>
                        <span class="glyphicon glyphicon-user"></span> written by <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='#sc.user.userid'/>&page=1"><s:property value="#sc.user.username"/></a>  &nbsp;&nbsp;
                        <span class="glyphicon glyphicon-calendar"></span> <s:date name="#sc.skilldate" format="yyyy-MM-dd"/> &nbsp;&nbsp;
                        <span class="glyphicon glyphicon-bookmark"></span> <s:iterator value='#sc.secondTags' var='t'><a href="${pageContext.request.contextPath}/skillacademy_clickRelatedTagToSkillAcademyList.go?secondTagId=<s:property value='#t.skillsecondid'/>"><s:property value='#t.skillsecondname'/></a>&nbsp;</s:iterator>
                    </p>
                    <p><s:property value='#sc.skillplaintext.substring(0,200)'/></p>
                </div>
               </s:iterator>
            </div>
        </div>
    </div>
            <div class="row">
<div class="col-xs-12 section_header">
                    <h1>分类精华</h1>
                </div>
            </div>
   
            <div class="web">
	<div class="con">
		<ul>
		<s:iterator value="categoryList" var="cl">
			<li>
				<img src='<s:property value='#cl.skillcategoryimage'/>'/>
				<div class="txt">
					<h3><a href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademyList.go?categoryId=<s:property value='#cl.skillcategoryid'/>&page=1"><s:property value='#cl.skillcategoryname'/></a></h3>
					<p><s:property value='#cl.skillcategorydescription'/></p>
				</div>
			</li>
			</s:iterator>
		</ul>
	</div>
</div>

    <div id="templatemo_footer" class="container_wapper">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <p>Copyright &copy;2016&nbsp;芳草寻源小组倾情出品</p>
                </div>
            </div>
        </div>
    </div>

</body>
</html>