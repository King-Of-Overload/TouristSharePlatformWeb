package alan.share.photo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 相册标签pojo映射
 * @author Administrator
 *
 */
public class AlbumTags implements Serializable {
	private int tagid;//标签id，主键,自增
	private String tagname;//标签名称
	private int clickednum;//点击量
	private Set<UserAlbums> albums=new HashSet<>();//多对多，相册集合
	
	public Set<UserAlbums> getAlbums() {
		return albums;
	}
	public void setAlbums(Set<UserAlbums> albums) {
		this.albums = albums;
	}
	public int getTagid() {
		return tagid;
	}
	public void setTagid(int tagid) {
		this.tagid = tagid;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public int getClickednum() {
		return clickednum;
	}
	public void setClickednum(int clickednum) {
		this.clickednum = clickednum;
	}
	public AlbumTags() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
