package alan.share.photo.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 页面pojo映射
 * @author Alan
 *
 */
public class DisplayUserAlbums implements Serializable {
	private String albumid;//相册id
	private String albumname;//相册标题
	private String albumdescription;//相册描述
	private int clickednum;//点击量
	private int ishot;//是否热门
	private Date uploadtime;//上传时间
	private int isessence;//是否精华
	private String imageUrl;
	public String getAlbumid() {
		return albumid;
	}
	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}
	public String getAlbumname() {
		return albumname;
	}
	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}
	public String getAlbumdescription() {
		return albumdescription;
	}
	public void setAlbumdescription(String albumdescription) {
		this.albumdescription = albumdescription;
	}
	public int getClickednum() {
		return clickednum;
	}
	public void setClickednum(int clickednum) {
		this.clickednum = clickednum;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	public int getIsessence() {
		return isessence;
	}
	public void setIsessence(int isessence) {
		this.isessence = isessence;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public DisplayUserAlbums() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
