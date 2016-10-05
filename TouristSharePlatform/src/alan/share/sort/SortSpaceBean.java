package alan.share.sort;

import java.util.Comparator;

import alan.share.utils.SpaceBean;

/**
 * 个人空间动态排序因子
 * @author Alan
 *
 */
public class SortSpaceBean implements Comparator{
	
	@Override
	public int compare(Object o1, Object o2) {
		SpaceBean bean1=(SpaceBean)o1;
		SpaceBean bean2=(SpaceBean)o2;
		int flag=bean1.getTime().compareTo(bean2.getTime());
		return flag;
	}

}
