package alan.share.skillacademy.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 热门标签相关话题
 * @author Alan
 *
 */
public class SkillAcademySecondTag implements Serializable {
	private int skillsecondid;
	private String skillsecondname;
	private int skillsecondclickednum;
	private SkillAcademyHotTag hotTag;//多对一对象
	private Set<SkillAcademy> academies=new HashSet<>();//多对多
	
	public Set<SkillAcademy> getAcademies() {
		return academies;
	}
	public void setAcademies(Set<SkillAcademy> academies) {
		this.academies = academies;
	}
	public int getSkillsecondid() {
		return skillsecondid;
	}
	public void setSkillsecondid(int skillsecondid) {
		this.skillsecondid = skillsecondid;
	}
	public String getSkillsecondname() {
		return skillsecondname;
	}
	public void setSkillsecondname(String skillsecondname) {
		this.skillsecondname = skillsecondname;
	}
	public int getSkillsecondclickednum() {
		return skillsecondclickednum;
	}
	public void setSkillsecondclickednum(int skillsecondclickednum) {
		this.skillsecondclickednum = skillsecondclickednum;
	}
	public SkillAcademyHotTag getHotTag() {
		return hotTag;
	}
	public void setHotTag(SkillAcademyHotTag hotTag) {
		this.hotTag = hotTag;
	}
	public SkillAcademySecondTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
