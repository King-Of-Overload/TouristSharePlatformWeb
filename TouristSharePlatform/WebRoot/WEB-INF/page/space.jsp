<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
    <meta charset="UTF-8">
    <title>芳草寻源-${currentUser.username}の个人中心</title>
    <link href="libs/bootstrap.min.css" rel="stylesheet">
    <link href="libs/font-awesome.min.css" rel="stylesheet">
    <link href="libs/highlight/styles/default.css" rel="stylesheet"/>
    <link href="css/base.css" rel="stylesheet">
    <link href="css/space.css" rel="stylesheet">
    <link href="css/rocketstyle.css" rel="stylesheet">
    <link href="css/jquery.toast.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/rocketscript.js"></script>
<script src="libs/EpicEditor/js/epiceditor.min.js"></script>
<script src="js/base.js"></script>
 <script src="js/space.js"></script>
 <script src="js/thereSomeThingAboutMe.js"></script>
 	<script src="js/space/materialMenu.min.js"></script>
 	 <!-- 对话框特效 -->
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.js"></script>
<!-- 推送框通知 -->
<script src="js/jquery.toast.js"></script>
	<script>
	window.onload=function(){
		  var menu = new Menu;
		  var headerImage=decodeURIComponent(sessionStorage.headerImage);
		  $('#myPic').attr('src',headerImage);
		  headerImage=headerImage.replace('images/', '');
		  thereSomeThingAboutMe.run({
			  random:false,
			  time: 4000,
			  pictures: [headerImage, headerImage],
			  inAnimation: 'flipInX',
			  outAnimation: 'bounceOut'
			});     
	};
	</script>
	<!-- @start 修改头像相关 -->
	<link href="css/cropper.min.css" rel="stylesheet">
	<link href="css/sitelogo.css" rel="stylesheet">
	<script src="js/space/cropper.min.js"></script>
	<script src="js/space/sitelogo.js"></script>
	<!-- @end 修改头像相关 -->
</head>
<body>
	<div id="wrapper" class="wrapper">
<div class="content">
    <div class="content-header">
        <div class="main">
            <div class="header-left">
            <div class="headImg">
            <div class="about-of-me">
            <div id="crop-avatar" class="col-md-6">
				<div class="avatar-view" title="Change Logo Picture">
           <img src="" id="myPic" height="250" class="animated "/>
           </div>
           </div>
                  </div>   
                  </div>

               <!--  <div class="headImg">
                    <img src="images/headImg.jpg" alt="用户头像"/>
                </div> -->
                <div class="header-info">
                    <h3 class="nickName">昵称</h3>
                    <div class="button-tool">
                    <span>${currentUser.usignature}</span>
                    </div>
                </div>
            </div>
            <div class="header-right">
                <dl class="fans fl">
                    <dt><s:property value="fansCount"/></dt>
                    <dd>粉丝</dd>
                </dl>
                <dl class="focus fr">
                    <dt><s:property value="focusCount"/></dt>
                    <dd>关注</dd>
                </dl>
            </div>
        </div>
    </div>
    <div class="content-body">
        <div class="body-left fl">
            <div class="timeLine-hd"></div>
            <s:if test="requestType=='index'">
            <s:if test="freshThings!=null">
            <s:iterator value="freshThings" var="ft">
            <s:if test="#ft.type=='strategy'">
             <ul class="timeLine">
                <li class="timeLine-item">
                    <dl class="timeLine-date">
                        <dt><s:date name="#ft.time" format="MM/dd"/></dt>
                        <dd><s:date name="#ft.time" format="yyyy"/></dd>
                    </dl>
                    <div class="timeLine-ico"></div>
                    <div class="timeLine-trangle"></div>
                    <div class="timeLine-content">
                    <s:if test="currentUser.username==#ft.user.username">
                    <p class="label">我共享了攻略游记</p>
                    </s:if>
                        <s:else>
                        <p class="label"><s:property value="#ft.user.username"/>共享了攻略游记</p>
                        </s:else>
                        <h3><a href="${pageContext.request.contextPath}/userstrategy_goToStrategyInfo.go?ustrategyid=<s:property value='#ft.id'/>" target="_blank"><s:property value="#ft.title"/></a></h3>
                        <p class="timeLine-intro"><s:property value="#ft.description"/></p>
                        <div class="pic">
                            <img src="<s:property value='#ft.coverImage'/>" alt="图片展示"/>
                        </div>
                        <ul class="content-info">
                            <li><i class="icon-eye-open"></i><span class="watched"><s:property value="#ft.clickedNum"/></span></li>
                            <li><i class="icon-thumbs-up-alt"></i><span class="up"><s:property value="#ft.likeCount"/></span></li>
                            <li><i class=" icon-comment"></i><span class="comment"><s:property value="#ft.commentNum"/></span></li>
                        </ul>
                    </div>
                </li>
            </ul>
            </s:if>
            <s:elseif test="#ft.type=='album'">
             <ul class="timeLine">
                <li class="timeLine-item">
                    <dl class="timeLine-date">
                        <dt><s:date name="#ft.time" format="MM/dd"/></dt>
                        <dd><s:date name="#ft.time" format="yyyy"/></dd>
                    </dl>
                    <div class="timeLine-ico"></div>
                    <div class="timeLine-trangle"></div>
                    <div class="timeLine-content">
                    <s:if test="currentUser.username==#ft.user.username">
                     <p class="label">我共享了相册</p>
                    </s:if>
                       <s:else>
                        <p class="label"><s:property value="#ft.user.username"/>共享了相册</p>
                       </s:else>
                        <h3><a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.go?albumid=<s:property value='#ft.id'/>" target="_blank"><s:property value="#ft.title"/></a></h3>
                        <p class="timeLine-intro"><s:property value="#ft.description"/></p>
                        <div class="pic">
                            <img src="<s:property value='#ft.coverImage'/>" alt="图片展示"/>
                        </div>
                        <ul class="content-info">
                            <li><i class="icon-eye-open"></i><span class="watched"><s:property value="#ft.clickedNum"/></span></li>
                        </ul>
                    </div>
                </li>
            </ul>
            </s:elseif>
            <s:elseif test="#ft.type=='skillacademy'">
             <ul class="timeLine">
                <li class="timeLine-item">
                    <dl class="timeLine-date">
                        <dt><s:date name="#ft.time" format="MM/dd"/></dt>
                        <dd><s:date name="#ft.time" format="yyyy"/></dd>
                    </dl>
                    <div class="timeLine-ico"></div>
                    <div class="timeLine-trangle"></div>
                    <div class="timeLine-content">
                    <s:if test="currentUser.username==#ft.user.username">
                    <p class="label">我共享了旅游摄影技法文章与素材</p>
                    </s:if>
                        <s:else>
                        <p class="label"><s:property value="#ft.user.username"/>共享了旅游摄影技法文章与素材</p>
                        </s:else>
                        <h3><a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#ft.id'/>" target="_blank"><s:property value="#ft.title"/></a></h3>
                        <p class="timeLine-intro"><s:property value="#ft.description"/></p>
                        <div class="pic">
                            <img src="<s:property value='#ft.coverImage'/>" alt="图片展示"/>
                        </div>
                        <ul class="content-info">
                             <li><i class="icon-eye-open"></i><span class="watched"><s:property value="#ft.clickedNum"/></span></li>
                            <li><i class=" icon-comment"></i><span class="comment"><s:property value="#ft.commentNum"/></span></li>
                        </ul>
                    </div>
                </li>
            </ul>
            </s:elseif>
            </s:iterator>
                       
            </s:if>
            <s:else>
              <ul class="timeLine">
              <li><span>空空如也</span></li>
            </ul>
            </s:else>
            </s:if>
            <s:elseif test="requestType=='album'">
            <s:if test="albums!=null">
            <s:iterator value="albums" var="as">
                         <ul class="timeLine">
                <li class="timeLine-item">
                    <dl class="timeLine-date">
                        <dt><s:date name="#as.time" format="MM/dd"/></dt>
                        <dd><s:date name="#as.time" format="yyyy"/></dd>
                    </dl>
                    <div class="timeLine-ico"></div>
                    <div class="timeLine-trangle"></div>
                    <div class="timeLine-content">
                     <p class="label">我共享了相册</p>
                        <h3><a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.go?albumid=<s:property value='#as.id'/>" target="_blank"><s:property value="#as.title"/></a></h3>
                        <p class="timeLine-intro"><s:property value="#as.description"/></p>
                        <div class="pic">
                            <img src="<s:property value='#as.coverImage'/>" alt="图片展示"/>
                        </div>
                        <ul class="content-info">
                            <li><i class="icon-eye-open"></i><span class="watched"><s:property value="#as.clickedNum"/></span></li>
                        </ul>
                    </div>
                </li>
            </ul>
            </s:iterator>
            </s:if>
            <s:else>
            <ul class="timeLine">
              <li><span>空空如也</span></li>
            </ul>
            </s:else>
            </s:elseif>
            <s:elseif test="requestType=='skillacademy'">
            <s:if test="academies!=null">
            <s:iterator value="academies" var="ad">
             <ul class="timeLine">
                <li class="timeLine-item">
                    <dl class="timeLine-date">
                        <dt><s:date name="#ad.time" format="MM/dd"/></dt>
                        <dd><s:date name="#ad.time" format="yyyy"/></dd>
                    </dl>
                    <div class="timeLine-ico"></div>
                    <div class="timeLine-trangle"></div>
                    <div class="timeLine-content">
                    <p class="label">我共享了旅游摄影技法文章与素材</p>
                        <h3><a href="${pageContext.request.contextPath}/skillacademy_goToShowOneSkillAcademyArticle.go?skillId=<s:property value='#ad.id'/>" target="_blank"><s:property value="#ad.title"/></a></h3>
                        <p class="timeLine-intro"><s:property value="#ft.description"/></p>
                        <div class="pic">
                            <img src="<s:property value='#ad.coverImage'/>" alt="图片展示"/>
                        </div>
                        <ul class="content-info">
                             <li><i class="icon-eye-open"></i><span class="watched"><s:property value="#ad.clickedNum"/></span></li>
                            <li><i class=" icon-comment"></i><span class="comment"><s:property value="#ad.commentNum"/></span></li>
                        </ul>
                    </div>
                </li>
            </ul>
            </s:iterator>
            </s:if>
            <s:else>
            <ul class="timeLine">
              <li><span>空空如也</span></li>
            </ul>
            </s:else>
            </s:elseif>
        </div>
        <div class="body-right fr">
            <div class="box fans-box">
                <div class="header">
                    <span class="fansNum"><s:property value="fansCount"/></span>
                    <h3>我的粉丝</h3>
                </div>
                <div class="list">
                <s:if test="fansList!=null">
                <s:iterator value="fansList" var="fl">
                 <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='#fl.user.userid'/>&page=1&requestType=strategy" target="_blank">
                        <dl class="item">
                            <dt><img src="<s:property value='#fl.user.headerimage'/>" alt="头像"/></dt>
                            <!-- <dd class="fansName"><s:property value='#fl.user.username'/></dd> -->
                        </dl>
                    </a>
                </s:iterator>
                </s:if>
                </div>
            </div>
            <div class="box focus-box">
                <div class="header">
                    <span class="focusNum"><s:property value="focusCount"/></span>
                    <h3>我的关注</h3>
                </div>
                <div class="list">
                <s:if test="focusUsers!=null">
                <s:iterator value="focusUsers" var="fu">
                  <a href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='#fu.userid'/>&page=1&requestType=strategy" target="_blank">
                        <dl class="item">
                            <dt><img src="<s:property value='#fu.headerimage'/>" alt="头像"/></dt>
                           <!--   <dd class="fansName"><s:property value='#fl.user.username'/></dd>-->
                        </dl>
                    </a>
                </s:iterator>
                </s:if>
                </div>
            </div>
        </div>
    </div>
</div>
	 
	 <!--时间线模板-->
<ul id="timeLine" style="display:none;">
    <li class="timeLine-item">
        <dl class="timeLine-date">
            <dt>11/09</dt>
            <dd>2015</dd>
        </dl>
        <div class="timeLine-ico"></div>
        <div class="timeLine-trangle"></div>
        <div class="timeLine-content">
            <p class="label">TA发表了攻略</p>
            <h3><a href="#">迷失在天堂，毛里求斯海陆空全方位体验</a></h3>
            <p class="timeLine-intro">2014年5月，和风哥成为合法夫妻。 2014年9月，开始穿着婚纱去旅行的第一站----马尔代夫。2015年2月，实现穿着婚纱去旅行的第二站----泰国。 2015年9月，人生...</p>
            <div class="pic">
                <img src="images/travel01.jpg" alt="图片展示"/>
            </div>
            <ul class="content-info">
                <li><i class="icon-eye-open"></i><span class="watched">99</span></li>
                <li><i class="icon-thumbs-up"></i><span class="up">99</span></li>
                <li><i class=" icon-comment"></i><span class="comment">99</span></li>
            </ul>
        </div>
    </li>
</ul>

	</div><!-- /wrapper -->
	
	
	
	<!-- @start修改头像模态框 -->
	<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form class="avatar-form" action="tripuser_uploadHeaderImage.do" enctype="multipart/form-data" method="post">
				<div class="modal-header">
					<button class="close" data-dismiss="modal" type="button">&times;</button>
					<h4 class="modal-title" id="avatar-modal-label">修改头像</h4>
				</div>
				<div class="modal-body">
					<div class="avatar-body">
						<div class="avatar-upload">
						  <input class="avatar-username" name="avatar-username" type="hidden" value="">
							<input class="avatar-src" name="avatar_src" type="hidden">
							<input class="avatar-data" name="avatar_data" type="hidden">
							<label for="avatarInput">头像上传</label>
							<input class="avatar-input" id="avatarInput" name="avatar_file" type="file"></div>
						<div class="row">
							<div class="col-md-9">
								<div class="avatar-wrapper"></div>
							</div>
							<div class="col-md-3">
								<div class="avatar-preview preview-lg"></div>
								<div class="avatar-preview preview-md"></div>
								<div class="avatar-preview preview-sm"></div>
							</div>
						</div>
						<div class="row avatar-btns">
							<div class="col-md-9">
								<div class="btn-group">
									<button class="btn" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees"><i class="fa fa-undo"></i> 向左旋转</button>
								</div>
								<div class="btn-group">
									<button class="btn" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees"><i class="fa fa-repeat"></i> 向右旋转</button>
								</div>
							</div>
							<div class="col-md-3">
								<button class="btn btn-success btn-block avatar-save" type="submit"><i class="fa fa-save"></i> 保存修改</button>
							</div>
						</div>
					</div>
				</div>
  		</form>
  	</div>
  </div>
</div>
    <!-- @end修改头像模态框 -->
<!-- @start 左侧汉堡菜单部分 -->
	<button id="mm-menu-toggle" class="mm-menu-toggle">菜单</button>
	<%@include file="navbar_nomenu.jsp" %>
	<nav id="mm-menu" class="mm-menu">
	  <div class="mm-menu__header">
	    <div class="mm-menu__image"><img src="images/headerImages/tangyurou/1.jpg" id="myPic"/></div>
	    <h2 class="mm-menu__title">帅的被人砍</h2>
	  </div>
	  <ul class="mm-menu__items">
	    <li class="mm-menu__item">
	      <a class="mm-menu__link" href="${pageContext.request.contextPath}/tripuser_goToSpace.php?requestType=index">
	        <span class="mm-menu__link-text"><i class="md md-home"></i>&nbsp;个人首页</span>
	      </a>
	    </li>
	    <li class="mm-menu__item">
	      <a class="mm-menu__link" href="${pageContext.request.contextPath}/tripuser_goToUserSpace.go?userid=<s:property value='currentUser.userid'/>&page=1&requestType=strategy">
	        <span class="mm-menu__link-text"><i class="md md-person"></i>&nbsp;我的攻略&&游记</span>
	      </a>
	    </li>
	    <li class="mm-menu__item">
	      <a class="mm-menu__link" href="${pageContext.request.contextPath}/tripuser_goToSpace.php?requestType=album">
	        <span class="mm-menu__link-text"><i class="md md-inbox"></i>&nbsp;爱の相册</span>
	      </a>
	    </li>
	            <li class="mm-menu__item">
	      <a class="mm-menu__link" href="${pageContext.request.contextPath}/tripuser_goToSpace.php?requestType=skillacademy">
	        <span class="mm-menu__link-text"><i class="md md-collections">&nbsp;技法学院文章与素材</i></span>
	      </a>
	    </li>
	        <li class="mm-menu__item">
	      <a class="mm-menu__link" href="${pageContext.request.contextPath}/tripuser_goToPersonalDoc.php?userid=${currentUser.userid}#m-word">
	        <span class="mm-menu__link-text"><i class="md md-face-unlock">&nbsp;羞羞的小收藏</i></span>
	      </a>
	    </li>
	    <li class="mm-menu__item">
	      <a class="mm-menu__link" href="${pageContext.request.contextPath}/tripuser_goToPersonalDoc.php?userid=${currentUser.userid}#m-word">
	        <span class="mm-menu__link-text"><i class="md md-shopping-cart"></i>&nbsp;购买记录</span>
	      </a>
	    </li>
	    <li class="mm-menu__item">
	      <a class="mm-menu__link" href="${pageContext.request.contextPath}/tripuser_goToPersonalDoc.php?userid=${currentUser.userid}">
	        <span class="mm-menu__link-text"><i class="md md-settings">&nbsp;个人信息与设置</i></span>
	      </a>
	    </li>
	  </ul>
	</nav>
	<!-- @end 左侧汉堡菜单部分 -->
	
		<!-- 火箭回到顶层 -->
	<div style="display:none;" id="rocket-to-top">
	<div style="opacity:0;display:block;" class="level-2"></div>
	<div class="level-3"></div>
      </div>
</body>
</html>