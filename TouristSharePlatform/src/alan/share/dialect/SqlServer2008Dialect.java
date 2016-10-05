package alan.share.dialect;

import org.hibernate.dialect.SQLServerDialect;

public class SqlServer2008Dialect extends SQLServerDialect {
	
	public SqlServer2008Dialect() {
		registerHibernateType(1, "string");  
        registerHibernateType(-9, "string");  
        registerHibernateType(-16, "string");  
        registerHibernateType(3, "double");  	
	}
}
