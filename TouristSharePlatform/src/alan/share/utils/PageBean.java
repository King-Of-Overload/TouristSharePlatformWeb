package alan.share.utils;

import java.io.Serializable;
import java.util.List;
/**
 * 分页通用pageBean映射
 * @author Alan
 *
 */
public class PageBean<T> implements Serializable{
	private int page;//当前页数
	private int totalCount;//总记录数
	private int totalPage;//总页数
	private int limit;//每页显示的记录数
	private List<T> list;//集合数据
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
