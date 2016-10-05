package alan.share.product.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import alan.share.product.model.OrderItem;
import alan.share.product.model.PersonalAddress;
import alan.share.product.model.Product;
import alan.share.product.model.ProductCategory;
import alan.share.product.model.ProductIndexCategoryBean;
import alan.share.product.model.ProductSecondCategory;
import alan.share.product.model.UserCollections;
import alan.share.product.service.ProductService;
import alan.share.skillacademy.service.SkillAcademyService;
import alan.share.user.model.TripUser;
import alan.share.user.service.TripUserService;
import alan.share.utils.CookieUtil;
import alan.share.utils.DomUtil;
import alan.share.utils.PageBean;
import alan.share.utils.PaymentUtil;
import alan.share.utils.UUIDUtils;
import net.sf.json.JSONArray;

public class ProductAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ProductService productService;
	private TripUserService userService;
	private SkillAcademyService academyService;
	private String pd_FrpId;//通过form接收支付通道编码
	private String orderprice;//价格
	private String orderpname;//产品名
	private String orderoid;//订单号
	private String searchValue;//搜索关键词
	
	
	
	public SkillAcademyService getAcademyService() {
		return academyService;
	}
	public void setAcademyService(SkillAcademyService academyService) {
		this.academyService = academyService;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(String orderprice) {
		this.orderprice = orderprice;
	}
	public String getOrderpname() {
		return orderpname;
	}
	public void setOrderpname(String orderpname) {
		this.orderpname = orderpname;
	}
	public String getOrderoid() {
		return orderoid;
	}
	public void setOrderoid(String orderoid) {
		this.orderoid = orderoid;
	}
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public TripUserService getUserService() {
		return userService;
	}
	public void setUserService(TripUserService userService) {
		this.userService = userService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	/**
	 * 跳转到商城主页
	 * @return
	 */
	public String goToProductIndex(){
		try {
			this.encodingReqAndRes();
			//TODO:
			//热门商品区域数据
//			List<Product> hotProductList=productService.findAllHotProducts();
//			List<Product> hotProducts=new ArrayList<>();//热门商品数据
//			if(hotProductList!=null&&!hotProductList.isEmpty()){
//				for(int i=0;i<4;i++){
//					hotProducts.add(hotProductList.get(i));
//				}
//			}
//			//为您推荐产品
//			List<Product> productList=productService.findAllRandomProduct();
//			List<Product> products=new ArrayList<>();//推荐产品数据
//			if(productList!=null&&!productList.isEmpty()){
//				for(int i=0;i<10;i++){
//					products.add(productList.get(i));
//				}
//			}
//			//限时热销产品
//			Product limitP=productService.findSingleProductByPid("1a6c1c5ad85e4705b1869be0ffaf30c4");
//			//相关产品文章
//			List<SkillAcademy> academyList=academyService.findRelatedProductsBlogs();
//			if(academyList.size()>4){
//				academyList.subList(0, 4);
//			}
			//读取一级分类以及所属的二级分类
			ValueStack stack=ActionContext.getContext().getValueStack();
			this.initialProductMenu(stack);
//			stack.set("hotProducts", hotProducts);//热销产品数据
//			stack.set("products", products);//推荐产品数据
//			stack.set("limitP", limitP);//显示热销产品
//			stack.set("academyList", academyList);//技法学院数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToProductIndex";
	}
	

	
	/**
	 * 跳转到发布产品界面
	 * @return
	 */
	public String goToPublishProduct(){
		try {
			this.encodingReqAndRes();
			String userid=CookieUtil.readCookieReturnId(request);
			TripUser existUser=(TripUser) request.getSession().getAttribute("sessionUser");
			if(existUser!=null){
				if(null!=userid&&!("").equals(userid)){
					if(existUser.getUserid().equals(userid)){
						//查询所有一级分类
						List<ProductCategory> categoryList=productService.findAllProductCategory();
						//查询第一个一级分类的二级分类
						List<ProductSecondCategory> secondCategroyList=productService.findProductSecondCategoryByCategory(categoryList.get(0));
						ValueStack stack=ActionContext.getContext().getValueStack();
						stack.set("categoryList", categoryList);//一级分类数据
						stack.set("secondCategroyList", secondCategroyList);//第一个一级分类的数据
					}else{
						return "loginPage";
					}
				}else{
					return "loginPage";
				}
			}else{
				return "loginPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToPublishProduct";
	}
	
	/**
	 * 跳转到商品列表界面
	 * @return
	 */
	public String goToProductList(){
		try {
			this.encodingReqAndRes();
			int page=Integer.parseInt(request.getParameter("page"));//不可能为空
			String searchValue=request.getParameter("searchValue");//搜索关键词，all
			String categoryid=request.getParameter("categoryid");//一级分类id，可能为空
			String secondid=request.getParameter("secondid");//二级分类id,可能为空
			String sort=request.getParameter("sort");//排序条件 all hot hightolow lowtohigh
			String quality=request.getParameter("quality");//品质 all  brandnew 99new 98new 95new 90new 80new
			String pricerange=request.getParameter("pricerange");//all 99p 199p 299p 499p 800p
			if(searchValue==null||searchValue.equals("")){
				if(null!=this.searchValue&&!("").equals(this.searchValue)){
					searchValue=this.searchValue;
				}
			}
			PageBean<Product> productList=null;
			if(("all").equals(categoryid)&&("all").equals(secondid)&&(null!=searchValue&&!("").equals(searchValue))){
				productList=productService.findProductByMutapleConditionWithPagination(page,searchValue,sort,quality,pricerange);
			}else if(null!=categoryid&&!("").equals(categoryid)){//一级分类不空
				productList=productService.findProductByCidMutipleConditionWithPagination(page,categoryid,sort,quality,pricerange,searchValue);
			}else if(null!=secondid&&!("").equals(secondid)){//二级分类不空
				productList=productService.findProductByCsidMutipleConditionWithPagination(page,secondid,sort,quality,pricerange,searchValue);
			}else{
				return "pageNotFound";
			}
			if(productList.getList()!=null&&productList.getList().size()>0){
				List<Product> products=productList.getList();
				for(int i=0;i<products.size();i++){
					Product p=products.get(i);
					p.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(p.getPcontent()));
				}
			}
			ValueStack stack=ActionContext.getContext().getValueStack();
			this.initialProductMenu(stack);
			stack.set("categoryid", categoryid);//一级分类id
			stack.set("secondid", secondid);//二级分类id
			stack.set("sort", sort);//排序条件
			stack.set("quality", quality);//品质
			stack.set("pricerange", pricerange);//价格范围
			stack.set("productList", productList);//产品数据
			stack.set("searchValue", searchValue);//搜索关键字
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToProductList";
	}
	
	/**
	 * 跳转到显示单个商品的界面
	 * @return
	 */
	public String goToSingleProduct(){
		try {
			this.encodingReqAndRes();
			Product p=null;
			String pid=request.getParameter("pid");//商品id
			if(null!=pid&&!("").equals(pid)){
				p=productService.findSingleProductByPid(pid);
				Boolean updateResult=productService.updateSingleProductClickStatus(p);
				if(updateResult==false){
					return "pageNotFound";
				}
			}else{
				return "pageNotFound";
			}
			//TODO:
			//查找出相关一级分类的产品
//			List<Product> categoryProductsList=productService.findProductsByCategory(p.getSecondCategory().getCategory());
//			categoryProductsList.subList(0, 4);
			//查出本二级分类相关产品
//			List<Product> secCategoryPList=productService.findProductsBySecCategory(p.getSecondCategory());
//			secCategoryPList.subList(0,4);
			ValueStack stack=ActionContext.getContext().getValueStack();
			this.initialProductMenu(stack);
			stack.set("singleProduct", p);
			//stack.set("categoryProductsList", categoryProductsList);//一级分类相关产品
			//stack.set("secCategoryPList", secCategoryPList);//本二级分类相关产品
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToSingleProduct";
	}
	
	/**
	 * 跳转到商品订单生成界面
	 * @return
	 */
	public String goToOrderCreate(){
		try {
			this.encodingReqAndRes();
			String userid=CookieUtil.readCookieReturnId(request);
			if(null!=userid&&!("").equals(userid)){
				TripUser existUser=userService.findUserByUserId(userid);
				request.getSession().invalidate();
				request.getSession().setAttribute("sessionUser", existUser);
				String pid=request.getParameter("pid");
				Product p=productService.findSingleProductByPid(pid);
				p.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(p.getPcontent()));
				List<PersonalAddress> addressList=productService.findUserAddressByTripUser(existUser);
				ValueStack stack=ActionContext.getContext().getValueStack();
				stack.set("userid", existUser.getUserid());
				stack.set("singleProduct", p);//产品数据
				stack.set("addressList", addressList);//地址数据
			}else{
				return "loginPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToOrderCreate";
	}
	
	/**
	 * 提交订单并跳转到支付界面
	 * @return
	 */
	public String submitOrder(){
		try {
			this.encodingReqAndRes();
			String userid=request.getParameter("userid");
			String pid=request.getParameter("pid");
			String addressid=(String) request.getSession().getAttribute("addressid");
			String payoid=(String) request.getSession().getAttribute("payoid");
			OrderItem item=null;
			Product p=null;
			if(("").equals(payoid)||null==payoid){
				TripUser buyer=userService.findUserByUserId(userid);
				p=productService.findSingleProductByPid(pid);
				PersonalAddress address=productService.findUserAddressByAddressId(addressid);
				item=productService.saveOrderItem(buyer,p,address);
				request.getSession().setAttribute("payoid", item.getOid());
				request.getSession().setAttribute("pid", pid);
			}else if(pid.equals(request.getSession().getAttribute("pid"))){
				item=productService.findOrderItemById((String)request.getSession().getAttribute("payoid"));
				p=productService.findSingleProductByPid(pid);
			}else{
				TripUser buyer=userService.findUserByUserId(userid);
				p=productService.findSingleProductByPid(pid);
				PersonalAddress address=productService.findUserAddressByAddressId(addressid);
				item=productService.saveOrderItem(buyer,p,address);
				request.getSession().setAttribute("payoid", item.getOid());
				request.getSession().setAttribute("pid", pid);
			}
			if(item!=null){
				ValueStack stack=ActionContext.getContext().getValueStack();
				stack.set("p", p);
				stack.set("orderItem", item);
				return "goToPay";//转向支付界面
			}else{
				return "pageNotFound";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "NONE";
	}
	
	
	/**
	 * 执行付款操作
	 * @return
	 */
	public String executePaymentOperation(){
		try {
			//this.encodingReqAndRes();
			//response.setCharacterEncoding("GBK");
			//生成支付请求参数
			String p0_Cmd="Buy";//业务类型
			String p1_MerId="";//商户编号，由第三方支付提供，需要注册
			Properties p=new Properties();
			InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("merchantInfo.properties");
			p.load(in);
			p1_MerId=p.getProperty("p1_MerId");//商户编号赋值
			String p2_Order=orderoid;//商户订单号，由form赋值
			//String p3_Amt=orderprice;//支付金额
			String p3_Amt="1.00";//支付金额
			String p4_Cur="CNY";//支付币种 人民币
			String p5_Pid=orderpname;//商品名称
			String p6_Pcat="";//商品种类 ，不传
			String p7_Pdesc="";//商品描述，不传
			String p8_Url="http://localhost:8080/TouristSharePlatform/payment_callback.php";//商户接收支付成功数据的地址,回调地址
			String p9_SAF="";//送货地址，不传
			String pa_MP="";//商户扩展信息，不传
			String pd_FrpId=this.pd_FrpId;//支付通道编码
			String pr_NeedResponse="1";//应答机制
			String keyValue=p.getProperty("keyValue");//签名秘钥，需要申请
			String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//签名加密数据
			//重定向到支付接口  正式请求地址: https://www.yeepay.com/app-merchant-proxy/node
			StringBuffer buffer=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			buffer.append("p0_Cmd=").append(p0_Cmd).append("&");
			buffer.append("p1_MerId=").append(p1_MerId).append("&");
			buffer.append("p2_Order=").append(p2_Order).append("&");
			buffer.append("p3_Amt=").append(p3_Amt).append("&");
			buffer.append("p4_Cur=").append(p4_Cur).append("&");
			buffer.append("p5_Pid=").append(p5_Pid).append("&");
			buffer.append("p6_Pcat=").append(p6_Pcat).append("&");
			buffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
			buffer.append("p8_Url=").append(p8_Url).append("&");
			buffer.append("p9_SAF=").append(p9_SAF).append("&");
			buffer.append("pa_MP=").append(pa_MP).append("&");
			buffer.append("pd_FrpId=").append(pd_FrpId).append("&");
			buffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			buffer.append("hmac=").append(hmac);
			//执行重定向:
			response.sendRedirect(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * radio选中改变异步访问获取选中地址信息
	 * @return
	 */
	public String registerAddressId(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String addressid=request.getParameter("addressid");
			request.getSession().setAttribute("addressid", addressid);
			PersonalAddress a=productService.findUserAddressByAddressId(addressid);
			List<PersonalAddress> list=new ArrayList<>();
			list.add(a);
			JSONArray array=productService.instoreAddressToJsonArray(list);
			System.out.println(array.toString());
			out.print(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * ajax删除地址对象
	 * @return
	 */
	public String deleteAddress(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=CookieUtil.readCookieReturnId(request);
			TripUser existUser=userService.findUserByUserId(userid);
			int addressid=Integer.parseInt(request.getParameter("addressid"));
			Boolean result=productService.deletePersonalAddressById(addressid);
			if(result==true){
				List<PersonalAddress> addressList=productService.findUserAddressByTripUser(existUser);
				JSONArray addressArray=productService.instoreAddressToJsonArray(addressList);
				System.out.println(addressArray.toString());
				out.print(addressArray.toString());
			}else{
				out.print("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * ajax更新用户地址信息，并且回送最新的地址集合
	 * @return
	 */
	public String asyncObtainAddress(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String paddress=request.getParameter("paddress");//地址
			String buyerName=request.getParameter("buyerName").trim();//收货人姓名
			String cellphone=request.getParameter("cellphone");//手机号码
			String userid=request.getParameter("userid");//用户id
			String existUserId=CookieUtil.readCookieReturnId(request);
			TripUser existUser=null;
			if(null!=existUserId&&!("".equals(existUserId))){
				existUser=userService.findUserByUserId(userid);
				request.getSession().invalidate();
				request.getSession().setAttribute("sessionUser", existUser);
			}else{
				return "loginPage";
			}
			Boolean result=productService.savePaddress(paddress,buyerName,cellphone,existUser);//保存地址
			if(result==true){
				List<PersonalAddress> addressList=productService.findUserAddressByTripUser(existUser);
				JSONArray addressArray=productService.instoreAddressToJsonArray(addressList);
				System.out.println(addressArray.toString());
				out.print(addressArray.toString());
			}else{
				return "pageNotFound";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return "NONE";
	}
	
	
	
	/**
	 * ajax保存产品信息
	 * 参数表：
	 * secondCategoryName 二级分类名称
	 * qualitychoice 成色
	 * productname 产品名
	 * description 简要说明
	 * shopprice 价格
	 * marketprice 市场价格
	 * cityName 所在城市名
	 * phonenum 手机号码
	 * htmlContent html产品详细介绍
	 * plainText 产品详细介绍纯文本
	 * @return
	 */
	public String saveProductData(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String secondCategoryName=request.getParameter("secondCategoryName").trim();
			String qualitychoice=request.getParameter("qualitychoice");
			String productname=request.getParameter("productname");
			String description=request.getParameter("description");
			String shopprice=request.getParameter("shopprice");
			String marketprice=request.getParameter("marketprice");
			String cityName=request.getParameter("cityName");
			String phonenum=request.getParameter("phonenum");
			String htmlContent=request.getParameter("htmlContent");
			String plainText=request.getParameter("plainText");
			String username=request.getParameter("username");
			String pid=UUIDUtils.getUUID();
			Boolean result=productService.saveProduct(username,pid,secondCategoryName,qualitychoice,productname,description,shopprice,marketprice,cityName,phonenum,htmlContent,plainText);
			if(result==true){
				System.out.println("{'result':'success','pid':'"+pid+"'}");
				out.print("{'result':'success','pid':'"+pid+"'}");
			}else if(result==false){
				out.print("{'result':'error'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * ajax异步更新二级分类下拉菜单信息
	 * @return
	 */
	public String updateSecondCategory(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String categoryName=request.getParameter("categoryName");
			if(categoryName!=null&&!categoryName.equals("")){
				ProductCategory pc=productService.findProductCategoryByCName(categoryName);
				List<ProductSecondCategory> secondList=productService.findProductSecondCategoryByCategory(pc);
				JSONArray jsonArray=productService.instoreProductSecondCategoryToJsonArray(secondList);
				System.out.println(jsonArray.toString());
				out.print(jsonArray.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 检查产品收藏情况
	 * @return
	 */
	public String checkProductCollectStatus(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String pid=request.getParameter("pid");
			TripUser existUser=userService.findUserByUserId(userid);
			UserCollections collection=productService.findCollectionByUserAndPid(existUser,pid);
			if(null!=collection){
				out.print("colleced");
			}else{
				out.print("notcollected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 收藏产品
	 * @return
	 */
	public String collectProduct(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			String userid=request.getParameter("userid");
			String pid=request.getParameter("pid");
			TripUser existUser=userService.findUserByUserId(userid);
			Boolean result=productService.saveUserCollection(existUser,pid);
			if(result==true){
				out.print("success");
			}else{
				out.print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 从用户资料区转向支付界面
	 * @return
	 */
	public String goToPayFromUser(){
		try {
			this.encodingReqAndRes();
			String oid=request.getParameter("oid");
			OrderItem item=productService.findOrderItemById(oid);
			Product p=item.getProduct();
			ValueStack stack=ActionContext.getContext().getValueStack();
			stack.set("p", p);
			stack.set("orderItem", item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goToPay";
	}
	
	/**
	 * ajax重定向
	 * @return
	 */
	public String redirectAjax(){
		PrintWriter out=null;
		try {
			this.encodingReqAndRes();
			out=response.getWriter();
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) out.close();
		}
		return NONE;
	}
	
	/**
	 * 初始化商城菜单数据与热门数据
	 * @param stack
	 */
	private void initialProductMenu(ValueStack stack){
		List<ProductIndexCategoryBean> categoryList=productService.instoreProductCategoryAndSecondCategory();
		List<Product> oneTwoCatregoryPlist=productService.findProductByTwoCategory(1,2);
		List<Product> threeFourCatregoryPlist=productService.findProductByTwoCategory(3,4);
		List<Product> fiveSixCatregoryPlist=productService.findProductByTwoCategory(5,6);
		List<Product> sevenEightCatregoryPlist=productService.findProductByTwoCategory(7,8);
		List<Product> nineEightCatregoryPlist=productService.findProductByTwoCategory(9,10);
		stack.set("categoryList", categoryList);//商品分类数据
		stack.set("oneTwoCatregoryPlist", oneTwoCatregoryPlist);
		stack.set("threeFourCatregoryPlist", threeFourCatregoryPlist);
		stack.set("fiveSixCatregoryPlist", fiveSixCatregoryPlist);
		stack.set("sevenEightCatregoryPlist", sevenEightCatregoryPlist);
		stack.set("nineEightCatregoryPlist", nineEightCatregoryPlist);
	}
	
	
	//request与response编码
		private void encodingReqAndRes() throws IOException{
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
		}
		
		
	
}
