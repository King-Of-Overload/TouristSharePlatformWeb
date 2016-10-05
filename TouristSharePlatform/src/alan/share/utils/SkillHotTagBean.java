package alan.share.utils;

import java.io.Serializable;
/**
 * 热门标签pojo映射
 * @author Alan
 *
 */
public class SkillHotTagBean implements Serializable {
	private String randomClassName;
	private String tagName;
	private int hotTagId;
	
	public int getHotTagId() {
		return hotTagId;
	}
	public void setHotTagId(int hotTagId) {
		this.hotTagId = hotTagId;
	}
	public String getRandomClassName() {
		return randomClassName;
	}
	public void setRandomClassName(String randomClassName) {
		this.randomClassName = randomClassName;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public SkillHotTagBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
