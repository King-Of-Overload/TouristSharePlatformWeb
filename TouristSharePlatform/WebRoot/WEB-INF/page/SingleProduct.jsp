<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<title>芳草寻源-寻源商城-${singleProduct.pname}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="芳草寻源  寻源商城  寻源小组  资源共享  旅行装备" />
<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="js/base.js"></script>
<!-- BootStrap样式 -->
<!-- @start全局样式 -->
<link href="css/productList/style.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/product/jquery.countdown.css" />
<!-- @end 全局样式 -->
<!--字体样式-->
<!-- <link
	href='http://fonts.useso.com/css?family=Raleway:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'> -->
<!-- dropdown -->
<script src="js/product/jquery.easydropdown.js"></script>
<!-- start menu -->
<link href="css/product/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="js/product/megamenu.js"></script>
<script>$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<script src="js/product/responsiveslides.min.js"></script>
<!-- 文章解析区核心代码，不要随意修改，需要修改请咨询@Alan -->
<script src="ueditor.parse.js"></script>
<link href="css/product/flexslider.css" rel='stylesheet' type='text/css' />
<script defer src="js/product/jquery.flexslider.js"></script>
<script type="text/javascript">
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			start : function(slider) {
				$('body').removeClass('loading');
			}
		});
	});
</script>
<!-- 产品放大镜核心区域 @Author Alan 不可修改 -->
<link rel="stylesheet" href="css/product/etalage.css">
<script src="js/product/jquery.etalage.min.js"></script>
<script src="js/singleProduct/singleProduct.js"></script>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="header_top"></div>
	<!-- @start logo与搜索区 -->
	<div class="header_bottom">
		<div class="container">
			<div class="header_bottom-box">
				<div class="header_bottom_left">
					<div class="logo">
						<a
							href="${pageContext.request.contextPath}/product_goToProductIndex.php"
							style="float: left; width: 250px;"><img
							src="images/weblogo.png" alt="" width="56px" height="56px;" /><font>芳草寻源旅行装备商城</font></a>
					</div>
					<ul class="clock">
						<i class="clock_icon"> </i>
						<li class="clock_desc">24小时运营</li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="header_bottom_right">
					<div class="search">
				<form action="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=all&categoryid=all&secondid=all&sort=all&pricerange=all" method="post">
			  <input type="text" value="请输入关键字查询" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入关键字查询';}" name="searchValue"/>
			  <input type="submit" value=""/>
			   <a href="${pageContext.request.contextPath}/product_goToPublishProduct.php"
							target="_blank" class="publish-button">免费发布</a>
			  </form>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- @end logo与搜索区 -->
	<div class="copyrights">
		@<a href="mailto:yzluxintao@163.com">芳草寻源小组</a>
	</div>
	<!-- @start 菜单区 -->
	<div class="menu" id="menu">
		<div class="container">
			<div class="menu_box">
				<ul class="megamenu skyblue">
					<li class="active grid"><a class="color2"
						href="${pageContext.request.contextPath}/product_goToProductIndex.php">商城首页</a></li>
					<li><a class="color10" href="javascript:void(0)">旅行服装&&鞋袜</a>
						<div class="megapanel">
							<div class="row">
								<s:if test="categoryList!=null">
									<s:subset source="categoryList" var="cList" start="0" count="2">
										<s:iterator value="#attr.cList" var="clt">
											<div class="col1">
												<div class="h_nav">
													<a
														href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
													<ul>
														<s:if test="#clt.listSecondCategory!=null">
															<s:iterator value="#clt.listSecondCategory" var="lsc">
																<li><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#lsc.pcsname" /></a></li>
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
															<img src="<s:property value='#l.coverImage'/>"
																class="img-responsive" alt="" width="60px" height="80px" />
														</div>
														<div class="p_right">
															<h4>
																<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)"/></a>
															</h4>
															<span class="item-cat"><small><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#l.secondCategory.pcsname" /></a></small></span> <span
																class="price">￥<s:property value="#l.shopprice" /></span>
														</div>
														<div class="clearfix"></div>
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<span>空空如也</span>
											</s:else>
										</ul>
									</div>
								</div>
							</div>
						</div></li>
					<li><a class="color7" href="javascript:void(0)">旅行背包&&露营</a>
						<div class="megapanel">
							<div class="row">
								<s:if test="categoryList!=null">
									<s:subset source="categoryList" var="cList" start="2" count="2">
										<s:iterator value="#attr.cList" var="clt">
											<div class="col1">
												<div class="h_nav">
													<a
														href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
													<ul>
														<s:if test="#clt.listSecondCategory!=null">
															<s:iterator value="#clt.listSecondCategory" var="lsc">
																<li><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#lsc.pcsname" /></a></li>
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
															<img src="<s:property value='#l.coverImage'/>"
																class="img-responsive" alt="" width="60px" height="80px" />
														</div>
														<div class="p_right">
															<h4>
																<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)" /></a>
															</h4>
															<span class="item-cat"><small><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#l.secondCategory.pcsname" /></a></small></span> <span
																class="price">￥<s:property value="#l.shopprice" /></span>
														</div>
														<div class="clearfix"></div>
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<span>空空如也</span>
											</s:else>
										</ul>
									</div>
								</div>
							</div>
						</div></li>
					<li><a class="color7" href="javascript:void(0)">旅行登山&&电子器材</a>
						<div class="megapanel">
							<div class="row">
								<s:if test="categoryList!=null">
									<s:subset source="categoryList" var="cList" start="4" count="2">
										<s:iterator value="#attr.cList" var="clt">
											<div class="col1">
												<div class="h_nav">
													<a
														href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
													<ul>
														<s:if test="#clt.listSecondCategory!=null">
															<s:iterator value="#clt.listSecondCategory" var="lsc">
																<li><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#lsc.pcsname" /></a></li>
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
															<img src="<s:property value='#l.coverImage'/>"
																class="img-responsive" alt="" width="60px" height="80px" />
														</div>
														<div class="p_right">
															<h4>
																<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)" /></a>
															</h4>
															<span class="item-cat"><small><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#l.secondCategory.pcsname" /></a></small></span> <span
																class="price">￥<s:property value="#l.shopprice" /></span>
														</div>
														<div class="clearfix"></div>
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<span>空空如也</span>
											</s:else>
										</ul>
									</div>
								</div>
							</div>
						</div></li>
					<li><a class="color7" href="javascript:void(0)">综合装备&&骑行装备</a>
						<div class="megapanel">
							<div class="row">
								<s:if test="categoryList!=null">
									<s:subset source="categoryList" var="cList" start="6" count="2">
										<s:iterator value="#attr.cList" var="clt">
											<div class="col1">
												<div class="h_nav">
													<a
														href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
													<ul>
														<s:if test="#clt.listSecondCategory!=null">
															<s:iterator value="#clt.listSecondCategory" var="lsc">
																<li><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#lsc.pcsname" /></a></li>
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
															<img src="<s:property value='#l.coverImage'/>"
																class="img-responsive" alt="" width="60px" height="80px" />
														</div>
														<div class="p_right">
															<h4>
																<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)" /></a>
															</h4>
															<span class="item-cat"><small><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#l.secondCategory.pcsname" /></a></small></span> <span
																class="price">￥<s:property value="#l.shopprice" /></span>
														</div>
														<div class="clearfix"></div>
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<span>空空如也</span>
											</s:else>
										</ul>
									</div>
								</div>
							</div>
						</div></li>
					<li><a class="color7" href="javascript:void(0)">滑雪&&其它装备</a>
						<div class="megapanel">
							<div class="row">
								<s:if test="categoryList!=null">
									<s:subset source="categoryList" var="cList" start="8" count="2">
										<s:iterator value="#attr.cList" var="clt">
											<div class="col1">
												<div class="h_nav">
													<a
														href="${pageContext.request.contextPath}/product_goToProductList.php?categoryid=${clt.cid}&page=1&sort=all&quality=all&pricerange=all&searchValue=all">${clt.cName}</a>
													<ul>
														<s:if test="#clt.listSecondCategory!=null">
															<s:iterator value="#clt.listSecondCategory" var="lsc">
																<li><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#lsc.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#lsc.pcsname" /></a></li>
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
															<img src="<s:property value='#l.coverImage'/>"
																class="img-responsive" alt="" width="60px" height="80px" />
														</div>
														<div class="p_right">
															<h4>
																<a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)" /></a>
															</h4>
															<span class="item-cat"><small><a
																	href="${pageContext.request.contextPath}/product_goToProductList.php?secondid=<s:property value='#l.secondCategory.pcsid'/>&page=1&sort=all&quality=all&pricerange=all&searchValue=all"><s:property
																			value="#l.secondCategory.pcsname" /></a></small></span> <span
																class="price">￥<s:property value="#l.shopprice" /></span>
														</div>
														<div class="clearfix"></div>
													</li>
												</s:iterator>
											</s:if>
											<s:else>
												<span>空空如也</span>
											</s:else>
										</ul>
									</div>
								</div>
							</div>
						</div></li>

					<div class="clearfix"></div>
				</ul>
			</div>
		</div>
	</div>
	<!-- @end 菜单区 -->
	<!-- @start 主区域 -->
	<div class="single" style="margin-top: 40px;">
		<div class="container">
		<input type="hidden" value="${singleProduct.pid}" class="currentProductId"/>
			<div class="single_top">
				<div class="col-md-9 single_right">
					<div class="grid images_3_of_2">
						<ul id="etalage">
							<s:if test="singleProduct.showImages!=null">
								<s:iterator value="singleProduct.showImages" var="simgs">
									<li><a href="javascript:void(0)">
											<!-- 此处可以添加回调 @Author Alan --> <img
											class="etalage_thumb_image" src="${simgs}"
											class="img-responsive" /> <img class="etalage_source_image"
											src="${simgs}" class="img-responsive" title="" />
									</a></li>
								</s:iterator>
							</s:if>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="desc1 span_3_of_2">
						<h1>
							<s:property value="singleProduct.pname" />
						</h1>
						<p class="m_5">
							￥
							<s:property value="singleProduct.shopprice" />
							<span class="reducedfrom">￥<s:property
									value="singleProduct.marketprice" /></span> <a href="javascript:void(0)">联系电话：<s:property
									value="singleProduct.contactnum" /></a>
						</p>
						<div class="btn_form">
							<form method="post" action="${pageContext.request.contextPath}/product_goToOrderCreate.php?pid=<s:property value='singleProduct.pid'/>" target="_blank">
							<input type="submit" value="购买" title="" class="submitOrder">
							</form>
						</div>
						<span class="m_link">
						</span>
						<p class="m_text2">
							<s:property value="singleProduct.pdescription" />
						</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="col-md-3">
					<!-- FlexSlider -->
					<section class="slider_flex">
						<div class="flexslider">
							<ul class="slides">
								<!-- TODO:等待修改 -->
								<li><img src="images/product/pic4.jpg"
									class="img-responsive" alt="" /></li>
								<li><img src="images/product/pic7.jpg"
									class="img-responsive" alt="" /></li>
								<li><img src="images/product/pic6.jpg"
									class="img-responsive" alt="" /></li>
								<li><img src="images/product/pic5.jpg"
									class="img-responsive" alt="" /></li>
							</ul>
						</div>
					</section>
					<!-- FlexSlider -->
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- @start 商品详细信息区 -->
			<div class="toogle">
				<h2>商品详情</h2>
				<!-- <p class="m_text2"></p> -->
				<div class="productDetail">
					<s:property value="singleProduct.pcontent" escapeHtml="false" />
				</div>
			</div>
			<!-- @end 商品详细信息区 -->
			<h4 class="head_single">Related Products</h4>
			<div class="span_3">
				<div class="col-sm-3 grid_1">
					<a href="single.html"> <img src="images/product/pic9.jpg"
						class="img-responsive" alt="" />
						<h3>parum clari</h3>
						<p>Duis autem vel eum iriure</p>
						<h4>Rs.399</h4>
					</a>
				</div>
				<div class="col-sm-3 grid_1">
					<a href="single.html"> <img src="images/product/pic8.jpg"
						class="img-responsive" alt="" />
						<h3>parum clari</h3>
						<p>Duis autem vel eum iriure</p>
						<h4>Rs.399</h4>
					</a>
				</div>
				<div class="col-sm-3 grid_1">
					<a href="single.html"> <img src="images/product/pic1.jpg"
						class="img-responsive" alt="" />
						<h3>parum clari</h3>
						<p>Duis autem vel eum iriure</p>
						<h4>Rs.399</h4>
					</a>
				</div>
				<div class="col-sm-3 grid_1">
					<a href="single.html"> <img src="images/product/pic3.jpg"
						class="img-responsive" alt="" />
						<h3>parum clari</h3>
						<p>Duis autem vel eum iriure</p>
						<h4>Rs.399</h4>
					</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- @end 主区域 -->

	<div class="footer">
		<div class="container">
			<ul class="footer_nav">
				<li><a href="${pageContext.request.contextPath}/index.php">芳草寻源首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/officialstrategy_goToOfficialStrategy.php">官方攻略</a></li>
				<li><a
					href="${pageContext.request.contextPath}/userstrategy_goToUserStrategy.php?page=1">攻略游记大全</a></li>
				<li><a
					href="${pageContext.request.contextPath}/photo_goToPhoto.php">精美相册</a></li>
				<li><a
					href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php">技法学院与素材</a></li>
				<li><a
					href="${pageContext.request.contextPath}/skillacademy_goToSkillAcademy.php">旅游装备</a></li>
				<li><a
					href="${pageContext.request.contextPath}/downloadPage/download.html">芳草寻源客户端</a></li>
			</ul>
			<p class="copy">Copyright &copy; 2016.芳草寻源小组倾情出品</p>
			<p class="copy">Copyright &copy; 2016.Team of the
				beauty-from-the-Fragrant-grass All rights reserved</p>
			<p class="copy">Copyright &copy; 2016. 芳香 のある 源 すべての権利が確保した</p>
		</div>
	</div>

</body>
</html>
