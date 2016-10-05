package alan.share.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import alan.share.user.model.TripUser;

public class OrderItem implements Serializable {
	private String oid;
	private BigDecimal oprice;
	private Date otime;
	private int ostate;
	private TripUser user;
	private Product product;
	private String paddress;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public BigDecimal getOprice() {
		return oprice;
	}
	public void setOprice(BigDecimal oprice) {
		this.oprice = oprice;
	}
	public Date getOtime() {
		return otime;
	}
	public void setOtime(Date otime) {
		this.otime = otime;
	}
	public int getOstate() {
		return ostate;
	}
	public void setOstate(int ostate) {
		this.ostate = ostate;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
