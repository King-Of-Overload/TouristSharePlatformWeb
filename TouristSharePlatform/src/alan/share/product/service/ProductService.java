package alan.share.product.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import alan.share.officialstrategy.dao.OfficialStrategyDao;
import alan.share.officialstrategy.model.City;
import alan.share.product.dao.ProductDao;
import alan.share.product.model.OrderItem;
import alan.share.product.model.PersonalAddress;
import alan.share.product.model.Product;
import alan.share.product.model.ProductCategory;
import alan.share.product.model.ProductIndexCategoryBean;
import alan.share.product.model.ProductSecondCategory;
import alan.share.product.model.UserCollections;
import alan.share.skillacademy.model.SkillAcademy;
import alan.share.user.dao.TripUserDao;
import alan.share.user.model.TripUser;
import alan.share.utils.DomUtil;
import alan.share.utils.PageBean;
import alan.share.utils.StringUtil;
import alan.share.utils.UUIDUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductService {
	private ProductDao productDao;
	private TripUserDao userDao;
	private OfficialStrategyDao officialStrategyDao;

	public OfficialStrategyDao getOfficialStrategyDao() {
		return officialStrategyDao;
	}

	public void setOfficialStrategyDao(OfficialStrategyDao officialStrategyDao) {
		this.officialStrategyDao = officialStrategyDao;
	}

	public TripUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(TripUserDao userDao) {
		this.userDao = userDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 封装一级分类与二级分类数据
	 * 
	 * @return
	 */
	public List<ProductIndexCategoryBean> instoreProductCategoryAndSecondCategory() {
		List<ProductIndexCategoryBean> results = new ArrayList<>();
		ProductIndexCategoryBean bean = null;
		List<ProductCategory> cList = productDao.findAllProductCategory();// 找出所有一级分类
		if (cList != null && cList.size() > 0) {
			for (ProductCategory p : cList) {
				bean = new ProductIndexCategoryBean();
				bean.setCid(p.getPcategoryid());
				bean.setcName(p.getPcategoryname());
				List<ProductSecondCategory> secondCList = productDao.findSecondCategoryByProductCategory(p);
				if (secondCList != null && secondCList.size() > 0) {
					bean.setListSecondCategory(secondCList);
				}
				results.add(bean);
			}
		}
		return results;
	}

	/**
	 * 查询所有商品的一级分类
	 * 
	 * @return
	 */
	public List<ProductCategory> findAllProductCategory() {
		return productDao.findAllProductCategory();
	}

	/**
	 * 根据一级分类id查询所有二级分类集合
	 * 
	 * @param pcategoryid
	 *            一级分类id
	 * @return
	 */
	public List<ProductSecondCategory> findProductSecondCategoryByCategory(ProductCategory p) {
		return productDao.findSecondCategoryByCategory(p);
	}

	/**
	 * 根据一级分类名查出一级分类对象
	 * 
	 * @param categoryName
	 *            一级分类名称
	 * @return
	 */
	public ProductCategory findProductCategoryByCName(String categoryName) {
		return productDao.findProductCategoryByCategoryName(categoryName);
	}

	/**
	 * 将二级分类集合封装成json数组
	 * 
	 * @param secondList
	 *            二级分类集合
	 * @return
	 */
	public JSONArray instoreProductSecondCategoryToJsonArray(List<ProductSecondCategory> secondList) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jObject = null;
		try {
			if (secondList != null && secondList.size() > 0) {
				for (ProductSecondCategory p : secondList) {
					jObject = new JSONObject();
					jObject.put("pcsid", p.getPcsid());
					jObject.put("pcsname", p.getPcsname());
					jsonArray.put(jObject);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jsonArray;
	}

	/**
	 * 保存产品信息
	 * 
	 * @param username
	 *            用户名
	 * @param pid
	 *            商品id
	 * @param secondCategoryName
	 *            二级分类名
	 * @param qualitychoice
	 *            成品
	 * @param productname
	 *            产品名
	 * @param description
	 *            简介
	 * @param shopprice
	 *            商城价
	 * @param marketprice
	 *            市场价
	 * @param cityName
	 *            城市名
	 * @param phonenum
	 *            手机号
	 * @param htmlContent
	 *            商品详细介绍内容
	 * @param plainText
	 *            商品介绍详细内容(不带格式)
	 * @return
	 * @throws ParseException
	 */
	public Boolean saveProduct(String username, String pid, String secondCategoryName, String qualitychoice,
			String productname, String description, String shopprice, String marketprice, String cityName,
			String phonenum, String htmlContent, String plainText) throws ParseException {
		TripUser seller = userDao.findByUserName(username);// 发布者
		ProductSecondCategory sc = productDao.findProductSecondCategoryByCSName(secondCategoryName);
		City city = officialStrategyDao.getCity(cityName);// 城市对象
		BigDecimal shopDecimal = new BigDecimal(shopprice);// 商城价
		BigDecimal marketDecimal = new BigDecimal(marketprice);// 市场价
		Product product = new Product();
		product.setCity(city);
		product.setContactnum(phonenum);
		product.setMarketprice(marketDecimal);
		product.setPclickednum(0);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		product.setPdate(format.parse(format.format(c.getTime())));
		product.setPdescription(description);
		product.setPid(pid);
		product.setPishot(1);
		product.setPname(productname);
		product.setPplaintext(plainText);
		product.setQualitychoice(qualitychoice);
		product.setSecondCategory(sc);
		product.setSeller(seller);
		product.setShopprice(shopDecimal);
		product.setPcontent(htmlContent);
		Boolean result = productDao.saveProduct(product);
		return result;
	}

	/**
	 * 一级分类带分页多条件查询商品
	 * 
	 * @param page
	 *            当前页
	 * @param categoryid
	 *            一级分类id
	 * @param sort
	 *            排序方式
	 * @param quality
	 *            品质
	 * @param pricerange
	 *            价格范围
	 * @param 搜索关键词
	 * @return
	 */
	public PageBean<Product> findProductByCidMutipleConditionWithPagination(int page, String categoryid, String sort,
			String quality, String pricerange,String searchValue) {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setPage(page);// 设置当前页码
		int limit = 9;
		pageBean.setLimit(limit);// 设置每页显示的记录数
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountProductByMutipleCondition(categoryid, sort, quality, pricerange,searchValue);
		pageBean.setTotalCount(totalCount);// 设置总记录数
		// 总页数设置
		int totalPage = 0;
		if (totalCount <= pageBean.getLimit()) {
			totalPage = 1;
		} else {
			if (totalCount % limit == 0) {
				totalPage = totalCount % limit;
			} else {
				totalPage = totalCount % limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);// 设置总页数
		int begin=(page-1)*limit;
		List<Product> list=productDao.findProductByCidMutipleConditionWithPagination(begin,limit,categoryid,sort,quality,pricerange,searchValue);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 二级分类带分页多条件查询商品
	 * @param page
	 * @param secondid
	 * @param sort
	 * @param quality
	 * @param pricerange
	 * @param searchValue
	 * @return
	 */
	public PageBean<Product> findProductByCsidMutipleConditionWithPagination(int page, String secondid, String sort,
			String quality, String pricerange,String searchValue) {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setPage(page);// 设置当前页码
		int limit = 9;
		pageBean.setLimit(limit);// 设置每页显示的记录数
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountProductByCsidMutipleCondition(secondid, sort, quality, pricerange,searchValue);
		pageBean.setTotalCount(totalCount);// 设置总记录数
		// 总页数设置
		int totalPage = 0;
		if (totalCount <= pageBean.getLimit()) {
			totalPage = 1;
		} else {
			if (totalCount % limit == 0) {
				totalPage = totalCount % limit;
			} else {
				totalPage = totalCount % limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);// 设置总页数
		int begin=(page-1)*limit;
		List<Product> list=productDao.findProductByCsidMutipleConditionWithPagination(begin,limit,secondid,sort,quality,pricerange,searchValue);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 菜单栏热销产品显示
	 * @param i
	 * @param j
	 * @return
	 */
	public List<Product> findProductByTwoCategory(int cid1, int cid2) {
		List<Product> result=productDao.findProductByTwoCategory(cid1,cid2);
		List<Product> r=new ArrayList<>();
		if(result!=null&&result.size()>0){
			for(int i=0;i<result.size();i++){
				Product p=result.get(i);
				p.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(p.getPcontent()));
			}
			int length=0;
			if(result.size()>3){
				length=3;
			}else{
				length=result.size();
			}
			for(int i=0;i<length;i++){
				r.add(result.get(i));
			}
		}
		return r;
	}

	/**
	 * 根据产品id查出产品对象
	 * @param pid 产品id
	 * @return
	 */
	public Product findSingleProductByPid(String pid) {
		Product p=productDao.findSingleProductByPid(pid);
		if(p!=null){
			p.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(p.getPcontent()));
			List<String> imgs=DomUtil.getImageListFromHtmlDocument(p.getPcontent(), 5);
			p.setShowImages(imgs);
		}
		return p;
	}

	/**
	 * 更新单个产品的点击量
	 * @param p
	 * @return
	 */
	public Boolean updateSingleProductClickStatus(Product p) {
		Product product=p;
		product.setPclickednum(p.getPclickednum()+1);
		return productDao.updateSingleProductClickStatus(product);
	}

	/**
	 * 根据用户对象查询地址集合
	 * @param existUser
	 * @return
	 */
	public List<PersonalAddress> findUserAddressByTripUser(TripUser existUser) {
		return productDao.findUserAddressByTripUser(existUser);
	}

	/**
	 * 保存新的用户地址
	 * @param paddress 完整地址
	 * @param buyerName 买家名字
	 * @param cellphone 联系电话
	 * @param existUser 用户对象
	 * @return
	 */
	public Boolean savePaddress(String paddress, String buyerName, String cellphone, TripUser existUser) {
		PersonalAddress address=new PersonalAddress();
		address.setPaddress(paddress);
		address.setPaddressname(buyerName);
		address.setPhone(cellphone);
		address.setUser(existUser);
		return productDao.savePaddress(address);
	}

	/**
	 * 将地址集合封装成json数组
	 * @param addressList  地址集合
	 * @return
	 */
	public JSONArray instoreAddressToJsonArray(List<PersonalAddress> addressList) {
		JSONArray array=new JSONArray();
		JSONObject object=null;
		if(addressList!=null&&addressList.size()>0){
			for(PersonalAddress address:addressList){
				object=new JSONObject();
				object.put("addressid", address.getPaddressid());
				object.put("address", address.getPaddress());
				object.put("addressname", address.getPaddressname());
				object.put("phone", address.getPhone());
				array.put(object);
			}
		}
		return array;
	}

	/**
	 * 根据地址id删除地址对象
	 * @param addressid 地址id
	 * @return
	 */
	public Boolean deletePersonalAddressById(int addressid) {
		PersonalAddress a=productDao.findUserAddressById(addressid);
		Boolean result=productDao.deletePersonalAddress(a);
		return result;
	}

	/**
	 * 根据地址id查找地址对象
	 * @param addressid  地址id
	 * @return
	 */
	public PersonalAddress findUserAddressByAddressId(String addressid) {
		return productDao.findUserAddressByAddressId(addressid);
	}

	/**
	 * 保存订单信息
	 * @param buyer 买家对象
	 * @param p 产品信息
	 * @param address 地址对象
	 * @return
	 * @throws ParseException 
	 */
	public OrderItem saveOrderItem(TripUser buyer, Product p, PersonalAddress address) throws ParseException {
		OrderItem item=new OrderItem();
		item.setOid(UUIDUtils.getUUID());
		item.setOprice(p.getShopprice());
		item.setOstate(1);//未付款
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		item.setOtime(format.parse(format.format(calendar.getTime())));
		String addressText=address.getPaddress()+"( "+address.getPaddressname()+"收)";
		item.setPaddress(addressText);
		item.setProduct(p);
		item.setUser(buyer);
		OrderItem result=productDao.saveOrderItem(item);
		return result;
	}

	/**
	 * 根据订单id查询订单对象
	 * @param attribute
	 * @return
	 */
	public OrderItem findOrderItemById(String attribute) {
		return productDao.findOrderItemByItemId(attribute);
	}

	/**
	 * 根据关键字搜索商品
	 * @param page
	 * @param searchValue
	 * sort,quality,pricerange
	 * @return
	 */
	public PageBean<Product> findProductByMutapleConditionWithPagination(int page, String searchValue,String sort
			,String quality,String pricerange) {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setPage(page);// 设置当前页码
		int limit = 9;
		pageBean.setLimit(limit);// 设置每页显示的记录数
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountProductBySearchValue(searchValue,sort,quality,pricerange);
		pageBean.setTotalCount(totalCount);// 设置总记录数
		// 总页数设置
		int totalPage = 0;
		if (totalCount <= pageBean.getLimit()) {
			totalPage = 1;
		} else {
			if (totalCount % limit == 0) {
				totalPage = totalCount % limit;
			} else {
				totalPage = totalCount % limit + 1;
			}
		}
		pageBean.setTotalPage(totalPage);// 设置总页数
		int begin=(page-1)*limit;
		List<Product> list=productDao.findProductsBySearchValue(searchValue,sort,quality,pricerange,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据一级分类查询相关产品
	 * @param category 一级分类对象
	 * @return
	 */
	public List<Product> findPrductByCategory(ProductCategory category) {
		return productDao.findProductByCategory(category);
	}

	/**
	 * 随机查找出热门商品
	 * @return
	 */
	public List<Product> findAllHotProducts() {
		return productDao.findAllHotProducts();
	}

	/**
	 * 随机查出所有产品
	 * @return
	 */
	public List<Product> findAllRandomProduct() {
		return productDao.findAllRandomProduct();
	}

	/**
	 * 根据一级分类查找产品
	 * @param category
	 * @return
	 */
	public List<Product> findProductsByCategory(ProductCategory category) {
		return productDao.findProductsByCategory(category);
	}

	/**
	 * 根据二级分类查找产品
	 * @param secondCategory
	 * @return
	 */
	public List<Product> findProductsBySecCategory(ProductSecondCategory secondCategory) {
		return productDao.findProductsBySecCategory(secondCategory);
	}

	/**
	 * 根据用户与产品id查询收藏
	 * @param existUser
	 * @param pid
	 * @return
	 */
	public UserCollections findCollectionByUserAndPid(TripUser existUser, String pid) {
		Product p=productDao.findSingleProductByPid(pid);
		return productDao.findCollectionsByUserAndProduct(existUser,p);
	}

	/**
	 * 收藏产品
	 * @param existUser
	 * @param pid
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public Boolean saveUserCollection(TripUser existUser, String pid) throws ParseException {
		Product p=productDao.findSingleProductByPid(pid);
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserCollections collection=new UserCollections();
		collection.setCollectdate(format.parse(format.format(c.getTime())));
		collection.setCollectid(UUIDUtils.getUUID());
		collection.setProduct(p);
		collection.setUser(existUser);
		return productDao.saveUserCollection(collection);
	}

	/**
	 * 根据用户id找出用户订单
	 * @param userid
	 * @return
	 */
	public List<OrderItem> findOrdersByUserId(String userid) {
		TripUser user=userDao.findUserByUserId(userid);
		return productDao.findOrdersByUser(user);
	}

	/**
	 * 将订单集合转成json数组
	 * @param items
	 * @return
	 */
	public JSONArray enstoreOrderItemListToJSONArray(List<OrderItem> items) {
		JSONArray array=new JSONArray();
		JSONObject object=null;
		try {
			if(null!=items&&!items.isEmpty()){
				for(OrderItem item:items){
					object=new JSONObject();
					object.put("oid", item.getOid());
					object.put("oprice", item.getOprice().toString());
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					object.put("otime", format.format(item.getOtime()));
					object.put("ostate", item.getOstate());
					object.put("username", item.getUser().getUsername());
					JSONObject pObject=new JSONObject();
					pObject.put("pid", item.getProduct().getPid());
					pObject.put("pname", item.getProduct().getPname());
					object.put("product", pObject);
					object.put("paddress", item.getPaddress());
					array.put(object);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return array;
	}


}
