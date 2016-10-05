package alan.share.skillacademy.model;

import java.io.Serializable;
/**
 * 分类精华pojo
 * @author Alan
 *
 */
public class SkillAcademyCategory implements Serializable {
	private int skillcategoryid;
	private String skillcategoryname;
	private String skillcategorydescription;
	private String skillcategoryimage;
	public int getSkillcategoryid() {
		return skillcategoryid;
	}
	public void setSkillcategoryid(int skillcategoryid) {
		this.skillcategoryid = skillcategoryid;
	}
	public String getSkillcategoryname() {
		return skillcategoryname;
	}
	public void setSkillcategoryname(String skillcategoryname) {
		this.skillcategoryname = skillcategoryname;
	}
	public String getSkillcategorydescription() {
		return skillcategorydescription;
	}
	public void setSkillcategorydescription(String skillcategorydescription) {
		this.skillcategorydescription = skillcategorydescription;
	}
	public String getSkillcategoryimage() {
		return skillcategoryimage;
	}
	public void setSkillcategoryimage(String skillcategoryimage) {
		this.skillcategoryimage = skillcategoryimage;
	}
	public SkillAcademyCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
