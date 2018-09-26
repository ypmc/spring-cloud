package cn.cib.configuration;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * @author clyde lou
 */
public class HibernatePropertiesBuilder {

    public static Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        return hibernateProperties;
    }


}
