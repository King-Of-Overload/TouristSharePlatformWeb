package alan.share.user.model;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {
	private String replyid;
	private Comments comment;
	private String replycontent;
	private Date replydate;
	private TripUser sender;
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}
	public Comments getComment() {
		return comment;
	}
	public void setComment(Comments comment) {
		this.comment = comment;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public Date getReplydate() {
		return replydate;
	}
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	public TripUser getSender() {
		return sender;
	}
	public void setSender(TripUser sender) {
		this.sender = sender;
	}
	
}
