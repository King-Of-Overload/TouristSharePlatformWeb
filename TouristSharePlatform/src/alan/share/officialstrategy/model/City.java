package alan.share.officialstrategy.model;
/**
 * 城市pojo对象
 */
import java.io.Serializable;

public class City implements Serializable{
	private int cityid;//城市id，自增，主键
	private String cityname;//城市名
	private String citykey;//城市缩写
	private Provinces province;//该城市所属省份,对应字段provinceid(int)
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCitykey() {
		return citykey;
	}
	public void setCitykey(String citykey) {
		this.citykey = citykey;
	}
	public Provinces getProvince() {
		return province;
	}
	public void setProvince(Provinces province) {
		this.province = province;
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
