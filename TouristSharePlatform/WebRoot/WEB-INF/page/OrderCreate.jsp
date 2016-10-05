<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<title>芳草寻源-寻源商城-订单生成</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="format-detection" content="telephone=no" />
<link rel="stylesheet" href="css/ordercreate/tasp.css" />
<link href="css/ordercreate/orderconfirm.css" rel="stylesheet" />
<link href="libs/bootstrap.min.css" rel="stylesheet">
<link href="libs/font-awesome.min.css" rel="stylesheet">
<link href="css/base.css" rel="stylesheet">
<script src="libs/jquery.min.js"></script>
<script src="js/base.js"></script>
<script src="libs/bootstrap.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<!-- 省市联动组件相关 -->
<link href="css/city-picker.css" rel="stylesheet">
<script src="js/city-picker.data.js"></script>
<script src="js/city-picker.js"></script>
<!-- 对话框事件 -->
<link rel="stylesheet" href="css/x0popup.min.css">
<script src="js/x0popup.min.js"></script>
<script src="js/ordercreate/ordercreate.js"></script>
<style>
#page {
	width: auto;
	margin-top: 145px;
}

#comm-header-inner, #content {
	width: 950px;
	margin: auto;
}

#logo {
	padding-top: 26px;
	padding-bottom: 12px;
}

#header .wrap-box {
	margin-top: -67px;
}

#logo .logo {
	position: relative;
	overflow: hidden;
	display: inline-block;
	width: 140px;
	height: 35px;
	font-size: 35px;
	line-height: 35px;
	color: #f40;
}
</style>

</head>
<%@ include file="navbar.jsp"%>
<body data-spm="1">
	<div id="page">

		<div id="content" class="grid-c">
			<div id="address" class="address" style="margin-top: 20px;"
				data-spm="2">
				<s:property value='userid' />
				<input type="hidden" class="currentUser"
					value="<s:property value='userid'/>">
				<form name="addrForm" id="addrForm" action="#">

					<h3>确认收货地址</h3>
					<ul id="address-list" class="address-list">
						<s:if test="addressList!=null">
							<s:iterator value="addressList" var="al">
								<s:if test="#al==addressList[0]">
									<li class="J_Addr J_MakePoint clearfix  J_DefaultAddr">
										<div class="address-info">
											<input name="address" class="J_MakePoint" type="radio"
												value="${al.paddressid}" id="address" checked="checked">
											<label for="addrId_674944241" class="user-address"><s:property
													value="#al.paddress" />(${al.paddressname} 收) <em
												class="userphone">${al.phone}</em>&nbsp;&nbsp;<a
												href="javascript:void(0);"
												onclick="deleteAddress(${al.paddressid})"><em>删除</em></a> </label>
										</div>
									</li>
								</s:if>
								<s:else>
									<li class="J_Addr J_MakePoint clearfix  J_DefaultAddr">
										<div class="address-info">
											<input name="address" class="J_MakePoint " type="radio"
												value="${al.paddressid}" id="address"> <label
												for="addrId_674944241" class="user-address"><s:property
													value="#al.paddress" />(${al.paddressname} 收) <em
												class="userphone">${al.phone}</em>&nbsp;&nbsp;<a
												href="javascript:void(0);"
												onclick="deleteAddress(${al.paddressid})"><em>删除</em></a> </label>
										</div>
									</li>
								</s:else>
							</s:iterator>
						</s:if>
						<s:else>
							<span>没有地址，赶紧添加一个吧</span>
						</s:else>
					</ul>
					<div class="address-bar">
						<a href="#" class="new J_MakePoint" id="J_NewAddressBtn"
							data-toggle="modal" data-target="#newAddressModal">使用新地址</a>
					</div>

				</form>
			</div>
			<form id="J_Form" name="J_Form" action="" method="post">
				<div>
					<h3 class="dib">确认订单信息</h3>
					<table cellspacing="0" cellpadding="0" class="order-table"
						id="J_OrderTable" summary="统一下单订单信息区域">
						<caption style="display: none">统一下单订单信息区域</caption>
						<thead>
							<tr>
								<th class="s-title">产品
									<hr />
								</th>
								<th class="s-price">市场价(元)
									<hr />
								</th>
								<th class="s-amount">数量
									<hr />
								</th>
								<th class="s-agio">优惠方式(元)
									<hr />
								</th>
								<th class="s-total">发布者定价(元)
									<hr />
								</th>
							</tr>
						</thead>



						<tbody data-spm="3" class="J_Shop" data-tbcbid="0"
							data-outorderid="47285539868" data-isb2c="false"
							data-postMode="2" data-sellerid="1704508670">
							<tr class="first">
								<td colspan="5"></td>
							</tr>
							<tr class="shop blue-line">
								<td colspan="3"><span class="seller">卖家：<a
										href="http://member1.taobao.com/member/user_profile.jhtml?user_id=ac5831c32f47bc9267fcb75b71b5eed6"
										target="_blank" class="J_MakePoint"
										data-point-url="http://log.mmstat.com/buy.1.15"><s:property
												value="singleProduct.seller.username" /></a></span> <span
									class="J_WangWang" data-nick="淘米魅" data-display="inline"></span>

								</td>
								<td colspan="2" class="promo">
									<div>
										<ul class="scrolling-promo-hint J_ScrollingPromoHint">
										</ul>
									</div>
								</td>
							</tr>
							<tr class="item"
								data-lineid="19614514619:31175333266:35612993875"
								data-pointRate="0">
								<td class="s-title"><a href="#" target="_blank"
									title=""
									class="J_MakePoint"
									data-point-url="http://log.mmstat.com/buy.1.5"> 
									<img src="${singleProduct.coverImage}" class="itempic" width="60px" height="60px"><span class="title J_MakePoint"
										data-point-url="http://log.mmstat.com/buy.1.5">${singleProduct.pname}</span></a>

									<div class="props">
										<span>品质: ${singleProduct.qualitychoice} </span> <span>发布者: ${singleProduct.seller.username} </span>
									</div> <a title="消费者保障服务，卖家承诺商品如实描述" href="#" target="_blank"> <img
										src="http://img03.taobaocdn.com/tps/i3/T1bnR4XEBhXXcQVo..-14-16.png" />
								</a>
									<div>
										<span style="color: gray;">卖家已通过小源审核</span>
									</div></td>
								<td class="s-price"><span class='price '> <em
										class="style-normal-small-black J_ItemPrice">${singleProduct.marketprice}</em>
								</span> <input type="hidden" name="costprice" value="${singleProduct.marketprice}"
									class="J_CostPrice" /></td>
								<td class="s-amount" data-point-url=""><input type="hidden"
									class="J_Quantity" value="1"
									name="19614514619_31175333266_35612993875_quantity" />1</td>
								<td class="s-agio">
									<div class="J_Promotion promotion" data-point-url="">无优惠</div>
								</td>
								<td class="s-total"><span class='price '> <em
										class="style-normal-bold-red J_ItemTotal ">${singleProduct.shopprice}</em>
								</span> <input id="furniture_service_list_b_47285539868" type="hidden"
									name="furniture_service_list_b_47285539868" /></td>
							</tr>



							<tr class="item-service">
								<td colspan="5" class="servicearea" style="display: none"></td>
							</tr>

							<tr class="blue-line" style="height: 2px;">
								<td colspan="5"></td>
							</tr>

							<tr class="shop-total blue-line">
								<td colspan="5">合计<span class="J_Exclude"
									style="display: none">不</span><span class="J_ServiceText"
									style="display: none">，服务费</span>： <span
									class='price g_price '> <span>&yen;</span><em
										class="style-middle-bold-red J_ShopTotal"><s:property value="singleProduct.shopprice"/></em>
								</span> <input type="hidden" name="1704508670:2|creditcard"
									value="false" /> <input type="hidden" id="J_IsLadderGroup"
									name="isLadderGroup" value="false" />

								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5">

									<div class="order-go" data-spm="4">
										<div class="J_AddressConfirm address-confirm">
											<div class="kd-popup pop-back" style="margin-bottom: 40px;">
												<div class="box">
													<div class="bd">
														<div class="point-in">

															<em class="t">总金额：</em> <span class='price g_price '>
																<span>&yen;</span><em class="style-large-bold-red"
																id="J_ActualFee"><s:property value="singleProduct.shopprice"/></em>
															</span>
														</div>

														<ul>
															<li><em>寄送至:</em><span id="J_AddrConfirm"
																style="word-break: break-all;"> <s:property
																		value="addressList[0].paddress" /></span></li>
															<li><em>收货人:</em><span id="J_AddrNameConfirm"><s:property value="addressList[0].paddressname"/>
																	<s:property value="addressList[0].phone"/> </span></li>
														</ul>
													</div>
												</div>
												<a
													href="${pageContext.request.contextPath}/product_submitOrder.do?userid=${userid}&pid=${singleProduct.pid}"
													id="J_Go" class=" btn-go" data-point-url="" tabindex="0"
													title="点击此按钮，提交订单。">提交订单<b class="dpl-button"></b></a>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>

				<input type="hidden" id="J_useSelfCarry" name="useSelfCarry"
					value="false" /> <input type="hidden" id="J_selfCarryStationId"
					name="selfCarryStationId" value="0" /> <input type="hidden"
					id="J_useMDZT" name="useMDZT" value="false" /> <input
					type="hidden" name="useNewSplit" value="true" /> <input
					type="hidden" id="J_sellerIds" name="allSellIds" value="1704508670" />
			</form>
		</div>

		<div id="footer"></div>
	</div>
	<div class="orderfooter">
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


	<!--模态框-->
	<div class="modal fade" id="newAddressModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="height: 430px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="ModalLabel">添加新收货地址</h3>
				</div>
				<div class="modal-body" style="height: 300px;">
					<!-- @start 省市联动组件 -->
					<label for="distpicker">请选择地址(省市区)：</label>
					<div id="distpicker">
						<div class="form-group" style="float: left;">
							<div style="position: relative;">
								<input id="city-picker3" class="form-control" readonly
									type="text" value="江苏省/扬州市/广陵区" data-toggle="city-picker">
							</div>
						</div>
						<div class="form-group">
							<button class="btn btn-warning" id="reset" type="button"
								style="margin-left: 10px;">重置</button>
						</div>
					</div>
					<!-- @end 省市联动组件 -->
					<label for="pAddress">请完善详细地址(路 街道 小区 门牌号)：</label>
					<textarea class="pAddress form-control" rows="1" id="pAddress"></textarea>
					<label for="buyerName">收货人姓名：</label>
					<textarea class="buyerName form-control" rows="1" id="buyerName"></textarea>
					<label for="cellphone">联系电话：</label>
					<textarea class="cellphone form-control" rows="1" id="cellphone"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="submit btn btn-primary"
						data-dismiss="modal" id="submitAddress">提交</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
