package alan.share.officialstrategy.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
public class Provinces implements Serializable{
	private int provinceid;//主键，省份id
	private String provincename;//省份名
	private String provincekeys;//省份缩写
	
	private Set<City> cities=new HashSet<City>();//省份中包含多个城市

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getProvincekeys() {
		return provincekeys;
	}

	public void setProvincekeys(String provincekeys) {
		this.provincekeys = provincekeys;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	public Provinces() {
		super();
	}
	
	
}
