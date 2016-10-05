package alan.share.utils;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
/**
 * hibernate分页模块重写
 * @author Alan
 *
 * @param <T>
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {
	private String hql;
	private Object[] params;//对象集合
	private int startIndex;//起始位置
	private int pageSize;//页数
	
	public PageHibernateCallback(String hql,Object[] params,int startIndex,int pageSize){
		this.hql=hql;
		this.params=params;
		this.startIndex=startIndex;
		this.pageSize=pageSize;
	}

	
	public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
		Query query=session.createQuery(hql);//执行hql语句
		//实际参数
		if(params!=null){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		//分页
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}

}
