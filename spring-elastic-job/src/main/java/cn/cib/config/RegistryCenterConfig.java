package cn.cib.config;

/**
 * @author clyde lou
 */

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RegistryCenterConfig {

    @Value("${reg-center.server-list}")
    private String serverList;
    @Value("${reg-center.namespace}")
    private String namespace;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        log.info("serverLists is {}", serverList);
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

}
