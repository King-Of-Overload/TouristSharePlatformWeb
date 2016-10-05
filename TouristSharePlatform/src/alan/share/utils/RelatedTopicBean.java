package alan.share.utils;

import java.io.Serializable;
/**
 * 相关话题滚动标签pojo
 * @author Alan
 *
 */
public class RelatedTopicBean implements Serializable {
	private String topicName;
	private String randomNum;
	private int topicId;
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getRandomNum() {
		return randomNum;
	}
	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public RelatedTopicBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
