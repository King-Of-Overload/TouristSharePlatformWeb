package alan.share.skillacademy.model;

import java.io.Serializable;
/**
 * 技法学院热门标签POJO
 * @author Alan
 *
 */
public class SkillAcademyHotTag implements Serializable {
	private int skillhottagid;
	private String skillhottagname;
	private int clickednum;
	public int getSkillhottagid() {
		return skillhottagid;
	}
	public void setSkillhottagid(int skillhottagid) {
		this.skillhottagid = skillhottagid;
	}
	public String getSkillhottagname() {
		return skillhottagname;
	}
	public void setSkillhottagname(String skillhottagname) {
		this.skillhottagname = skillhottagname;
	}
	public int getClickednum() {
		return clickednum;
	}
	public void setClickednum(int clickednum) {
		this.clickednum = clickednum;
	}
	public SkillAcademyHotTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
