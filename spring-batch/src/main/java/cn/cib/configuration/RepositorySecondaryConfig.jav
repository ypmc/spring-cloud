package cn.cib.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySecondary", transactionManagerRef = "transactionManagerSecondary", basePackages = {
        "cn.cib.repository.write"})
public class RepositorySecondaryConfig {

    @Autowired
    @Qualifier("w_ds")
    private  DataSource w_ds;

    @Bean(destroyMethod = "", name = "entityManagerSecondary")
    public  EntityManager entityManager() {
        return entityManagerFactorySecondary().getObject().createEntityManager();
    }

    @Bean(destroyMethod = "", name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(w_ds);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(HibernatePropertiesBuilder.hibernateProperties());
        factoryBean.setPackagesToScan("cn.cib.repository.write","cn.cib.entity.write");
        factoryBean.setPersistenceUnitName("write");
        return factoryBean;
    }

    @Bean(destroyMethod = "", name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary() {
        return new JpaTransactionManager(entityManagerFactorySecondary().getObject());
    }

}