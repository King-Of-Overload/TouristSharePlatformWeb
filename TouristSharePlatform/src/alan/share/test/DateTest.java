package alan.share.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.interceptor.annotations.Before;

import alan.share.userstrategy.dao.UserStrategyDao;
import alan.share.userstrategy.model.UserStrategy;

public class DateTest extends BaseDAOTestCase{
	private UserStrategyDao strategyDao;
	
	@Autowired
	public void setStrategyDao(UserStrategyDao strategyDao) {
		this.strategyDao = strategyDao;
	}
	@Before
	public void init(){
		
	}

	@Test
	public void testDateFormat(){
		UserStrategy s=strategyDao.findStrategyByStrategyId("21d9af33c14c8ff203a45340903b904d");
		Calendar c=Calendar.getInstance();
		c.setTime(s.getUstrategydate());
		DateFormat f=new SimpleDateFormat("yyyy");
		System.out.println(f.format(c.getTime()));
		f=new SimpleDateFormat("MM-dd");
		System.out.println(f.format(c.getTime()));
	}

}
