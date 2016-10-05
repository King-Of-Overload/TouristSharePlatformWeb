package alan.share.user.model;

import java.io.Serializable;
/**
 * 关注Pojo映射
 * @author Alan
 *
 */
public class PayAttention implements Serializable {
	private int attentionid;
	private TripUser user;
	private String followid;
	public int getAttentionid() {
		return attentionid;
	}
	public void setAttentionid(int attentionid) {
		this.attentionid = attentionid;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	public String getFollowid() {
		return followid;
	}
	public void setFollowid(String followid) {
		this.followid = followid;
	}
	public PayAttention() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
