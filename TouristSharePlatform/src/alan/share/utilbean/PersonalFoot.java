package alan.share.utilbean;

import java.io.Serializable;
/**
 * 个人足迹pojo对象
 * @author Alan
 *
 */
import java.util.List;
public class PersonalFoot implements Serializable {
	private String year;
	private List<PersonalFootDetail> list;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<PersonalFootDetail> getList() {
		return list;
	}
	public void setList(List<PersonalFootDetail> list) {
		this.list = list;
	}
	public PersonalFoot() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
