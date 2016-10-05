package alan.share.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.sun.javafx.geom.Point2D;

import alan.share.product.model.OrderItem;
import alan.share.product.model.PersonalAddress;
import alan.share.product.model.Product;
import alan.share.product.model.ProductCategory;
import alan.share.product.model.ProductSecondCategory;
import alan.share.product.model.UserCollections;
import alan.share.user.model.TripUser;
import alan.share.utils.DomUtil;
import alan.share.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	/**
	 * 找出所有一级分类对象
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProductCategory> findAllProductCategory() {
		String hql = "FROM ProductCategory";
		List<ProductCategory> results = null;
		try {
			List<ProductCategory> list = this.getHibernateTemplate().find(hql);
			if (list != null && list.size() > 0) {
				results = list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据商品一级分类对象找出对应的所有二级分类对象
	 * 
	 * @param p
	 *            商品一级分类对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProductSecondCategory> findSecondCategoryByProductCategory(ProductCategory p) {
		String hql = "FROM ProductSecondCategory WHERE category=?";
		List<ProductSecondCategory> results = null;
		try {
			List<ProductSecondCategory> list = this.getHibernateTemplate().find(hql, p);
			if (list != null && list.size() > 0) {
				results = list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据一级分类id查出一级分类对象
	 * 
	 * @param pcategoryid
	 *            一级分类id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProductCategory findProductCategoryByCategoryId(int pcategoryid) {
		String hql = "FROM ProductCategory WHERE pcategoryid=?";
		ProductCategory results = null;
		try {
			List<ProductCategory> list = this.getHibernateTemplate().find(hql, pcategoryid);
			if (list != null && list.size() > 0) {
				results = list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据一级分类对象查询相关二级分类对象
	 * 
	 * @param c
	 *            一级分类对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProductSecondCategory> findSecondCategoryByCategory(ProductCategory c) {
		String hql = "FROM ProductSecondCategory WHERE category=?";
		List<ProductSecondCategory> results = null;
		try {
			List<ProductSecondCategory> list = this.getHibernateTemplate().find(hql, c);
			if (list != null && list.size() > 0) {
				results = list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 
	 * @param categoryName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProductCategory findProductCategoryByCategoryName(String categoryName) {
		String hql = "FROM ProductCategory WHERE pcategoryname=?";
		ProductCategory results = null;
		try {
			List<ProductCategory> list = this.getHibernateTemplate().find(hql, categoryName);
			if (list != null && list.size() > 0) {
				results = list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据二级分类名查询二级分类对象
	 * 
	 * @param secondCategoryName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProductSecondCategory findProductSecondCategoryByCSName(String secondCategoryName) {
		String hql = "FROM ProductSecondCategory WHERE pcsname=?";
		ProductSecondCategory results = null;
		try {
			List<ProductSecondCategory> list = this.getHibernateTemplate().find(hql, secondCategoryName);
			if (list != null && list.size() > 0) {
				results = list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 保存产品对象
	 * 
	 * @param product
	 * @return
	 */
	@Transactional
	public Boolean saveProduct(Product product) {
		Boolean result = false;
		try {
			this.getHibernateTemplate().save(product);
			result = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 统计多条件根据一级分类产品数量
	 * 
	 * @param categoryid
	 * @param sort
	 * @param quality
	 * @param pricerange
	 * @param sort 排序条件 all hot hightolow lowtohigh 
	 * @param quality 品质 all brandnew 99new 98new 95new 90new 80new 
	 * @param pricerange all 99p 199p 299p 499p 800p
	 * @param searchValue 搜索关键字
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountProductByMutipleCondition(String categoryid, String sort, String quality, String pricerange,String searchValue) {
		int result = 0;
		StringBuilder hql = new StringBuilder();
		ProductCategory c = this.findProductCategoryByCategoryId(Integer.parseInt(categoryid));
		hql.append("SELECT COUNT(*) FROM Product WHERE secondCategory.category=?");
		if (quality.equals("brandnew")) {
			hql.append(" AND qualitychoice='全新'");
		} else if (quality.equals("99new")) {
			hql.append(" AND qualitychoice='99新'");
		} else if (quality.equals("98new")) {
			hql.append(" AND qualitychoice='98新'");
		} else if (quality.equals("95new")) {
			hql.append(" AND qualitychoice='95新'");
		} else if (quality.equals("90new")) {
			hql.append(" AND qualitychoice='90新'");
		} else if (quality.equals("80new")) {
			hql.append(" AND qualitychoice='80新及以下'");
		}
		if (pricerange.equals("99p")) {
			hql.append(" AND shopprice BETWEEN 0.00 and 99.99");
		} else if (pricerange.equals("199p")) {
			hql.append(" AND shopprice BETWEEN 100.00 and 199.99");
		} else if (pricerange.equals("299p")) {
			hql.append(" AND shopprice BETWEEN 200.00 and 299.99");
		} else if (pricerange.equals("499p")) {
			hql.append(" AND shopprice BETWEEN 400.00 and 499.99");
		} else if (pricerange.equals("800p")) {
			hql.append(" AND shopprice>800.00");
		}
		if(!searchValue.equals("all")){
			hql.append(" AND pname LIKE '%"+searchValue+"%' OR pdescription LIKE '%"+searchValue+"%'");
		}
		try {
			List<Long> list = this.getHibernateTemplate().find(hql.toString(), c);
			if (list != null && list.size() > 0) {
				result = list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据一级分类id多条件带分页查询商品
	 * 
	 * @param begin
	 * @param limit
	 * @param categoryid
	 * @param sort
	 * @param quality
	 * @param pricerange
	 * @param searchValue
	 * @return
	 */
	public List<Product> findProductByCidMutipleConditionWithPagination(int begin, int limit, String categoryid,
			String sort, String quality, String pricerange,String searchValue) {
		List<Product> results = null;
		StringBuilder hql = new StringBuilder();
		ProductCategory c = this.findProductCategoryByCategoryId(Integer.parseInt(categoryid));
		hql.append("FROM Product WHERE secondCategory.category=?");
		if (quality.equals("brandnew")) {
			hql.append(" AND qualitychoice='全新'");
		} else if (quality.equals("99new")) {
			hql.append(" AND qualitychoice='99新'");
		} else if (quality.equals("98new")) {
			hql.append(" AND qualitychoice='98新'");
		} else if (quality.equals("95new")) {
			hql.append(" AND qualitychoice='95新'");
		} else if (quality.equals("90new")) {
			hql.append(" AND qualitychoice='90新'");
		} else if (quality.equals("80new")) {
			hql.append(" AND qualitychoice='80新及以下'");
		}
		if (pricerange.equals("99p")) {
			hql.append(" AND shopprice BETWEEN 0.00 AND 99.99");
		} else if (pricerange.equals("199p")) {
			hql.append(" AND shopprice BETWEEN 100.00 AND 199.99");
		} else if (pricerange.equals("299p")) {
			hql.append(" AND shopprice BETWEEN 200.00 AND 299.99");
		} else if (pricerange.equals("499p")) {
			hql.append(" AND shopprice BETWEEN 400.00 AND 499.99");
		} else if (pricerange.equals("800p")) {
			hql.append(" AND shopprice>800.00");
		}
		if (sort.equals("hot")) {
			hql.append(" ORDER BY pclickednum DESC");
		} else if (sort.equals("hightolow")) {
			hql.append(" ORDER BY shopprice DESC");
		} else if (sort.equals("lowtohigh")) {
			hql.append(" ORDER BY shopprice");
		}
		if(!searchValue.equals("all")){
			hql.append(" AND pname LIKE '%"+searchValue+"%' OR pdescription LIKE '%"+searchValue+"%'");
		}
		try {
			List<Product> list = this.getHibernateTemplate()
					.execute(new PageHibernateCallback<Product>(hql.toString(), new Object[] { c }, begin, limit));
			if (list != null && list.size() > 0) {
				results = list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据二级分类id查询商品个数
	 * 
	 * @param secondid
	 * @param sort
	 * @param quality
	 * @param pricerange
	 * @param searchValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountProductByCsidMutipleCondition(String secondid, String sort, String quality, String pricerange
			,String searchValue) {
		int results = 0;
		StringBuilder hql = new StringBuilder();
		ProductSecondCategory cs = this.findProductSecondCategoryBySecondid(Integer.parseInt(secondid));
		hql.append("SELECT COUNT(*) FROM Product WHERE secondCategory=?");
		if (quality.equals("brandnew")) {
			hql.append(" AND qualitychoice='全新'");
		} else if (quality.equals("99new")) {
			hql.append(" AND qualitychoice='99新'");
		} else if (quality.equals("98new")) {
			hql.append(" AND qualitychoice='98新'");
		} else if (quality.equals("95new")) {
			hql.append(" AND qualitychoice='95新'");
		} else if (quality.equals("90new")) {
			hql.append(" AND qualitychoice='90新'");
		} else if (quality.equals("80new")) {
			hql.append(" AND qualitychoice='80新及以下'");
		}
		if (pricerange.equals("99p")) {
			hql.append(" AND shopprice BETWEEN 0.00 AND 99.99");
		} else if (pricerange.equals("199p")) {
			hql.append(" AND shopprice BETWEEN 100.00 AND 199.99");
		} else if (pricerange.equals("299p")) {
			hql.append(" AND shopprice BETWEEN 200.00 AND 299.99");
		} else if (pricerange.equals("499p")) {
			hql.append(" AND shopprice BETWEEN 400.00 AND 499.99");
		} else if (pricerange.equals("800p")) {
			hql.append(" AND shopprice>800.00");
		}
		if(!searchValue.equals("all")){
			hql.append(" AND pname LIKE '%"+searchValue+"%' OR pdescription LIKE '%"+searchValue+"%'");
		}
		try {
			List<Long> list = this.getHibernateTemplate().find(hql.toString(), cs);
			if (list != null && list.size() > 0) {
				results = list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据二级分类id查询二级分类对象
	 * 
	 * @param secondid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProductSecondCategory findProductSecondCategoryBySecondid(int secondid) {
		String hql = "FROM ProductSecondCategory WHERE pcsid=?";
		ProductSecondCategory sc = null;
		try {
			List<ProductSecondCategory> list = this.getHibernateTemplate().find(hql, secondid);
			if (list != null && list.size() > 0) {
				sc = list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return sc;
	}

	/**
	 * 根据二级分类id多条件带分页查询商品
	 * 
	 * @param begin
	 * @param limit
	 * @param secondid
	 * @param sort
	 * @param quality
	 * @param pricerange
	 * @param searchValue
	 * @return
	 */
	public List<Product> findProductByCsidMutipleConditionWithPagination(int begin, int limit, String secondid,
			String sort, String quality, String pricerange,String searchValue) {
		List<Product> results = null;
		StringBuilder hql = new StringBuilder();
		ProductSecondCategory sc = this.findProductSecondCategoryBySecondid(Integer.parseInt(secondid));
		hql.append("FROM Product WHERE secondCategory=?");
		if (quality.equals("brandnew")) {
			hql.append(" AND qualitychoice='全新'");
		} else if (quality.equals("99new")) {
			hql.append(" AND qualitychoice='99新'");
		} else if (quality.equals("98new")) {
			hql.append(" AND qualitychoice='98新'");
		} else if (quality.equals("95new")) {
			hql.append(" AND qualitychoice='95新'");
		} else if (quality.equals("90new")) {
			hql.append(" AND qualitychoice='90新'");
		} else if (quality.equals("80new")) {
			hql.append(" AND qualitychoice='80新及以下'");
		}
		if (pricerange.equals("99p")) {
			hql.append(" AND shopprice BETWEEN 0.00 AND 99.99");
		} else if (pricerange.equals("199p")) {
			hql.append(" AND shopprice BETWEEN 100.00 AND 199.99");
		} else if (pricerange.equals("299p")) {
			hql.append(" AND shopprice BETWEEN 200.00 AND 299.99");
		} else if (pricerange.equals("499p")) {
			hql.append(" AND shopprice BETWEEN 400.00 AND 499.99");
		} else if (pricerange.equals("800p")) {
			hql.append(" AND shopprice>800.00");
		}
		if (sort.equals("hot")) {
			hql.append(" ORDER BY pclickednum DESC");
		} else if (sort.equals("hightolow")) {
			hql.append(" ORDER BY shopprice DESC");
		} else if (sort.equals("lowtohigh")) {
			hql.append(" ORDER BY shopprice");
		}
		if(!searchValue.equals("all")){
			hql.append(" AND pname LIKE '%"+searchValue+"%' OR pdescription LIKE '%"+searchValue+"%'");
		}
		try {
			List<Product> list = this.getHibernateTemplate()
					.execute(new PageHibernateCallback<Product>(hql.toString(), new Object[] { sc }, begin, limit));
			if (list != null && list.size() > 0) {
				results = list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 查询两个分类的热销产品
	 * 
	 * @param cid1
	 * @param cid2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findProductByTwoCategory(int cid1, int cid2) {
		List<Product> results = null;
		try {
			List<Product> list1 = this.getHibernateTemplate()
					.find("FROM Product WHERE secondCategory.category.pcategoryid=? ORDER BY pclickednum desc", cid1);
			if (list1 != null && list1.size() > 0) {
				results = list1;
			}
			List<Product> list2 = this.getHibernateTemplate().find(
					"FROM Product WHERE secondCategory.category.pcategoryid=? ORDER BY pclickednum desc", cid2);
			if(list2!=null&list2.size()>0){
				if(results==null){
					if(list2.size()<=3){
						results=list2;
					}else{
						for(int i=0;i<3;i++){
							results=new ArrayList<>();
							results.add(list2.get(i));
						}
					}
					
				}else if(results.size()<3){
					if(results.size()==1){results.add(list2.get(0));}
					else if(results.size()==2){results.add(list2.get(0));}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据产品id查出产品对象
	 * @param pid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Product findSingleProductByPid(String pid) {
		String hql="FROM Product WHERE pid=?";
		Product result=null;
		try {
			List<Product> list=this.getHibernateTemplate().find(hql,pid);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 更新单个产品的点击量
	 * @param product
	 * @return
	 */
	public Boolean updateSingleProductClickStatus(Product product) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().update(product);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据用户对象dao查询用户地址集合
	 * @param existUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PersonalAddress> findUserAddressByTripUser(TripUser existUser) {
		String hql="FROM PersonalAddress WHERE user=?";
		List<PersonalAddress> results=null;
		try {
			List<PersonalAddress> list=this.getHibernateTemplate().find(hql,existUser);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 保存新的地址对象
	 * @param address 地址对象
	 * @return
	 */
	@Transactional
	public Boolean savePaddress(PersonalAddress address) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().save(address);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据id找出地址对象
	 * @param addressid 地址id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PersonalAddress findUserAddressById(int addressid) {
		String hql="FROM PersonalAddress WHERE paddressid=?";
		PersonalAddress result=null;
		try {
			List<PersonalAddress> list=this.getHibernateTemplate().find(hql,addressid);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 删除地址对象
	 * @param a 地址对象
	 * @return
	 */
	@Transactional
	public Boolean deletePersonalAddress(PersonalAddress a) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().delete(a);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据地址id查找地址对象
	 * @param addressid 地址id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PersonalAddress findUserAddressByAddressId(String addressid) {
		int id=Integer.parseInt(addressid);
		String hql="FROM PersonalAddress WHERE paddressid=?";
		PersonalAddress result=null;
		try {
			List<PersonalAddress> list=this.getHibernateTemplate().find(hql,id);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 根据订单id查询订单对象
	 * @param oid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OrderItem findOrderItemByItemId(String oid){
		String hql="FROM OrderItem WHERE oid=?";
		OrderItem result=null;
		try {
			List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 保存订单信息
	 * @param item 订单对象
	 * @return
	 */
	@Transactional
	public OrderItem saveOrderItem(OrderItem item) {
		OrderItem result=null;
		try {
			this.getHibernateTemplate().save(item);
			result=this.findOrderItemByItemId(item.getOid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 统计关键字商品个数
	 * @param searchValue
	 * sort,quality,pricerange
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountProductBySearchValue(String searchValue,String sort,String quality,String pricerange) {
		StringBuilder hql=new StringBuilder("SELECT COUNT(*) FROM Product WHERE pname LIKE '%"+searchValue+"%' OR pdescription LIKE '%"+searchValue+"%'");
		if (quality.equals("brandnew")) {
			hql.append(" AND qualitychoice='全新'");
		} else if (quality.equals("99new")) {
			hql.append(" AND qualitychoice='99新'");
		} else if (quality.equals("98new")) {
			hql.append(" AND qualitychoice='98新'");
		} else if (quality.equals("95new")) {
			hql.append(" AND qualitychoice='95新'");
		} else if (quality.equals("90new")) {
			hql.append(" AND qualitychoice='90新'");
		} else if (quality.equals("80new")) {
			hql.append(" AND qualitychoice='80新及以下'");
		}
		if (pricerange.equals("99p")) {
			hql.append(" AND shopprice BETWEEN 0.00 AND 99.99");
		} else if (pricerange.equals("199p")) {
			hql.append(" AND shopprice BETWEEN 100.00 AND 199.99");
		} else if (pricerange.equals("299p")) {
			hql.append(" AND shopprice BETWEEN 200.00 AND 299.99");
		} else if (pricerange.equals("499p")) {
			hql.append(" AND shopprice BETWEEN 400.00 AND 499.99");
		} else if (pricerange.equals("800p")) {
			hql.append(" AND shopprice>800.00");
		}
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql.toString());
			if(list!=null&&list.size()>0){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}


	/**
	 * 根据关键字查询产品集合
	 * @param searchValue
	 * sort,quality,pricerange
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findProductsBySearchValue(String searchValue,String sort,String quality,String pricerange
			,int begin,int limit) {
		StringBuilder hql=new StringBuilder("FROM Product WHERE pname LIKE '%"+searchValue+"%' OR pdescription LIKE '%"+searchValue+"%'");
		if (quality.equals("brandnew")) {
			hql.append(" AND qualitychoice='全新'");
		} else if (quality.equals("99new")) {
			hql.append(" AND qualitychoice='99新'");
		} else if (quality.equals("98new")) {
			hql.append(" AND qualitychoice='98新'");
		} else if (quality.equals("95new")) {
			hql.append(" AND qualitychoice='95新'");
		} else if (quality.equals("90new")) {
			hql.append(" AND qualitychoice='90新'");
		} else if (quality.equals("80new")) {
			hql.append(" AND qualitychoice='80新及以下'");
		}
		if (pricerange.equals("99p")) {
			hql.append(" AND shopprice BETWEEN 0.00 AND 99.99");
		} else if (pricerange.equals("199p")) {
			hql.append(" AND shopprice BETWEEN 100.00 AND 199.99");
		} else if (pricerange.equals("299p")) {
			hql.append(" AND shopprice BETWEEN 200.00 AND 299.99");
		} else if (pricerange.equals("499p")) {
			hql.append(" AND shopprice BETWEEN 400.00 AND 499.99");
		} else if (pricerange.equals("800p")) {
			hql.append(" AND shopprice>800.00");
		}
		if (sort.equals("hot")) {
			hql.append(" ORDER BY pclickednum DESC");
		} else if (sort.equals("hightolow")) {
			hql.append(" ORDER BY shopprice DESC");
		} else if (sort.equals("lowtohigh")) {
			hql.append(" ORDER BY shopprice");
		}
		List<Product> results=null;
		try {
			List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql.toString(),null, begin, limit));
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据一级分类查出相关产品
	 * @param category 一级分类对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findProductByCategory(ProductCategory category) {
		String hql="FROM Product WHERE secondCategory.category=?";
		List<Product> results=null;
		try {
			List<Product> list=this.getHibernateTemplate().find(hql,category);
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 随机查找出热门商品
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAllHotProducts() {
		String hql="FROM Product WHERE pishot=0 ORDER BY NEWID()";
		List<Product> results=new ArrayList<>();
		try {
			List<Product> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				for(Product p:list){
					p.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(p.getPcontent()));
				}
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 随机查找所有产品
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAllRandomProduct() {
		String hql="FROM Product ORDER BY NEWID()";
		List<Product> results=new ArrayList<>();
		try {
			List<Product> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&!list.isEmpty()){
				for(Product p:list){
					p.setCoverImage(DomUtil.getSingleImageFromHtmlDocument(p.getPcontent()));
				}
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据一级分类查找产品
	 * @param category
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findProductsByCategory(ProductCategory category) {
		String hql="FROM Product WHERE secondCategory.category=? ORDER BY NEWID()";
		List<Product> results=new ArrayList<>();
		try {
			List<Product> list=this.getHibernateTemplate().find(hql,category);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据二级分类随机查找产品
	 * @param secondCategory
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findProductsBySecCategory(ProductSecondCategory secondCategory) {
		String hql="FROM Product WHERE secondCategory=? ORDER BY NEWID()";
		List<Product> results=new ArrayList<>();
		try {
			List<Product> list=this.getHibernateTemplate().find(hql,secondCategory);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public UserCollections findCollectionsByUserAndProduct(TripUser existUser, Product p) {
		String hql="FROM UserCollections WHERE product=? AND user=?";
		UserCollections result=null;
		try {
			List<UserCollections> list=this.getHibernateTemplate().find(hql,p,existUser);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 保存收藏对象
	 * @param collection
	 * @return
	 */
	public Boolean saveUserCollection(UserCollections collection) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().save(collection);
			result=true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据用户对象查出所有订单项
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderItem> findOrdersByUser(TripUser user) {
		String hql="FROM OrderItem WHERE user=? ORDER BY ostate DESC";
		List<OrderItem> results=new ArrayList<>();
		try {
			List<OrderItem> list=this.getHibernateTemplate().find(hql,user);
			if(list!=null&&!list.isEmpty()){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

}
