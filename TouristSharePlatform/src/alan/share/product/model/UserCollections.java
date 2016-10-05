package alan.share.product.model;

import java.io.Serializable;
import java.util.Date;

import alan.share.user.model.TripUser;

public class UserCollections implements Serializable {
	private String collectid;
	private Product product;
	private Date collectdate;
	private TripUser user;
	public String getCollectid() {
		return collectid;
	}
	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getCollectdate() {
		return collectdate;
	}
	public void setCollectdate(Date collectdate) {
		this.collectdate = collectdate;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	
}
