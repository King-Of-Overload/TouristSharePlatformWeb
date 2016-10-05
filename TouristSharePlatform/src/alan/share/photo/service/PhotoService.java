package alan.share.photo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import alan.share.officialstrategy.dao.OfficialStrategyDao;
import alan.share.officialstrategy.model.City;
import alan.share.photo.dao.PhotoDao;
import alan.share.photo.model.AlbumTags;
import alan.share.photo.model.UserAlbums;
import alan.share.photo.model.UserPhotos;
import alan.share.skillacademy.model.SkillAcademy;
import alan.share.user.dao.TripUserDao;
import alan.share.user.model.TripUser;
import alan.share.utils.PageBean;
import alan.share.utils.StringUtil;

public class PhotoService {
	private PhotoDao photoDao;
	private OfficialStrategyDao officialStrategyDao;
	private TripUserDao tripUserDao;
	

	public TripUserDao getTripUserDao() {
		return tripUserDao;
	}

	public void setTripUserDao(TripUserDao tripUserDao) {
		this.tripUserDao = tripUserDao;
	}

	public OfficialStrategyDao getOfficialStrategyDao() {
		return officialStrategyDao;
	}

	public void setOfficialStrategyDao(OfficialStrategyDao officialStrategyDao) {
		this.officialStrategyDao = officialStrategyDao;
	}

	public PhotoDao getPhotoDao() {
		return photoDao;
	}

	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	/**
	 * 查找热门标签
	 * @return
	 */
	public List<AlbumTags> findAllHotTags() {
		return photoDao.findAllHotTags();
	}
	
	/**
	 * 查找热门标签
	 * @return
	 */
	public List<AlbumTags> findConditionAlHotTags() {
		return photoDao.findConditionAlHotTags();
	}

	/**
	 * 将相册标签list集合转换为json对象
	 * @param albumTags
	 * @return
	 */
	public JSONArray parseTagListToJSON(List<AlbumTags> albumTags) {
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<albumTags.size();i++){
			AlbumTags tag=albumTags.get(i);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("tagid", tag.getTagid());
			jsonObject.put("tagname",tag.getTagname() );
			jsonObject.put("clickednum", tag.getClickednum());
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	/**
	 * 查询用户相册
	 * @param existUser用户
	 * @return
	 */
	public List<UserAlbums> findUserAlbums(TripUser existUser) {
		return photoDao.findUserAlbums(existUser);
	}

	/**
	 * 保存用户新建相册信息
	 * @param albumName 相册名称
	 * @param albumDescription 相册说明
	 * @param cityName 城市名
	 * @param userName 用户名
	 * @param chosenStr 标签名
	 * @return
	 */
	public Boolean saveUserAlbumInfo(String albumName, String albumDescription, String cityName, String userName,
			String chosenStr) {
		Boolean result=false;
		try{
		UserAlbums userAlbum=new UserAlbums();
		userAlbum.setAlbumid(StringUtil.getMD5(albumName));//ID
		userAlbum.setAlbumname(albumName);
		userAlbum.setAlbumdescription(albumDescription);
		City cityInfo=officialStrategyDao.getCity(cityName);
		if(cityInfo!=null) userAlbum.setCity(cityInfo);
		userAlbum.setClickednum(0);
		userAlbum.setIsessence(0);
		userAlbum.setIshot(0);
		String[] tagArray=chosenStr.split(";");
		Set<AlbumTags> existTags=photoDao.queryTagsByTagNames(tagArray);
		if(existTags.size()>0) userAlbum.setTags(existTags);
		userAlbum.setTripUser(tripUserDao.findByUserName(userName));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		userAlbum.setUploadtime(format.parse(format.format(new Date())));//日期
		result=photoDao.saveUserAlbumInfo(userAlbum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将相册集合封装成json数组
	 * @param userAlbums
	 * @return
	 */
	public JSONArray makeAlbumListToJSONArray(List<UserAlbums> userAlbums) {
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=null;
		for(int i=0;i<userAlbums.size();i++){
			jsonObject=new JSONObject();
			UserAlbums album=userAlbums.get(i);
			jsonObject.put("albumid", album.getAlbumid());
			jsonObject.put("albumname", album.getAlbumname());
			jsonObject.put("albumdescription", album.getAlbumdescription());
			jsonObject.put("clickednum", album.getClickednum());
			jsonObject.put("ishot", album.getIshot());
			jsonObject.put("uploadtime", album.getUploadtime());
			jsonObject.put("isessence", album.getIsessence());
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	/**
	 * 根据相册名查找相册
	 * @param albumName相册名
	 * @return
	 */
	public UserAlbums findAlbumByAlbumName(String albumName) {
		return photoDao.findAlbumByAlbumName(albumName);
	}

	/**
	 * 保存相册内照片信息
	 * @param imageNames
	 * @param album
	 * @return
	 */
	public boolean savePhotosInAlbums(List<String> imageNames, UserAlbums album) {
		return photoDao.savePhotosInAlbums(imageNames,album);
	}

	/**
	 * 查询所有的相册
	 * @return
	 */
	public List<UserAlbums> findAllAlbums() {
		return photoDao.findAllAlbums();
	}

	/**
	 * 根据id获取照片
	 * @param albumid
	 * @return
	 */
	public List<UserPhotos> findUserPhotosByAlbumId(String albumid) {
		UserAlbums album=photoDao.findAlbumByAlbumId(albumid);
		return photoDao.findUserPhotosByAlbum(album);
	}

	/**
	 * 将相片信息封装成json数组
	 * @param photos 相片集合
	 * @return
	 */
	public JSONArray instoreJsonFormPhotoList(List<UserPhotos> photos) {
		JSONArray jsonArray=new JSONArray();
		JSONObject jObject=null;
		try {
			for(int i=0;i<photos.size();i++){
				UserPhotos photo=photos.get(i);
				jObject=new JSONObject();
				jObject.put("name", photo.getPhotourl());
				jObject.put("caption", photo.getAlbums().getAlbumname());
				jsonArray.put(jObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(jObject!=null) jObject=null;
		}
		return jsonArray;
	}

	/**
	 * 根据相册id查找相册对象
	 * @param albumId 相册id
	 * @return
	 */
	public UserAlbums findAlbumByAlbumId(String albumId) {
		return photoDao.findAlbumByAlbumId(albumId);
	}

	/**
	 * 带分页的查询相册
	 * @param page 
	 * @param existUser
	 * @return
	 */
	public PageBean<UserAlbums> findUserAlbumsByTripUserWithPagination(int page, TripUser existUser) {
		PageBean<UserAlbums> pageBean=new PageBean<>();
		pageBean.setPage(page);//设置当前页码
		int limit=3;
		pageBean.setLimit(limit);//每页记录数
		//设置总记录数
		int totalCount=0;
		totalCount=photoDao.findCountUserAlbumsByTripUser(existUser);//总记录数
		pageBean.setTotalCount(totalCount);//设置总记录数
		//总页数设置
		int totalPage=0;
		if(totalCount<=pageBean.getLimit()){
			totalPage=1;
		}else{
			if(totalCount%limit==0){
				totalPage=totalCount%limit;
			}else{
				totalPage=totalCount%limit+1;
			}
		}
		pageBean.setTotalPage(totalPage);//设置总页数
		//查询每页显示的数据集合
		int begin=(page-1)*limit;//从哪一个数据开始向下查询
		List<UserAlbums> list=photoDao.findUserAlbumsByTripUserWithPagination(existUser,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据类型筛选相册
	 * @param type  hot issense latest
	 * @return
	 */
	public List<UserAlbums> findAlbumsByType(String type) {
		return photoDao.findAlbumsByType(type);
	}

	/**
	 * 根据标签筛选相册
	 * @param tagid
	 * @return
	 */
	public List<UserAlbums> findAlbumsByTag(int tagid) {
		AlbumTags tag=photoDao.findAlbumTagsByTagId(tagid);
		List<UserAlbums> results=new ArrayList<>();
		Set<UserAlbums> set=tag.getAlbums();
		Iterator<UserAlbums> iterator=set.iterator();
		while(iterator.hasNext()){
			UserAlbums ab=iterator.next();
			results.add(ab);
		}
		return results;
	}

	/**
	 * 根据关键字搜索相册数据
	 * @param searchKey 关键字
	 * @return
	 */
	public List<UserAlbums> findUserAlbumsBySearchValue(String searchKey) {
		return photoDao.findUserAlbumsBySearchValue(searchKey);
	}

	/**
	 * 更新相册信息
	 * @param album
	 */
	public void updateUserAlbum(UserAlbums album) {
		photoDao.updateUserAlbum(album);
	}


}
