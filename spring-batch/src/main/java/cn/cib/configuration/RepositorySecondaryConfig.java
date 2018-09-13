package cn.cib.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySecondary", transactionManagerRef = "transactionManagerSecondary", basePackages = {
        "cn.cib.repository.write"})
@Slf4j
public class RepositorySecondaryConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("w_ds")
    private DataSource w_ds;

    @Bean(destroyMethod = "", name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(destroyMethod = "", name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(w_ds)
                .properties(getVendorProperties(w_ds))
                .packages("cn.cib.entity.write")
                .persistenceUnit("secondaryPersistenceUnit").build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        //for spring boot 1.x
        //return jpaProperties.getHibernateProperties(dataSource);

        //for spring boot 2.x
        log.info("dataSource is {}", dataSource);
        return jpaProperties.getProperties();
    }

    @Bean(destroyMethod = "", name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }

}