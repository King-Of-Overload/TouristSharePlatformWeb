package alan.share.photo.model;

import java.io.Serializable;
/**
 * 相册中的照片
 * @author Alan
 *
 */
public class UserPhotos implements Serializable {
	private String photoid;//照片id
	private String photourl;//照片url
	private UserAlbums albums;//多对一，相册对象
	public String getPhotoid() {
		return photoid;
	}
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public UserAlbums getAlbums() {
		return albums;
	}
	public void setAlbums(UserAlbums albums) {
		this.albums = albums;
	}
	public UserPhotos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
