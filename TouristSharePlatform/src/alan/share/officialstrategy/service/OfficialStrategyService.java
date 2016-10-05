package alan.share.officialstrategy.service;

import java.util.List;

import alan.share.officialstrategy.dao.OfficialStrategyDao;
import alan.share.officialstrategy.model.City;
import alan.share.officialstrategy.model.OfficialStrategy;

public class OfficialStrategyService {
	private OfficialStrategyDao officialStrategyDao;

	public OfficialStrategyDao getOfficialStrategyDao() {
		return officialStrategyDao;
	}

	public void setOfficialStrategyDao(OfficialStrategyDao officialStrategyDao) {
		this.officialStrategyDao = officialStrategyDao;
	}

	//获取所有官方攻略信息
	public List<OfficialStrategy> getAllOfficialStrategy() {
		return officialStrategyDao.queryAllOfficialStrategy();
	}

	//根据省份id查询与该省份有关的攻略
	public List<OfficialStrategy> findStrategyByPid(int provinceid) {
		return officialStrategyDao.queryStrategyByPid(provinceid);
	}
    //根据城市id查询攻略
	public List<OfficialStrategy> findStrategyByCid(int cityid) {
		return officialStrategyDao.queryStrategyByCid(cityid);
	}
	/**
	 * 查找城市
	 * @param cityName
	 * @return
	 */
	public City getCity(String cityName){
		return officialStrategyDao.getCity(cityName);
	}

	/**
	 * 通过关键字查找官方攻略
	 * @param searchKey
	 * @return
	 */
	public List<OfficialStrategy> findOStrategyBySearchValue(String searchKey) {
		return officialStrategyDao.findOStrategyBySearchValue(searchKey);
	}

	/**
	 * 查出所有官方攻略数据降序
	 * @return
	 */
	public List<OfficialStrategy> findAllOStrategyDesc() {
		return officialStrategyDao.findAllOStrategyDesc();
	}
	
	
}
