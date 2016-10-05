package alan.share.utils;

import java.io.Serializable;
/**
 * 用户空间的相册pojo映射
 * @author Alan
 *
 */
public class UserSpacePhoto implements Serializable {
	private String albumid;
	private String photourl;//照片url
	public String getAlbumid() {
		return albumid;
	}
	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public UserSpacePhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
