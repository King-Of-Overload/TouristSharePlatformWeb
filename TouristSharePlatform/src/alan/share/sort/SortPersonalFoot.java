package alan.share.sort;

import java.util.Comparator;

import alan.share.utilbean.PersonalFoot;

public class SortPersonalFoot implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		PersonalFoot foot1=(PersonalFoot)o1;
		PersonalFoot foot2=(PersonalFoot)o2;
		int flag=foot1.getYear().compareTo(foot2.getYear());
		return flag;
	}

}
