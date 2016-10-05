<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta charset="UTF-8">
<title>芳草寻源-相册区</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="libs/highlight/styles/default.css" rel="stylesheet" />
<script src="libs/jquery.min.js"></script>
<link href="css/base.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/userphoto.css" rel="stylesheet">
<script src="libs/bootstrap.min.js"></script>
<script src="libs/EpicEditor/js/epiceditor.min.js"></script>
<script src="js/base.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/userphoto.js"></script>
<script src="js/stopExecutionOnTimeout.js?t=1"></script>
<!-- 相册显示依赖 -->
<script src="js/pinterest_grid.js"></script>
<script src="js/jquery.gallerybox.js"></script>
<style type="text/css">
.slide:nth-child(1) .slide__bg {
  left: 0;
  background-image: url('${bannerList[0].imageUrl}');
  background-size:100% 700px;
  width:100%;
  height:500px;
}
.slide:nth-child(1) .slide__overlay-path {
  fill: #e99c7e;
}
@media (max-width: 991px) {
  .slide:nth-child(1) .slide__text {
    background-color: rgba(233, 156, 126, 0.8);
  }
}
.slide:nth-child(2) {
  left: 100%;
}
.slide:nth-child(2) .slide__bg {
  left: -50%;
    background-image: url("${bannerList[1].imageUrl}");
    background-size:100% 700px;
  width:100%;
  height:500px;
}
.slide:nth-child(2) .slide__overlay-path {
  fill: #e1ccae;
}
@media (max-width: 991px) {
  .slide:nth-child(2) .slide__text {
    background-color: rgba(225, 204, 174, 0.8);
  }
}
.slide:nth-child(3) {
  left: 200%;
}
.slide:nth-child(3) .slide__bg {
  left: -100%;
    background-image: url("${bannerList[2].imageUrl}");
      background-size:100% 700px;
      width:100%;
      height:500px;
}
.slide:nth-child(3) .slide__overlay-path {
  fill: #adc5cd;
}
@media (max-width: 991px) {
  .slide:nth-child(3) .slide__text {
    background-color: rgba(173, 197, 205, 0.8);
  }
}
.slide:nth-child(4) {
  left: 300%;
}
.slide:nth-child(4) .slide__bg {
  left: -150%;
  background-image: url("${bannerList[3].imageUrl}");
  background-size:100% 700px;
  width:100%;
  height:500px;
}
</style>
</head>
<body>
 <%@ include file="navbar.jsp" %>
<!--@start 轮播图-->
	<div class="slider-container">
	  <div class="slider-control left inactive"></div>
	  <div class="slider-control right"></div>
	  <ul class="slider-pagi"></ul>
	  <div class="slider">
	    	 
	    <div class="slide slide-0 active">

	      <div class="slide__bg"></div>
	      <div class="slide__content">
	        <svg class="slide__overlay" viewBox="0 0 720 405" preserveAspectRatio="xMaxYMax slice">
	          <path class="slide__overlay-path" d="M0,0 150,0 500,405 0,405" />
	        </svg>
	         <a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${bannerList[0].albumid}" target="_blank">
	        <div class="slide__text">
	          <h2 class="slide__text-heading">${bannerList[0].albumname}</h2>
	          <p class="slide__text-desc">${bannerList[0].albumdescription}</p>
	         
	        </div>
	         </a>
	      </div>   
	    </div>

 
	    <div class="slide slide-1">
	      <div class="slide__bg"></div>
	      <div class="slide__content">
	        <svg class="slide__overlay" viewBox="0 0 720 405" preserveAspectRatio="xMaxYMax slice">
	          <path class="slide__overlay-path" d="M0,0 150,0 500,405 0,405" />
	        </svg>
	        <a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${bannerList[1].albumid}" target="_blank">
	        <div class="slide__text">
	          <h2 class="slide__text-heading">${bannerList[1].albumname}</h2>
	          <p class="slide__text-desc">${bannerList[1].albumdescription}</p>
	         
	        </div>
	        	    </a>
	      </div>
	    </div>

	   
	    <div class="slide slide-2">
	      <div class="slide__bg"></div>
	      <div class="slide__content">
	        <svg class="slide__overlay" viewBox="0 0 720 405" preserveAspectRatio="xMaxYMax slice">
	          <path class="slide__overlay-path" d="M0,0 150,0 500,405 0,405" />
	        </svg>
	         <a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${bannerList[2].albumid}" target="_blank">
	        <div class="slide__text">
	          <h2 class="slide__text-heading">${bannerList[2].albumname}</h2>
	          <p class="slide__text-desc">${bannerList[1].albumdescription}</p>
	        </div>
	        	    </a>
	      </div>
	    </div>
	   
	    <div class="slide slide-3">
	      <div class="slide__bg"></div>
	      <div class="slide__content">
	        <svg class="slide__overlay" viewBox="0 0 720 405" preserveAspectRatio="xMaxYMax slice">
	          <path class="slide__overlay-path" d="M0,0 150,0 500,405 0,405" />
	        </svg>
	        <a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=${bannerList[3].albumid}" target="_blank">
	        <div class="slide__text">
	           <h2 class="slide__text-heading">${bannerList[3].albumname}</h2>
	          <p class="slide__text-desc">${bannerList[3].albumdescription}</p>
	        </div>
	        </a>
	      </div>
	    </div>
	  </div>
	</div>
    <!--@end 轮播图-->
    <!-- @start主题 -->
    <div class="container">
    <div class="pictures-head">
	<div class="small-icon">
	<img alt="经典格言" src="images/smallicon.jpg" height="50">
	</div>
	<div class="geyan-words">
	<img alt="旅行，是你唯一能买到是你更富有的东西" src="images/geyan.jpg"/>
	</div>
	<div class="declare-pictures-btn">
	<a class="new-pictures" href="${pageContext.request.contextPath}/photo_goToUploadImage.php" id="create-new-pictures" target="_blank">上传精彩相册集</a>
	</div>
	</div>
	<div class="recommand-list">
	<div class="recommand">
	<span class="recommand-title">本站推荐</span>
	<div class="pictures-categories">
	<ul>
	<li>
	<s:if test="type=='hot'">
	<a id="pictures-hotest" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?type=hot" class="pictures-active">热门</a>
	</s:if>
	<s:else>
	<a id="pictures-hotest" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?type=hot">热门</a>
	</s:else>
	</li>
	<li>
	<s:if test="type=='issense'">
	<a id="pictures-perfect" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?type=issense" class="pictures-active">优质精华</a>
	</s:if>
	<s:else>
	<a id="pictures-perfect" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?type=issense">优质精华</a>
	</s:else>
	</li>
	<li>
	<s:if test="type=='latest'">
	<a id="pictures-latest" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?type=latest" class="pictures-active">最新上传</a>
	</s:if>
	<s:else>
	<a id="pictures-latest" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?type=latest">最新上传</a>
	</s:else>
	</li>
	</ul>
	</div>
	<div class="hottest-recommand">
	<span class="hottest-tags">热门标签</span>
	<div class="tags-categories">
	<ul>
	<li>
	<s:if test="tag!=null&&tag=='all'">
	<a id="" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?tag=0" class="tags-active">全部</a>
	</s:if>
	<s:else>
	<a id="" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?tag=0">全部</a>
	</s:else>
	</li>
	<s:iterator value="albumTags" var="at">
	<s:if test="#at.tagid==tag">
	<li><a class="tags-active" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?tag=${at.tagid}"><s:property value="#at.tagname"/></a></li>
	</s:if>
	<s:else>
	<li><a id="" href="${pageContext.request.contextPath}/photo_goToPhotoWithCondition.php?tag=${at.tagid}"><s:property value="#at.tagname"/></a></li>
	</s:else>
		
	</s:iterator>
	</ul>
	</div>
	</div>
	</div>
	</div>
    </div>
    <!-- @end主题 -->
    	<!-- @start 相册主体部分 -->
		<section id="gallery-wrapper">
		<s:iterator value="allAlbums" var="album">
		<article class="white-panel">
			<a href="${pageContext.request.contextPath}/photo_goToShowOneAlbum.php?albumid=<s:property value='#album.albumid'/>"><img src='<s:property value='#album.imageUrl'/>' class="thumb"></a>
			<h1><a href="#"><s:property value="#album.albumname"/></a></h1>
	  		<p><s:property value="#album.albumdescription"/></p>
		</article>
</s:iterator>
    </section>
	<!-- @end 相册主体部分  -->
	
	<%@include file="footer.jsp" %>

</body>
</html>