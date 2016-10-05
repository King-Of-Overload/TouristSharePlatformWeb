<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
 <!DOCTYPE HTML>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<title>芳草寻源-寻源商城-商品大全</title>
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
<link href="css/productList/style.css" rel='stylesheet' type='text/css' />
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
<script src="js/productList/productList.js"></script>
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
		<input type="hidden" value="${searchValue}" class="searchVal"/>
		<input type="hidden" value="${categoryid}" class="categoryid"/>
		<input type="hidden" value="${secondid}" class="secondcid"/>
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
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}" target="_blank"><s:property value="#l.pname.substring(0,37)"/></a></h4>
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
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)"/></a></h4>
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
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)"/></a></h4>
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
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)"/></a></h4>
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
											<h4><a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=${l.pid}"><s:property value="#l.pname.substring(0,37)"/></a></h4>
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
<!-- @start 主区域 -->
<div class="men">
	<div class="container">
	  <div class="col-md-3 sidebar">
	  	<div class="block block-layered-nav">
		    <div class="block-title">
		        <strong><span>筛选条件</span></strong>
		    </div>
    <div class="block-content">
                                    
            <dl id="narrow-by-list">
            <dt class="odd">价格区间</dt>
                    <dd class="odd">
<ol>
    <li>
           <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=${quality}&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=99p&searchValue=${searchValue}"><span class="price1">￥&nbsp;0,00</span> - <span class="price1">￥&nbsp;99,99</span></a>
               
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=${quality}&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=199p&searchValue=${searchValue}"><span class="price1">￥&nbsp;100,00</span> - <span class="price1">￥&nbsp;199,99</span></a>
                     
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=${quality}&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=299p&searchValue=${searchValue}"><span class="price1">￥&nbsp;200,00</span> - <span class="price1">￥&nbsp;299,99</span></a>
                   
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=${quality}&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=499p&searchValue=${searchValue}"><span class="price1">￥&nbsp;400,00</span> - <span class="price1">￥&nbsp;499,99</span></a>
                
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=${quality}&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=800p&searchValue=${searchValue}"><span class="price1">￥&nbsp;800,00</span>以上</a>
                
            </li>
</ol>
</dd>
     <dt class="even">品质</dt>
     <dd class="even">
<ol>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=brandnew&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=${pricerange}&searchValue=${searchValue}">全新</a>
                      
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=99new&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=${pricerange}&searchValue=${searchValue}">99新</a>
                      
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=98new&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=${pricerange}&searchValue=${searchValue}">98新</a>
                   
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=95new&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=${pricerange}&searchValue=${searchValue}">95新</a>
                   
            </li>
    <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=90new&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=${pricerange}&searchValue=${searchValue}">90新</a>
                   
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/product_goToProductList.php?page=1&quality=80new&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=${pricerange}&searchValue=${searchValue}">80新及以下</a>
                      
            </li>
</ol>
</dd>

 </dl>
   </div>
</div>
<div class="block block-cart">
        <div class="block-title">
        <strong><span>Carrello</span></strong>
    </div>
    <div class="block-content">
                        <p class="empty">You have no items in your shopping cart.</p>
        </div>
</div>
<div class="block block-list block-compare">
    <div class="block-title">
        <strong><span>Compare Products                    </span></strong>
    </div>
    <div class="block-content">
            <p class="empty">You have no items to compare.</p>
        </div>
</div>
</div>
<div class="col-md-9">
	<div class="mens-toolbar">
          <div class="sort">
               	<div class="sort-by">
		            <label>排序条件</label>
		            <select class="sortSelect">
		            <option value="hot">当前热门</option>
		            <option value="hightolow">价格由高到低</option>
		            <option value="lowtohigh">价格由低到高</option>
		            </select>
		        </div>
    		</div>
	        <div class="pager">   
	       		<ul class="dc_pagination dc_paginationA dc_paginationA06" style="float: right;">
				    <li><a href="#" class="previous">页数</a></li>
				    <s:iterator begin="1" end="productList.totalPage" var="pp">
				      <li><a href="${pageContext.request.contextPath}/product_goToProductList.php?page=<s:property value='#pp'/>&quality=${quality}&categoryid=${categoryid}&secondid=${secondid}&sort=${sort}&pricerange=${pricerange}&searchValue=${searchValue}"><s:property value="#pp"/></a></li>
				    </s:iterator>
			  	</ul>
		   		<div class="clearfix"></div>
	    	</div>
     	    <div class="clearfix"></div>
	     </div>
	          <div class="span_2">
	          <s:if test="productList.list!=null">
	          <s:iterator value="productList.list" var="pl">
	           <div class="col_1_of_single1 span_1_of_single1">
	          	    <a href="${pageContext.request.contextPath}/product_goToSingleProduct.php?pid=<s:property value='#pl.pid'/>">
				     <img src="<s:property value='#pl.coverImage'/>" class="img-responsive" alt="" width="200" height="150"/>
				     <h3><s:property value='#pl.pname'/></h3>
				   	 <p><s:property value="#pl.pdescription.substring(0,40)"/></p>
				   	 <h4>￥<s:property value="#pl.shopprice"/></h4>
			         </a>  
				  </div> 
	          </s:iterator>
	          </s:if>
	          <s:else>
	          <span>空空如也</span>
	          </s:else>
            </div>
      </div>
</div>
<!-- @end 主区域 -->

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