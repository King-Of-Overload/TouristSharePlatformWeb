package alan.share.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateFormatTest {
	
	@Test
	public void testFormat() throws ParseException{
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c=Calendar.getInstance();
		c.setTime(sim.parse("2016-07-30 21:15:05.000"));
		DateFormat f=new SimpleDateFormat("yyyy");
		System.out.println(f.format(c.getTime()));
		f=new SimpleDateFormat("MM-dd");
		System.out.println(f.format(c.getTime()));
	}

}
