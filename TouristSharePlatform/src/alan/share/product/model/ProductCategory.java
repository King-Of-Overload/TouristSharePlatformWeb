package alan.share.product.model;

import java.io.Serializable;
/**
 * 商城一级分类
 * @author Alan
 *
 */
public class ProductCategory implements Serializable {
	private int pcategoryid;
	private String pcategoryname;
	public int getPcategoryid() {
		return pcategoryid;
	}
	public void setPcategoryid(int pcategoryid) {
		this.pcategoryid = pcategoryid;
	}
	public String getPcategoryname() {
		return pcategoryname;
	}
	public void setPcategoryname(String pcategoryname) {
		this.pcategoryname = pcategoryname;
	}
	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
