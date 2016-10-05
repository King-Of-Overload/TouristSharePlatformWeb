package alan.share.photo.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import alan.share.photo.model.AlbumTags;
import alan.share.photo.model.UserAlbums;
import alan.share.photo.model.UserPhotos;
import alan.share.user.model.TripUser;
import alan.share.utils.PageHibernateCallback;
import alan.share.utils.UUIDUtils;

public class PhotoDao extends HibernateDaoSupport{

	/**
	 * 查找所有热门标签
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AlbumTags> findAllHotTags() {
		List<AlbumTags> tagLists=new ArrayList<AlbumTags>();
		try{
			String hql="FROM AlbumTags ORDER BY clickednum desc";
			List<AlbumTags> list=this.getHibernateTemplate().find(hql);
			if(list!=null) tagLists=list; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return tagLists;
	}
	
	/**
	 * 查找所有热门标签
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AlbumTags> findConditionAlHotTags() {
		List<AlbumTags> tagLists=new ArrayList<AlbumTags>();
		try{
			String hql="FROM AlbumTags WHERE clickednum>100000 ORDER BY clickednum desc";
			List<AlbumTags> list=this.getHibernateTemplate().find(hql);
			if(list!=null) tagLists=list; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return tagLists;
	}

	/**
	 * 查找出用户拥有的相册
	 * @param existUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserAlbums> findUserAlbums(TripUser existUser) {
		String hql="FROM UserAlbums WHERE tripUser=?";
		List<UserAlbums> userAlbums=null;
		try {
			List<UserAlbums> list=this.getHibernateTemplate().find(hql,existUser);
			if(list!=null&&list.size()>0){
				userAlbums=list;
			}else{
				userAlbums=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userAlbums;
	}
	
	/**
	 * 根据提供的标签数组查询所有标签
	 * @return tagNames 标签数组
	 */
	@SuppressWarnings("unchecked")
	public Set<AlbumTags> queryTagsByTagNames(String[] tagNames){
		String hql="";
		Set<AlbumTags> albumTags=null;
		try {
			albumTags=new HashSet<>();
			for(int i=0;i<tagNames.length;i++){
				hql="FROM AlbumTags WHERE tagname=?";
				List<AlbumTags> list=this.getHibernateTemplate().find(hql,tagNames[i]);
				if(list.size()>0&&list!=null) albumTags.add(list.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return albumTags;
	}

	/**
	 * 保存新建相册信息
	 * @param userAlbum
	 * @return
	 */
	public Boolean saveUserAlbumInfo(UserAlbums userAlbum) {
		Boolean result=false;
		try {
			this.getHibernateTemplate().save(userAlbum);
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查找相册根据相册名
	 * @param albumName
	 * @return
	 */
	public UserAlbums findAlbumByAlbumName(String albumName) {
		String hql="FROM UserAlbums WHERE albumname=?";
		try {
			List<UserAlbums> list=this.getHibernateTemplate().find(hql,albumName);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存相册内照片
	 * @param imageNames
	 * @param album
	 * @return
	 */
	public boolean savePhotosInAlbums(List<String> imageNames, UserAlbums album) {
		Boolean result=false;
		UserPhotos userPhoto=null;
		try {
			for(int i=0;i<imageNames.size();i++){
				String imagePath=imageNames.get(i);
				userPhoto=new UserPhotos();
				userPhoto.setPhotoid(UUIDUtils.getUUID());
				userPhoto.setPhotourl(imagePath);
				userPhoto.setAlbums(album);
				this.getHibernateTemplate().save(userPhoto);
				result=true;
			}
		} catch (Exception e) {
			result=false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查找所有相册
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserAlbums> findAllAlbums() {
		List<UserAlbums> list=null;
		String hql="FROM UserAlbums";
		try {
			List<UserAlbums> result=this.getHibernateTemplate().find(hql);
			if(result.size()>0&&result!=null){
				list=result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据相册id查相册
	 * @param albumid
	 * @return
	 */
	public UserAlbums findAlbumByAlbumId(String albumid) {
		String hql="FROM UserAlbums WHERE albumid=?";
		try {
			List<UserAlbums> list=this.getHibernateTemplate().find(hql,albumid);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据相册查询相关照片
	 * @param album
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserPhotos> findUserPhotosByAlbum(UserAlbums album) {
		String hql="FROM UserPhotos WHERE albums=?";
		try {
			List<UserPhotos> list=this.getHibernateTemplate().find(hql,album);
			if(list.size()>0&&list!=null){
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据用户对象统计相册数量
	 * @param existUser 用户对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findCountUserAlbumsByTripUser(TripUser existUser) {
		String hql="SELECT COUNT(*) FROM UserAlbums WHERE tripUser=?";
		int result=0;
		try {
			List<Long> list=this.getHibernateTemplate().find(hql,existUser);
			if(list!=null&&list.size()>0){
				result=list.get(0).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据用户对象带分页的查询相册集合
	 * @param existUser
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<UserAlbums> findUserAlbumsByTripUserWithPagination(TripUser existUser, int begin, int limit) {
		String hql="FROM UserAlbums WHERE tripUser=? ORDER BY uploadtime DESC";
		List<UserAlbums> results=null;
		try {
			List<UserAlbums> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, new Object[]{existUser}, begin, limit));
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据类型筛选相册
	 * @param type
	 * @return hot issense latest
	 */
	@SuppressWarnings("unchecked")
	public List<UserAlbums> findAlbumsByType(String type) {
		StringBuilder hql=new StringBuilder("FROM UserAlbums WHERE 1=1");
		List<UserAlbums> results=null;
		try {
			if(("hot").equals(type)){
				hql.append(" AND ishot=0");
			}else if(("issense").equals(type)){
				hql.append(" AND isessence=0");
			}else if(("latest").equals(type)){
				hql.append(" ORDER BY uploadtime DESC");
			}
			List<UserAlbums> list=this.getHibernateTemplate().find(hql.toString());
			if(list!=null&&list.size()>0){
				results=list;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 根据tagid查询tag对象
	 * @param tagid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public AlbumTags findAlbumTagsByTagId(int tagid) {
		String hql="FROM AlbumTags WHERE tagid=?";
		AlbumTags result=null;
		try {
			List<AlbumTags> list=this.getHibernateTemplate().find(hql,tagid);
			if(list!=null&&list.size()>0){
				result=list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据搜索关键字查询相册集合
	 * @param searchKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserAlbums> findUserAlbumsBySearchValue(String searchKey) {
		String hql1="FROM UserAlbums WHERE albumname LIKE '%"+searchKey+"%' OR albumdescription LIKE '%"+searchKey+"%' "
				+ "OR tripUser.username LIKE '%"+searchKey+"%' OR city.cityname LIKE '%"+searchKey+"%'";
		List<UserAlbums> results=null;
		try {
			List<UserAlbums> list=this.getHibernateTemplate().find(hql1);
			if(list!=null&&list.size()>0){
				for(int i = list.size()-1; i >=0 ;i--){
					UserAlbums album=list.get(i);
					List<UserPhotos> photos=this.findUserPhotosByAlbum(album);
					if(null!=photos&&photos.size()>0){
						album.setCoverImage(photos.get(0).getPhotourl());
					}else{
						list.remove(i);
					}
				}
				results=list;
			}
			String hql2="FROM AlbumTags WHERE tagname LIKE '%"+searchKey+"%'";
			List<AlbumTags> list2=this.getHibernateTemplate().find(hql2);
			List<UserAlbums> albumsByTagList=new ArrayList<>();
			if(list2!=null&&list2.size()>0){
				for(int i=0;i<results.size();i++){
					for(int j=0;j<list2.size();j++){
						Set<UserAlbums> albums=list2.get(j).getAlbums();
						Iterator<UserAlbums> iterator=albums.iterator();
						while(iterator.hasNext()){
							UserAlbums a=iterator.next();
							if(!results.contains(a)){
								albumsByTagList.add(a);
							}
						}
					}
				}
				for(int i = albumsByTagList.size()-1; i >=0 ;i--){
					UserAlbums a=albumsByTagList.get(i);
					List<UserPhotos> photos=this.findUserPhotosByAlbum(a);
					if(photos!=null&&photos.size()>0){
						a.setCoverImage(photos.get(0).getPhotourl());
					}else{
						albumsByTagList.remove(i);
					}
				}
				for(UserAlbums a:albumsByTagList){
					results.add(a);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	/**
	 * 更新相册信息
	 * @param album
	 */
	public void updateUserAlbum(UserAlbums album) {
		try {
			this.getHibernateTemplate().update(album);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
