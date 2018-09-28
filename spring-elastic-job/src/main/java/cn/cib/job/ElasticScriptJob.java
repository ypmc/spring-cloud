package cn.cib.job;

import cn.cib.listener.CommonElasticJobListener;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author clyde lou
 */
@Component
public class ElasticScriptJob {
    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Autowired
    private LiteJobConfiguration scriptLiteJobConfiguration;

    @Autowired
    private CommonElasticJobListener commonElasticJobListener;

    @Autowired
    private JobEventConfiguration jobEventConfiguration;

    @Bean(initMethod = "init")
    public JobScheduler scriptJob() {
        return new JobScheduler(regCenter, scriptLiteJobConfiguration, jobEventConfiguration, commonElasticJobListener);
    }
}
