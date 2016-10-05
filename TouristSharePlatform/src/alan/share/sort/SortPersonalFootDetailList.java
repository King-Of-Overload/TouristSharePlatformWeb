package alan.share.sort;

import java.util.Comparator;

import alan.share.utilbean.PersonalFootDetail;

public class SortPersonalFootDetailList implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		PersonalFootDetail bean1=(PersonalFootDetail)o1;
		PersonalFootDetail bean2=(PersonalFootDetail)o2;
		int flag=bean1.getMonthAndDay().compareTo(bean2.getMonthAndDay());
		return flag;
	}

}
