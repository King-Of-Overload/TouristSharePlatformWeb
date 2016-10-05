package alan.share.user.model;

import java.io.Serializable;
import java.util.Date;

public class Visitor implements Serializable {
	private int visitid;
	private TripUser user;
	private Date visitdate;
	private String visitorid;
	public int getVisitid() {
		return visitid;
	}
	public void setVisitid(int visitid) {
		this.visitid = visitid;
	}
	public TripUser getUser() {
		return user;
	}
	public void setUser(TripUser user) {
		this.user = user;
	}
	public Date getVisitdate() {
		return visitdate;
	}
	public void setVisitdate(Date visitdate) {
		this.visitdate = visitdate;
	}
	public String getVisitorid() {
		return visitorid;
	}
	public void setVisitorid(String visitorid) {
		this.visitorid = visitorid;
	}
	public Visitor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
