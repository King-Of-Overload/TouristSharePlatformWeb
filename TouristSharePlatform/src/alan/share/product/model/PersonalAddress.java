package alan.share.product.model;

import java.io.Serializable;

import alan.share.user.model.TripUser;
/**
 * 个人地址pojo映射
 * @author Alan
 *
 */
public class PersonalAddress implements Serializable {
	private int paddressid;
	private String paddressname;
	private String paddress;
	private TripUser user;
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPaddressid() {
		return paddressid;
	}
	public void setPaddressid(int paddressid) {
		this.paddressid = paddressid;
	}
	public String getPaddressname() {
		return paddressname;
	}
	public void setPaddressname(String paddressname) {
		this.paddressname = paddressname;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	public PersonalAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
