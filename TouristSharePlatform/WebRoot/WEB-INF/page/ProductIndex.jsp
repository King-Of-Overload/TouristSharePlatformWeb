<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
 <!DOCTYPE HTML>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<title>芳草寻源-寻源商城</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="芳草寻源  寻源商城  寻源小组  资源共享  旅行装备" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="js/base.js"></script>
<!-- BootStrap样式 -->
<!-- @start全局样式 -->
<link href="css/product/style.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/product/jquery.countdown.css" />
<!-- @end 全局样式 -->
<!--字体样式-->
<!-- <link href='http://fonts.useso.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'> -->
<!-- dropdown -->
<script src="js/product/jquery.easydropdown.js"></script>
<!-- start menu -->
<link href="css/product/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/product/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="js/product/responsiveslides.min.js"></script>
<script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: false,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
</script>
<script src="js/product/jquery.countdown.js"></script>
<script src="js/product/script.js"></script>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="header_top">

</div>
<!-- @start logo与搜索区 -->
<div class="header_bottom">
	<div class="container">
	 <div class="header_bottom-box">
		<div class="header_bottom_left">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/product_goToProductIndex.php" style="float: left;width: 250px;"><img src="images/weblogo.png" alt="" width="56px" height="56px;"/><font>芳草寻源旅行装备商城</font></a>
			</div>
			<ul class="clock">
				<i class="clock_icon"> </i>
				<li class="clock_desc">24小时运营</li>
			</ul>
			<div class="clearfix"> </div>
		</div>
		<div class="header_bottom_right">
			<div class="search">
			  <form action="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=all&categoryid=all&secondid=all&sort=all&pricerange=all" method="post">
			  <input type="text" value="请输入关键字查询" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入关键字查询';}" name="searchValue"/>
			  <input type="submit" value=""/>
			  <a href="${pageContext.request.contextPath}/product_goToPublishProduct.php" target="_blank" class="publish-button">免费发布</a>
			  </form>
	  		</div>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
</div>
<!-- @end logo与搜索区 -->
<div class="copyrights">@<a href="mailto:yzluxintao@163.com" >芳草寻源小组</a></div>
<!-- @start 菜单区 -->
<div class="menu" id="menu">
	<div class="container">
		<div class="menu_box">
	     <ul class="megamenu skyblue">
			<li class="active grid"><a class="color2" href="${pageContext.request.contextPath}/product_goToProductIndex.php">商城首页</a></li>			
			<li><a class="color10" href="javascript:void(0)">旅行服装&&鞋袜</a>
				<div class="megapanel">
					<div class="row">
						<s:if test="categoryList!=null">
					<s:subset source="categoryList" var="cList" start="0" count="2">
					<s:iterator value="#attr.cList" var="clt">
					<div class="col1">
							<div class="h_nav">
								<a href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
								<ul>
								<s:if test="#clt.listSecondCategory!=null">
								<s:iterator value="#clt.listSecondCategory" var="lsc">
								<li><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#lsc.pcsname"/></a></li>
								</s:iterator>
								</s:if>
								</ul>	
							</div>							
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
						
						<div class="col2">
							<div class="h_nav">
								<h4>热门</h4>
								<ul>
								<s:if test="oneTwoCatregoryPlist!=null">
								<s:iterator value="oneTwoCatregoryPlist" var="l">
								<li>
										<div class="p_left">
										   <img src="<s:property value='#l.coverImage'/>" class="img-responsive" alt="" width="60px" height="80px"/>
										</div>
										<div class="p_right">
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,29)"/></a></h4>
											<span class="item-cat"><small><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#l.secondCategory.pcsname"/></a></small></span>
											<span class="price">￥<s:property value="#l.shopprice"/></span>
										</div>
										<div class="clearfix"> </div>
									</li>
									</s:iterator>
								</s:if>
								<s:else><span>空空如也</span></s:else>
								</ul>	
							</div>												
						</div>
					  </div>
					</div>
			</li>
			<li><a class="color7" href="javascript:void(0)">旅行背包&&露营</a>
				<div class="megapanel">
					<div class="row">
					<s:if test="categoryList!=null">
					<s:subset source="categoryList" var="cList" start="2" count="2">
					<s:iterator value="#attr.cList" var="clt">
					<div class="col1">
							<div class="h_nav">
								<a href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
								<ul>
								<s:if test="#clt.listSecondCategory!=null">
								<s:iterator value="#clt.listSecondCategory" var="lsc">
								<li><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#lsc.pcsname"/></a></li>
								</s:iterator>
								</s:if>
								</ul>	
							</div>							
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
						<div class="col2">
							<div class="h_nav">
								<h4>热门</h4>
								<ul>
								<s:if test="threeFourCatregoryPlist!=null">
								<s:iterator value="threeFourCatregoryPlist" var="l">
								<li>
										<div class="p_left">
										   <img src="<s:property value='#l.coverImage'/>" class="img-responsive" alt="" width="60px" height="80px"/>
										</div>
										<div class="p_right">
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,29)"/></a></h4>
											<span class="item-cat"><small><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#l.secondCategory.pcsname"/></a></small></span>
											<span class="price">￥<s:property value="#l.shopprice"/></span>
										</div>
										<div class="clearfix"> </div>
									</li>
									</s:iterator>
								</s:if>
								<s:else><span>空空如也</span></s:else>
								</ul>	
							</div>												
						</div>
					  </div>
					</div>
			</li>
				<li><a class="color7" href="javascript:void(0)">旅行登山&&电子器材</a>
				<div class="megapanel">
					<div class="row">
					<s:if test="categoryList!=null">
					<s:subset source="categoryList" var="cList" start="4" count="2">
					<s:iterator value="#attr.cList" var="clt">
					<div class="col1">
							<div class="h_nav">
								<a href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
								<ul>
								<s:if test="#clt.listSecondCategory!=null">
								<s:iterator value="#clt.listSecondCategory" var="lsc">
								<li><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#lsc.pcsname"/></a></li>
								</s:iterator>
								</s:if>
								</ul>	
							</div>							
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
						<div class="col2">
							<div class="h_nav">
								<h4>热门</h4>
								<ul>
								<s:if test="fiveSixCatregoryPlist!=null">
								<s:iterator value="fiveSixCatregoryPlist" var="l">
								<li>
										<div class="p_left">
										   <img src="<s:property value='#l.coverImage'/>" class="img-responsive" alt="" width="60px" height="80px"/>
										</div>
										<div class="p_right">
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,29)"/></a></h4>
											<span class="item-cat"><small><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#l.secondCategory.pcsname"/></a></small></span>
											<span class="price">￥<s:property value="#l.shopprice"/></span>
										</div>
										<div class="clearfix"> </div>
									</li>
									</s:iterator>
								</s:if>
								<s:else><span>空空如也</span></s:else>
								</ul>	
							</div>												
						</div>
					  </div>
					</div>
			</li>
			<li><a class="color7" href="javascript:void(0)">综合装备&&骑行装备</a>
				<div class="megapanel">
					<div class="row">
					<s:if test="categoryList!=null">
					<s:subset source="categoryList" var="cList" start="6" count="2">
					<s:iterator value="#attr.cList" var="clt">
					<div class="col1">
							<div class="h_nav">
								<a href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
								<ul>
								<s:if test="#clt.listSecondCategory!=null">
								<s:iterator value="#clt.listSecondCategory" var="lsc">
								<li><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#lsc.pcsname"/></a></li>
								</s:iterator>
								</s:if>
								</ul>	
							</div>							
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
						<div class="col2">
							<div class="h_nav">
								<h4>热门</h4>
								<ul>
									<s:if test="sevenEightCatregoryPlist!=null">
								<s:iterator value="sevenEightCatregoryPlist" var="l">
								<li>
										<div class="p_left">
										   <img src="<s:property value='#l.coverImage'/>" class="img-responsive" alt="" width="60px" height="80px"/>
										</div>
										<div class="p_right">
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,29)"/></a></h4>
											<span class="item-cat"><small><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#l.secondCategory.pcsname"/></a></small></span>
											<span class="price">￥<s:property value="#l.shopprice"/></span>
										</div>
										<div class="clearfix"> </div>
									</li>
									</s:iterator>
								</s:if>
								<s:else><span>空空如也</span></s:else>
								</ul>	
							</div>												
						</div>
					  </div>
					</div>
			</li>
			<li><a class="color7" href="javascript:void(0)">滑雪&&其它装备</a>
				<div class="megapanel">
					<div class="row">
					<s:if test="categoryList!=null">
					<s:subset source="categoryList" var="cList" start="8" count="2">
					<s:iterator value="#attr.cList" var="clt">
					<div class="col1">
							<div class="h_nav">
								<a href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
								<ul>
								<s:if test="#clt.listSecondCategory!=null">
								<s:iterator value="#clt.listSecondCategory" var="lsc">
								<li><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#lsc.pcsname"/></a></li>
								</s:iterator>
								</s:if>
								</ul>	
							</div>							
						</div>
					</s:iterator>
					</s:subset>
					</s:if>
						<div class="col2">
							<div class="h_nav">
								<h4>热门</h4>
								<ul>
									<s:if test="nineEightCatregoryPlist!=null">
								<s:iterator value="nineEightCatregoryPlist" var="l">
								<li>
										<div class="p_left">
										   <img src="<s:property value='#l.coverImage'/>" class="img-responsive" alt="" width="60px" height="80px"/>
										</div>
										<div class="p_right">
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,29)"/></a></h4>
											<span class="item-cat"><small><a href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property value="#l.secondCategory.pcsname"/></a></small></span>
											<span class="price">￥<s:property value="#l.shopprice"/></span>
										</div>
										<div class="clearfix"> </div>
									</li>
									</s:iterator>
								</s:if>
								<s:else><span>空空如也</span></s:else>
								</ul>	
							</div>												
						</div>
					  </div>
					</div>
			</li>
			<div class="clearfix"> </div>
		 </ul>
	  </div>
</div>
</div>
<!-- @end 菜单区 -->
<!-- @start slider区域 -->
<div class="index_slider">
	<div class="container">
	  <div class="callbacks_container">
	      <ul class="rslides" id="slider">
	        <li><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=1a6c1c5ad85e4705b1869be0ffaf30c4" target="_blank"><img src="images/product/slider1.jpg" class="img-responsive" alt=""/></a></li>
	        <li><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=3fda057a7b464d4fb20f058678e03637" target="_blank"><img src="images/product/2.jpg" class="img-responsive" alt=""/></a></li>
	        <li><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=f7a44fcdb88441f4a9f9600cbd7fb0d1" target="_blank"><img src="images/product/3.jpg" class="img-responsive" alt=""/></a></li>
	      </ul>
	  </div>
	</div> 
</div>
<!-- @end slider区域 -->
<div class="content_top">
	<div class="container">
	<!-- @start 特色声明区域 -->
		<div class="grid_1">
			<div class="col-md-3">
			 <div class="box2">
			 	<ul class="list1">
			 		<i class="lock"> </i>
			 		<li class="list1_right"><p>即时互动共享，让您花最少的钱</p></li>
			 		<div class="clearfix"> </div>
			 	</ul>
			 </div>
			</div>
			<div class="col-md-3">
			 <div class="box3">
			 	<ul class="list1">
			 		<i class="clock1"> </i>
			 		<li class="list1_right"><p>方便寻找，终结选择综合症</p></li>
			 		<div class="clearfix"> </div>
			 	</ul>
			 </div>
			</div>
			<div class="col-md-3">
			 <div class="box4">
			 	<ul class="list1">
			 		<i><img alt="" src="images/encript.png" width="84px" height="80px" style="float: left;"></i>
			 		<li class="list1_right"><p>账号加密，保障安全</p></li>
			 		<div class="clearfix"> </div>
			 	</ul>
			 </div>
			</div>
			<div class="col-md-3">
			 <div class="box5">
			 	<ul class="list1">
			 		<i class="dollar"> </i>
			 		<li class="list1_right"><p>支持多家银行线上网银支付</p></li>
			 		<div class="clearfix"> </div>
			 	</ul>
			 </div>
			</div>
			<div class="clearfix"> </div>
		</div>
		<!-- @end 特色声明区域 -->
		<div class="column_center">
			<h1>芳草寻源在线商城，为您的旅行助力 </h1>
			<h2></h2>
		</div>
		<div class="sellers_grid">
			<ul class="sellers">
				<i class="star"> </i>
				<li class="sellers_desc"><h2>热门商品</h2></li>
				<div class="clearfix"> </div>
			</ul>
		</div>
		<div class="grid_2">
			<div class="col-md-3 span_6">
			  <div class="box_inner">
				<img src="images/product/p1.jpg" class="img-responsive" alt=""/>
				 <div class="sale-box"> </div>
				 <div class="desc">
				 	<h3>Ullamcorper suscipit</h3>
				 	<h4>178,90 $</h4>
				 	<ul class="list2">
				 	  <li class="list2_left"><span class="m_1"><a href="#" class="link">Add to Cart</a></span></li>
				 	  <li class="list2_right"><span class="m_2"><a href="#" class="link1">See More</a></span></li>
				 	  <div class="clearfix"> </div>
				 	</ul>
				 	<div class="heart"> </div>
				 </div>
			   </div>
			</div>
			<div class="col-md-3 span_6">
			  <div class="box_inner">
				<img src="images/product/p2.jpg" class="img-responsive" alt=""/>
				 <div class="sale-box"> </div>
				 <div class="desc">
				 	<h3>Ullamcorper suscipit</h3>
				 	<h4>178,90 $</h4>
				 	<ul class="list2">
				 	  <li class="list2_left"><span class="m_1"><a href="#" class="link">Add to Cart</a></span></li>
				 	  <li class="list2_right"><span class="m_2"><a href="#" class="link1">See More</a></span></li>
				 	  <div class="clearfix"> </div>
				 	</ul>
				 	<div class="heart"> </div>
				 </div>
			   </div>
			</div>
			<div class="col-md-3 span_6">
			  <div class="box_inner">
				<img src="images/product/p3.jpg" class="img-responsive" alt=""/>
				 <div class="sale-box"> </div>
				 <div class="desc">
				 	<h3>Ullamcorper suscipit</h3>
				 	<h4>178,90 $</h4>
				 	<ul class="list2">
				 	  <li class="list2_left"><span class="m_1"><a href="#" class="link">Add to Cart</a></span></li>
				 	  <li class="list2_right"><span class="m_2"><a href="#" class="link1">See More</a></span></li>
				 	  <div class="clearfix"> </div>
				 	</ul>
				 	<div class="heart"> </div>
				 </div>
			   </div>
			</div>
			<div class="col-md-3 span_6">
			  <div class="box_inner">
				<img src="images/product/p4.jpg" class="img-responsive" alt=""/>
				 <div class="sale-box"> </div>
				 <div class="desc">
				 	<h3>Ullamcorper suscipit</h3>
				 	<h4>178,90 $</h4>
				 	<ul class="list2">
				 	  <li class="list2_left"><span class="m_1"><a href="#" class="link">Add to Cart</a></span></li>
				 	  <li class="list2_right"><span class="m_2"><a href="#" class="link1">See More</a></span></li>
				 	  <div class="clearfix"> </div>
				 	</ul>
				 	<div class="heart"> </div>
				 </div>
			   </div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="content_middle">
	<div class="container">
		<ul class="promote">
			<i class="promote_icon"> </i>
			<li class="promote_head"><h3>为您推荐</h3></li>
		</ul>
		 <ul id="flexiselDemo3">
						<li><img src="images/product/n1.jpg"  class="img-responsive" /><div class="grid-flex"><h4>Contrary to popular </h4><p>589,90 $</p>
							<div class="m_3"><a href="#" class="link2">Add to Cart</a></div>
							<div class="ticket"> </div>
						</div></li>
						<li><img src="images/product/n2.jpg"  class="img-responsive" /><div class="grid-flex"><h4>Contrary to popular </h4><p>589,90 $</p>
							<div class="m_3"><a href="#" class="link2">Add to Cart</a></div>
							<div class="ticket"> </div>
						</div></li>
						<li><img src="images/product/n3.jpg"  class="img-responsive" /><div class="grid-flex"><h4>Contrary to popular </h4><p>589,90 $</p>
							<div class="m_3"><a href="#" class="link2">Add to Cart</a></div>
							<div class="ticket"> </div>
						</div></li>
						<li><img src="images/product/n4.jpg"  class="img-responsive" /><div class="grid-flex"><h4>Contrary to popular </h4><p>589,90 $</p>
							<div class="m_3"><a href="#" class="link2">Add to Cart</a></div>
							<div class="ticket"> </div>
						</div></li>
						<li><img src="images/product/n5.jpg"  class="img-responsive" /><div class="grid-flex"><h4>Contrary to popular </h4><p>589,90 $</p>
							<div class="m_3"><a href="#" class="link2">Add to Cart</a></div>
							<div class="ticket"> </div>
						</div></li>
				     </ul>
				    <script type="text/javascript">
					 $(window).load(function() {
						$("#flexiselDemo3").flexisel({
							visibleItems: 6,
							animationSpeed: 1000,
							autoPlay:true,
							autoPlaySpeed: 3000,    		
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
					    	responsiveBreakpoints: { 
					    		portrait: { 
					    			changePoint:480,
					    			visibleItems: 1
					    		}, 
					    		landscape: { 
					    			changePoint:640,
					    			visibleItems: 2
					    		},
					    		tablet: { 
					    			changePoint:768,
					    			visibleItems: 3
					    		}
					    	}
					    });
					    
					});
				   </script>
				   <script type="text/javascript" src="js/product/jquery.flexisel.js"></script>
	</div>
</div>
<div class="container">
   <div class="content_middle_bottom">
	  <div class="col-md-4">
		  <ul class="spinner">
			<i class="spinner_icon"> </i>
			<li class="spinner_head"><h3>显示热销</h3></li>
			<div class="clearfix"> </div>
		  </ul>
		  <div class="timer_box">
			<div class="thumb"> </div>
			<div class="timer_grid">
 			<ul id="countdown">
			</ul>
				<ul class="navigation">
					<li>
						<p class="timeRefDays">天</p>
					</li>
					<li>
						<p class="timeRefHours">时</p>
					</li>
					<li>
						<p class="timeRefMinutes">分</p>
					</li>
					<li>
						<p class="timeRefSeconds">秒</p>
					</li>
				</ul>
          </div>
          <div class="thumb_desc">
          	<h3> totam rem aperiam</h3>
          	<div class="price">
			   <span class="reducedfrom">$140.00</span>
			   <span class="actual">$120.00</span>
			</div>
		 </div>
		 <a href="#"><div class="m_3 deal"><div class="link3">购买该产品</div></div></a>
		</div>
		</div>
		<div class="col-md-8">
		  <ul class="spinner">
			<i class="paperclip"> </i>
			<li class="spinner_head"><h3>相关产品文章</h3></li>
			<div class="clearfix"> </div>
		  </ul>
		      <div class="a-top">
				 <div class="left-grid">
					<img src="images/product/t4.jpg" class="img-responsive" alt=""/>
				 </div>
				 <div class="right-grid">
					<h4><a href="#">Duis autem vel eum iriure dolor in hendrerit</a></h4>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat</p>
				 </div>
				 <div class="but">
				   <a class="arrow" href="#"> </a>
				 </div>
				 <div class="clearfix"></div>
			 </div>
			 <div class="a-top">
				 <div class="left-grid">
					<img src="images/product/t5.jpg" class="img-responsive" alt=""/>
				 </div>
				 <div class="right-grid">
					<h4><a href="#">Duis autem vel eum iriure dolor in hendrerit</a></h4>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat</p>
				 </div>
				 <div class="but">
				   <a class="arrow" href="#"> </a>
				 </div>
				 <div class="clearfix"></div>
			 </div>
			 <div class="a-top">
				 <div class="left-grid">
					<img src="images/product/t6.jpg" class="img-responsive" alt=""/>
				 </div>
				 <div class="right-grid">
					<h4><a href="#">Duis autem vel eum iriure dolor in hendrerit</a></h4>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat</p>
				 </div>
				 <div class="but">
				   <a class="arrow" href="#"> </a>
				 </div>
				 <div class="clearfix"></div>
			 </div>
			 <div class="a-top">
				 <div class="left-grid">
					<img src="images/product/t7.jpg" class="img-responsive" alt=""/>
				 </div>
				 <div class="right-grid">
					<h4><a href="#">Duis autem vel eum iriure dolor in hendrerit</a></h4>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat</p>
				 </div>
				 <div class="but">
				   <a class="arrow" href="#"> </a>
				 </div>
				 <div class="clearfix"></div>
			 </div>
		</div>
		<div class="clearfix"></div>
	</div>
	 <!-- @start 底部关于我们与联系我们 -->
    <div class="content_bottom">
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="box_icon"> </i>
			 <li class="spinner_head"><h3>芳草寻源</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		  <img src="images/sub-nav-logo.jpg" class="img-responsive" width="255px" height="124px"/>
    	</div>
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="bubble"> </i>
			 <li class="spinner_head"><h3>关于我们</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		  <p>芳草寻源旅游共享平台，为您提供旅游攻略、精美相册、旅行贴士、二手旅游装备的共享、发布以及下载的一站式服务，wish you happy</p>
    	</div>
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="mail"> </i>
			 <li class="spinner_head"><h3>联系我们</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		 <ul class="social">
			<li><a href="www.twitter.com" title="twitter" target="_blank"><img alt="" src="images/product/twitter.png" width="35px" height="35px" style="background-position:-437px -102px;"></a></li>
		    <li><a target="_blank" href="http://weibo.com/yzluxintaoweibo/home" title="microblog"><img alt="" src="images/product/weiblog.png" width="35px" height="35px" style="background-position:-394px -145px;"></a></li>
			<li><a href="http://wpa.qq.com/msgrd?v=3&uin=570036271&site=qq&menu=yes" title="QQ" target="_blank"><img alt="" src="images/product/QQ.png" width="35px" height="35px" style="background-position:-445px -145px;"></a></li>
			<li><a href="www.instagram.com" title="instagram" target="_blank"><img alt="" src="images/product/instagram.png" width="35px" height="35px" style="background-position:-18px -222px;"></a></li>
			<li><a href="mailto:yzluxintao@163.com" title="e-mail" target="_blank"><img alt="" src="images/product/mail.png" width="35px" height="35px" style="background-position:-64px -222px;"></a></li>
		</ul>
    	</div>
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="mail"> </i>
			 <li class="spinner_head"><h3>友情提示</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		  <p>芳草寻源旅游装备平台为自由交易平台，可自由发布产品，本站不会以任何理由向您索要账号以及密码信息，请注意自我信息保护</p>
		  <p style="float: right;">——芳草寻源项目组</p>
    	</div>
    	<div class="clearfix"> </div>
    </div>
    <!-- @end 底部关于我们与联系我们 -->
</div>
<div class="footer">
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

</body>
</html>		