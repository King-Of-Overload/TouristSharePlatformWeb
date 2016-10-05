<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芳草寻源-官方攻略</title>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/book.css" />
<script src="libs/jquery.min.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/base.js"></script>
<script src="js/wow.js"></script>
<link href="css/animate.css" rel="stylesheet">
    <script type="text/javascript" src="js/officialStrategy/raphael-min.js"></script>
    <script type="text/javascript" src="js/officialStrategy/chinaMapConfig.js"></script>
    <script type="text/javascript" src="js/officialStrategy/map-min.js"></script>
    <link href="css/official_strategy.css" rel="stylesheet">
<script type="text/javascript" src="js/officialStrategy/vmc.slider.full.min.js"></script> 
<script type="text/javascript" src="js/officialStrategy/officialStrategy.js"></script>  
</head>
<body class="gummy-transition">
<%@ include file="navbar.jsp" %>
<div class="container" style="width:1500px;">
<div class="nav-map" style=" padding-top:80px;">
<div class="itemCount" style="float:left;">  <div id="ChinaMap" style="margin:50px 0px 10px 50px;"></div></div>
    		<!-- <div class="container"> -->
			<div class="content" style="float:left;width:700px;height:380px;margin:30px auto;overflow:hidden;padding-top: 50px;">
				<div id="slider">
					<a href="#"><img src="images/demo1.jpg"/></a>
					<a href="#"><img src="images/demo2.jpg"/></a>
					<a href="#"><img src="images/demo3.jpg"/></a>
					<a href="#"><img src="images/demo4.jpg"/></a>
				</div>
			</div>
</div>

<div id="stateTip" style="position: absolute;left: 100%;text-align: left;display: inline;"></div>
    <div id="mapTipContent" style="width: 900px;margin: 0 auto;display: none">
        <div class="mapTipText mapTipText0">
            <div class="mapTipImg"><img src="images/officialStrategy/heilongjiang.jpg" alt=""/></div>
            <div class="mapTipList">
                <h2><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByProvince.do?provinceid=10">黑龙江<span>Heilongjiang</span></a></h2>
                <ul>
                    <li><a href="73${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=73">哈尔滨</a></li>
                </ul>
            </div>
        </div>
        <div class="mapTipText mapTipText1">
            <div class="mapTipImg"><img src="images/officialStrategy/jilin.jpg" alt=""/></div>
            <div class="mapTipList">
                <h2><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByProvince.do?provinceid=9">吉林<span>Jilin</span></a></h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=64">长春</a></li>
                </ul>
            </div>
        </div>
        <div class="mapTipText mapTipText2">
            <div class="mapTipImg"><img src="images/officialStrategy/liaoning.jpg" alt=""/></div>
            <div class="mapTipList">
                <h2><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByProvince.do?provinceid=8">辽宁<span>Liaoning</span></a></h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=50">沈阳</a></li>
                </ul>
            </div>
        </div>

        <div class="mapTipText mapTipText3">
            <div class="mapTipImg"><img src="images/officialStrategy/hebei.jpg" alt=""/></div>
            <div class="mapTipList">
                <h2><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByProvince.do?provinceid=5">河北<span>HeBei</span></a></h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=5">石家庄</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=6">唐山</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=7">秦皇岛</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=8">邯郸</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=9">邢台</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=10">保定</a></li>
                </ul>
            </div>
        </div>

        <div class="mapTipText mapTipText4">
            <div class="mapTipImg"><img src="images/officialStrategy/shandong.jpg" alt=""/></div>
            <div class="mapTipList">
                <h2><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByProvince.do?provinceid=16">山东<span>ShanDong</span></a></h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=147">济南</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=148">青岛</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=149">淄博</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=150">枣庄</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=152">烟台</a></li>
                </ul>
            </div>
        </div>
        
                <div class="mapTipText mapTipText5">
            <div class="mapTipImg"><img src="images/officialStrategy/jiangsu.jpg" alt=""/></div>
            <div class="mapTipList">
                <h2><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByProvince.do?provinceid=11">江苏<span>jiangsu</span></a></h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=86">南京</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=87">无锡</a></li>
                    <li><a href="${pageContext.request.contextPath}/oficialstrategy_findStrategyByCity.do?cityid=89">常州</a></li>
                    <li><a href="${pageContext.request.contextPath}/oficialstrategy_findStrategyByCity.do?cityid=90">苏州</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=95">扬州</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=96">镇江</a></li>
                </ul>
            </div>
        </div>
         <div class="mapTipText mapTipText6">
            <div class="mapTipImg"><img src="images/officialStrategy/zhejiang.jpg" alt=""/></div>
            <div class="mapTipList">
                <h2><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByProvince.do?provinceid=12">浙江<span>ZheJiang</span></a></h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=99">杭州</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=100">宁波</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=104">绍兴</a></li>
                    <li><a href="${pageContext.request.contextPath}/officialstrategy_findStrategyByCity.do?cityid=107">舟山</a></li>
                </ul>
            </div>
        </div>
    </div>
    </div>
    <h3 class="title-blue" style="margin-left: 300px;margin-right: 350px;">攻略大全</h3>
    	<div class="container">
			<div class="component">
				<ul class="align">
				<s:iterator value="oList" var="o">
									<li>
						<figure class='book'>
							<!-- Front -->
							<ul class='hardcover_front'>
								<li>
								<img src="<s:property value='#o.ostrategyimage'/>" alt="" width="100%" height="100%">
								</li>
								<li></li>
							</ul>
							<!-- Pages -->
							<ul class='page'>
								<li></li>
								<li>
									<a class="bookbtn" href="${pageContext.request.contextPath}/pdfReader/web/documents/pdf/<s:property value='#o.ostrategyurl'/>">下载</a>
									<a class="bookbtn" href="pdfReader/web/viewer.html?file=documents/pdf/<s:property value='#o.ostrategyurl'/>">预览</a>
								</li>
								<li></li>
								<li></li>
								<li></li>
							</ul>

							<!-- Back -->

							<ul class='hardcover_back'>
								<li></li>
								<li></li>
							</ul>
							<ul class='book_spine'>
								<li></li>
								<li></li>
							</ul>
							<figcaption>
								<h1><s:property value="#o.ostrategyname"/></h1>
								<span><s:property value="#o.ostrategybref"/> </span>
								<p><s:property value="#o.ostrategydescription"/></p>
							</figcaption>
						</figure>
					</li>					
				</s:iterator>
				</ul>
			</div>
		</div><!-- /container -->


</body>
</html>