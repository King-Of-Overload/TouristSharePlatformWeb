package alan.share.utilbean.index;

import java.io.Serializable;
import java.util.List;

import alan.share.photo.model.UserAlbums;

public class IndexAlbumBean implements Serializable {
	private String name;
	private List<UserAlbums> albums;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UserAlbums> getAlbums() {
		return albums;
	}
	public void setAlbums(List<UserAlbums> albums) {
		this.albums = albums;
	}
	
}
