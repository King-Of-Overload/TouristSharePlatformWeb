package alan.share.officialstrategy.model;

import java.io.Serializable;
/**
 * 官方攻略pojo
 * @author Alan
 *
 */
public class OfficialStrategy implements Serializable{
	private int ostrategyid;//主键，非自增
	private String ostrategyname;//攻略名
	private String ostrategybref;//一句话简介
	private String ostrategydescription;//详细介绍
	private String ostrategyurl;//文件名
	private String ostrategyimage;//攻略封面
	private int clickednum;//下载数
	private int cityid;//城市id
	private Provinces provinces;//省份对象
	
	
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public int getOstrategyid() {
		return ostrategyid;
	}
	public void setOstrategyid(int ostrategyid) {
		this.ostrategyid = ostrategyid;
	}
	public String getOstrategyname() {
		return ostrategyname;
	}
	public void setOstrategyname(String ostrategyname) {
		this.ostrategyname = ostrategyname;
	}
	public String getOstrategybref() {
		return ostrategybref;
	}
	public void setOstrategybref(String ostrategybref) {
		this.ostrategybref = ostrategybref;
	}
	public String getOstrategydescription() {
		return ostrategydescription;
	}
	public void setOstrategydescription(String ostrategydescription) {
		this.ostrategydescription = ostrategydescription;
	}
	public String getOstrategyurl() {
		return ostrategyurl;
	}
	public void setOstrategyurl(String ostrategyurl) {
		this.ostrategyurl = ostrategyurl;
	}
	public String getOstrategyimage() {
		return ostrategyimage;
	}
	public void setOstrategyimage(String ostrategyimage) {
		this.ostrategyimage = ostrategyimage;
	}
	public int getClickednum() {
		return clickednum;
	}
	public void setClickednum(int clickednum) {
		this.clickednum = clickednum;
	}
	public Provinces getProvinces() {
		return provinces;
	}
	public void setProvinces(Provinces provinces) {
		this.provinces = provinces;
	}
	public OfficialStrategy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
