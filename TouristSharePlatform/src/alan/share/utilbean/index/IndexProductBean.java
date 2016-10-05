package alan.share.utilbean.index;

import java.io.Serializable;
import java.util.List;

import alan.share.product.model.Product;

public class IndexProductBean implements Serializable {
	private String cName;
	private List<Product> list;
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public IndexProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
