package alan.share.product.model;

import java.io.Serializable;
import java.util.List;
/**
 * 商城首页商品一级分类与二级分类封装类
 * @author Alan
 *
 */
public class ProductIndexCategoryBean implements Serializable {
	private String cName;
	private int cid;
	private List<ProductSecondCategory> listSecondCategory;
	
	public List<ProductSecondCategory> getListSecondCategory() {
		return listSecondCategory;
	}
	public void setListSecondCategory(List<ProductSecondCategory> listSecondCategory) {
		this.listSecondCategory = listSecondCategory;
	}
	
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public ProductIndexCategoryBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
