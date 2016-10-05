package alan.share.user.model;

import java.io.Serializable;

import alan.share.officialstrategy.model.City;
/**
 * 用户javaBean
 * @author Alan
 *
 */
public class TripUser implements Serializable{
	private String userid;
	private String username;
	private String userpassword;
	private String useremail;
	private int userstate;
	private String useractivecode;
	private String headerimage;//用户头像
	private String phone;
	private String sex;
	private City city;
	private String usercookievalue;
	private int ismaster;
	private String usignature;
	
	
	
	
	
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getUsignature() {
		return usignature;
	}
	public void setUsignature(String usignature) {
		this.usignature = usignature;
	}
	public int getIsmaster() {
		return ismaster;
	}
	public void setIsmaster(int ismaster) {
		this.ismaster = ismaster;
	}
	public String getUsercookievalue() {
		return usercookievalue;
	}
	public void setUsercookievalue(String usercookievalue) {
		this.usercookievalue = usercookievalue;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHeaderimage() {
		return headerimage;
	}
	public void setHeaderimage(String headerimage) {
		this.headerimage = headerimage;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public int getUserstate() {
		return userstate;
	}
	public void setUserstate(int userstate) {
		this.userstate = userstate;
	}
	public String getUseractivecode() {
		return useractivecode;
	}
	public void setUseractivecode(String useractivecode) {
		this.useractivecode = useractivecode;
	}



	public TripUser() {
		super();
	}
	
	
	
	
}
