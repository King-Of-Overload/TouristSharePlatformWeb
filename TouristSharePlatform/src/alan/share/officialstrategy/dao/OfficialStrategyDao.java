package alan.share.officialstrategy.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import alan.share.officialstrategy.model.City;
import alan.share.officialstrategy.model.OfficialStrategy;
import alan.share.officialstrategy.model.Provinces;
/**
 * 官方攻略持久层
 * @author Alan
 *
 */
public class OfficialStrategyDao extends HibernateDaoSupport{
/**
 * 查询所有官方攻略
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<OfficialStrategy> queryAllOfficialStrategy() {
		String hql="from OfficialStrategy order by clickednum";
		try{
			List<OfficialStrategy> list=this.getHibernateTemplate().find(hql);
			if(list!=null){
				return list;
			}else{
				return null;
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
/**
 * 查询与省份有关的官方攻略
 * @param provinceid
 * @return
 */
  @SuppressWarnings("unchecked")
public List<OfficialStrategy> queryStrategyByPid(int provinceid) {
	  String provinceHQL="from Provinces where provinceid=?";
	  String hql="from OfficialStrategy where provinces=?";
	  try {
		 Provinces province=null; 
		List<Provinces> p=this.getHibernateTemplate().find(provinceHQL,provinceid);
		if(p!=null){
			province=p.get(0);
		}
		List<OfficialStrategy> list=this.getHibernateTemplate().find(hql,province);
		if(list!=null){
			return list;
		}else {
			return null;
		}
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}
/**
 * 查询与城市相关的攻略
 * @param cityid
 * @return
 */
  @SuppressWarnings("unchecked")
public List<OfficialStrategy> queryStrategyByCid(int cityid) {
	  String hql="from OfficialStrategy where cityid=?";
	  try {
		List<OfficialStrategy> list=this.getHibernateTemplate().find(hql,cityid);
		if(list!=null){
			return list;
		}else{
			return null;
		}
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}
  /**
   * 查询城市的信息
   * @return
   */
  @SuppressWarnings("unchecked")
public City getCity(String cityName){
	  String hql="FROM City WHERE cityname=?";
	  City result=null;
	  try{
		  List<City> cityList= this.getHibernateTemplate().find(hql,cityName);
		  if(cityList!=null&&cityList.size()>0){
			 result=cityList.get(0); 
		  }
	  }catch(Exception e){
		e.printStackTrace();  
	  }
	  return result;
  }

  /**
   * 通过关键字查找官方攻略
   * @param searchKey
   * @return
   */
@SuppressWarnings("unchecked")
public List<OfficialStrategy> findOStrategyBySearchValue(String searchKey) {
	String hql="FROM OfficialStrategy WHERE ostrategyname LIKE '%"+searchKey+"%' OR ostrategybref LIKE '%"+searchKey+"%' "
			+ "OR provinces.provincename LIKE '%"+searchKey+"%'";
	List<OfficialStrategy> results=null;
	try {
		List<OfficialStrategy> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			results=list;
		}
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	return results;
}

/**
 * 官方攻略根据点击量降序
 * @return
 */
@SuppressWarnings("unchecked")
public List<OfficialStrategy> findAllOStrategyDesc() {
	String hql="FROM OfficialStrategy ORDER BY clickednum DESC";
	List<OfficialStrategy> results=null;
	try {
		List<OfficialStrategy> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			results=list;
		}
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	return results;
}
	
	
}
