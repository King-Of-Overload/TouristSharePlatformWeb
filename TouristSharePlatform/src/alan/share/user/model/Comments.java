package alan.share.user.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comments implements Serializable{
	private String commentid;
	private String topicid;
	private String topictype;//类型 strategy skillacademy
	private String commentcontent;//评论内容
	private TripUser fromuser;
	private Date commentdate;
	
	private List<Reply> replies=new ArrayList<>();
	
	
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public Date getCommentdate() {
		return commentdate;
	}
	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}
	public String getTopictype() {
		return topictype;
	}
	public void setTopictype(String topictype) {
		this.topictype = topictype;
	}
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	public TripUser getFromuser() {
		return fromuser;
	}
	public void setFromuser(TripUser fromuser) {
		this.fromuser = fromuser;
	}
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
