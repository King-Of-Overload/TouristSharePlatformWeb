package alan.share.user.model;

import java.io.Serializable;

public class ClickZan implements Serializable {
	private int czid;
	private String userid;
	private String topicid;
	private String topictype;
	
	
	public String getTopictype() {
		return topictype;
	}
	public void setTopictype(String topictype) {
		this.topictype = topictype;
	}
	public int getCzid() {
		return czid;
	}
	public void setCzid(int czid) {
		this.czid = czid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}
	
}
