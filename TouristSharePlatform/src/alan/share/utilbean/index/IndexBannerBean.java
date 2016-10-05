package alan.share.utilbean.index;

import java.io.Serializable;

public class IndexBannerBean implements Serializable {
	private String id;
	private String coverImage;
	private String name;
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private String type;//strategy album skillacademy
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getP3() {
		return p3;
	}
	public void setP3(String p3) {
		this.p3 = p3;
	}
	public String getP4() {
		return p4;
	}
	public void setP4(String p4) {
		this.p4 = p4;
	}
	public IndexBannerBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
