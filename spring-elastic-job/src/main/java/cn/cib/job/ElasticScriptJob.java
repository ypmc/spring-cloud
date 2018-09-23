package cn.cib.job;

import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author kangkang lou
 */
@Component
public class ElasticScriptJob {
    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Autowired
    private LiteJobConfiguration scriptLiteJobConfiguration;

    @Bean(initMethod = "init")
    public JobScheduler scriptJob() {
        return new JobScheduler(regCenter, scriptLiteJobConfiguration);
    }
}
