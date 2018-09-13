package cn.cib.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManagerPrimary", basePackages = {
        "cn.cib.repository.read"})
public class RepositoryPrimaryConfig {

    @Autowired
    @Qualifier("r_ds")
    private DataSource r_ds;

    @Bean(destroyMethod = "", name = "entityManagerPrimary")
    @Primary
    public EntityManager entityManager() {
        return entityManagerFactoryPrimary().getObject().createEntityManager();
    }

    @Bean(destroyMethod = "", name = "entityManagerFactoryPrimary")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(r_ds);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(HibernatePropertiesBuilder.hibernateProperties());
        factoryBean.setPackagesToScan("cn.cib.repository.read", "cn.cib.entity.read");
        factoryBean.setPersistenceUnitName("read");
        return factoryBean;
    }

    @Bean(destroyMethod = "", name = "transactionManagerPrimary")
    @Primary
    PlatformTransactionManager transactionManagerPrimary() {
        return new JpaTransactionManager(entityManagerFactoryPrimary().getObject());
    }

}