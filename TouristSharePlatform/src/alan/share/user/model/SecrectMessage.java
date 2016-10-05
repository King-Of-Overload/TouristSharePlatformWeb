package alan.share.user.model;

import java.io.Serializable;
import java.util.Date;

public class SecrectMessage implements Serializable {
	private String smessageid;
	private String message;
	private Date smessagetime;
	private int isread;
	private TripUser sender;
	private TripUser receiver;
	public String getSmessageid() {
		return smessageid;
	}
	public void setSmessageid(String smessageid) {
		this.smessageid = smessageid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSmessagetime() {
		return smessagetime;
	}
	public void setSmessagetime(Date smessagetime) {
		this.smessagetime = smessagetime;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public TripUser getSender() {
		return sender;
	}
	public void setSender(TripUser sender) {
		this.sender = sender;
	}
	public TripUser getReceiver() {
		return receiver;
	}
	public void setReceiver(TripUser receiver) {
		this.receiver = receiver;
	}
	public SecrectMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
