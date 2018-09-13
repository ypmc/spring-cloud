package cn.cib.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManagerPrimary", basePackages = {
        "cn.cib.repository.read"})
@Slf4j
public class RepositoryPrimaryConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("r_ds")
    private DataSource r_ds;

    @Bean(destroyMethod = "", name = "entityManagerPrimary")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder)
                .getObject()
                .createEntityManager();
    }

    @Bean(destroyMethod = "", name = "entityManagerFactoryPrimary")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(r_ds)
                .properties(getVendorProperties(r_ds))
                .packages("cn.cib.entity.read")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        //for spring boot 1.x
        //return jpaProperties.getHibernateProperties(dataSource);

        //for spring boot 2.x
        log.info("dataSource is {}", dataSource);
        return jpaProperties.getProperties();
    }

    @Bean(destroyMethod = "", name = "transactionManagerPrimary")
    @Primary
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}