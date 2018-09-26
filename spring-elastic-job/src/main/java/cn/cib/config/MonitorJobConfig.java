package cn.cib.config;

/**
 * @author clyde lou
 */

import cn.cib.job.ServiceMonitor;
import cn.cib.listener.CommonElasticJobListener;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitorJobConfig {

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Autowired
    private ServiceMonitor serviceMonitor;

    @Autowired
    private LiteJobConfiguration liteJobConfiguration;

    @Autowired
    private CommonElasticJobListener commonElasticJobListener;

    @Autowired
    private JobEventConfiguration jobEventConfiguration;


    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler() {
        return new SpringJobScheduler(serviceMonitor
                , regCenter
                , liteJobConfiguration
                , jobEventConfiguration
                , commonElasticJobListener);
    }
}
