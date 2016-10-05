<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>芳草寻源-寻源商城-支付订单</title>
<link href="css/pay/public.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/pay/base.css" />
<link rel="stylesheet" type="text/css" href="css/pay/buyConfirm.css" />
<link href="libs/bootstrap.min.css" rel="stylesheet" />
<link href="libs/font-awesome.min.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet" />
<script src="libs/jquery.min.js"></script>
<script src="js/base.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
</head>

<body>
	<%@ include file="navbar.jsp"%>
	<!--订单提交body部分开始-->
	<div class="border_top_cart">
		<div class="container payment-con">
			<form action="product_executePaymentOperation.do" id="pay-form"
				method="post" target="_blank">
				<input type="hidden" value="${orderItem.oprice}" name="orderprice" />
				<input type="hidden" value="${p.pname}" name="orderpname" /> <input
					type="hidden" value="${orderItem.oid}" name="orderoid" />
				<div class="order-info">
					<div class="msg">
						<h3>您的寻源订单已提交成功！付款咯～</h3>
						<p></p>
						<p class="post-date">成功付款后，请联系发布者是否收到款项并提醒发货</p>
					</div>
					<div class="info">
						<p>
							金额：<span class="pay-total">${p.shopprice}元</span>
						</p>
						<p>订单号：${p.pid}</p>
						<p>
							付款人：${orderItem.user.username} <span class="line">/</span>
							${orderItem.paddress} <span class="line">/</span>
							发布者联系电话：${p.contactnum}
						</p>
					</div>
					<div class="icon-box">
						<i class="iconfont"><img src="images/yes_ok.png" /></i>
					</div>
				</div>

				<div class="xm-plain-box">
					<div class="box-bd" id="bankList">
						<div class="payment-bd">
							<dl class="clearfix payment-box">
								<dt>
									<strong>银行网银</strong>
									<p>支持以下各银行借记卡及信用卡</p>

								</dt>
								<dd>
									<ul class="payment-list clearfix">
										<li><label for="CMBCHINA-NET-B2C"><input
												type="radio" name="pd_FrpId" id="CMBCHINA-NET-B2C"
												value="CMBCHINA-NET-B2C" checked="checked" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_zsyh.gif"
												alt="" /></label></li>
										<li><label for="ICBCB2C"><input type="radio"
												name="pd_FrpId" id="ICBC-NET-B2C" value="ICBC-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_gsyh.gif"
												alt="" /></label></li>
										<li><label for="ABC-NET-B2C"><input type="radio"
												name="pd_FrpId" id="ABC-NET-B2C" value="ABC-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_nyyh.gif"
												alt="" /></label></li>
										<li><label for="CCB-NET-B2C"><input type="radio"
												name="pd_FrpId" id="CCB-NET-B2C" value="CCB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_jsyh.gif"
												alt="" /></label></li>
										<li><label for="BCCB-NET-B2C"><input type="radio"
												name="pd_FrpId" id="BCCB-NET-B2C" value="BCCB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_bjyh.gif"
												alt="北京银行" /></label></li>
										<li><label for="BOCO-NET-B2C"><input type="radio"
												name="pd_FrpId" id="BOCO-NET-B2C" value="BOCO-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_jtyh.gif"
												alt="交通银行" /></label></li>
										<li><label for="CIB-NET-B2C"><input type="radio"
												name="pd_FrpId" id="CIB-NET-B2C" value="CIB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_xyyh.gif"
												alt="兴业银行" /></label></li>
										<li><label for="CMBC-NET-B2C"><input type="radio"
												name="pd_FrpId" id="CMBC-NET-B2C" value="CMBC-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_msyh.gif"
												alt="民生银行" /></label></li>
										<li><label for="CEB-NET-B2C"><input type="radio"
												name="pd_FrpId" id="CEB-NET-B2C" value="CEB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_gdyh.gif"
												alt="光大银行" /></label></li>
										<li><label for="BOC-NET-B2C"><input type="radio"
												name="pd_FrpId" id="BOC-NET-B2C" value="BOC-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_zgyh.gif"
												alt="中国银行" /></label></li>
										<li><label for="PINGANBANK-NET"><input
												type="radio" name="pd_FrpId" id="PINGANBANK-NET"
												value="PINGANBANK-NET" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_payh.gif"
												alt="平安银行" /></label></li>
										<li><label for="NBCB-NET-B2C"><input type="radio"
												name="pd_FrpId" id="NBCB-NET-B2C" value="NBCB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_nbyh.gif"
												alt="宁波银行" /></label></li>
										<li><label for="ECITIC-NET-B2C"><input
												type="radio" name="pd_FrpId" id="ECITIC-NET-B2C"
												value="ECITIC-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_zxyh.gif"
												alt="中信银行" /></label></li>
										<li><label for="SDB-NET-B2C"><input type="radio"
												name="pd_FrpId" id="SDB-NET-B2C" value="SDB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_sfyh.gif"
												alt="深圳发展银行" /></label></li>
										<li><label for="SHB-NET-B2C"><input type="radio"
												name="pd_FrpId" id="SHB-NET-B2C" value="SHB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_shyh.gif"
												alt="上海银行" /></label></li>
										<li><label for="POST-NET-B2C"><input type="radio"
												name="pd_FrpId" id="POST-NET-B2C" value="POST-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_youzheng.gif"
												alt="中国邮政" /></label></li>
										<li><label for="BJRCB-NET-B2C"><input
												type="radio" name="pd_FrpId" id="BJRCB-NET-B2C"
												value="BJRCB-NET-B2C" /> <img
												src="${pageContext.request.contextPath}/bank_img/payOnline_bjnsyh.gif"
												alt="北京农村商业银行" /></label></li>
									</ul>
								</dd>
							</dl>




						</div>
					</div>
					<div class="box-ft clearfix">
						<input type="submit" class="btn btn-primary" value="下一步"
							id="payBtn" /> <span class="tip"></span>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--订单提交body部分结束-->



	<!-- 底部 -->
	<div class="Pfooter">
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
