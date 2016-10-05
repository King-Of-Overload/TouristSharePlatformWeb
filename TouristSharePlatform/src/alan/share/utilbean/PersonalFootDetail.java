package alan.share.utilbean;

import java.io.Serializable;
/**
 * 个人足迹数据详情
 * @author Alan
 *
 */
public class PersonalFootDetail implements Serializable {
	private String pid;
	private String monthAndDay;
	private String name;
	private String description;
	private String type;//strategy album skillacademy
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getMonthAndDay() {
		return monthAndDay;
	}
	public void setMonthAndDay(String monthAndDay) {
		this.monthAndDay = monthAndDay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PersonalFootDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
