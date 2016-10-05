package alan.share.utils;

import java.io.Serializable;
import java.util.List;

public class ViewOnRoadBean implements Serializable {
	private String cityName;
	private List<SingleViewOnRoad> list;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<SingleViewOnRoad> getList() {
		return list;
	}
	public void setList(List<SingleViewOnRoad> list) {
		this.list = list;
	}
	public ViewOnRoadBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
