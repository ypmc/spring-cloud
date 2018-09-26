package cn.cib.config;

import cn.cib.job.ServiceMonitor;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clyde lou
 */
@Configuration
public class MonitorJobProperties {
    @Value("${monitor.cron}")
    private String cron;

    @Value("${monitor.sharding-total-count}")
    private int shardingTotalCount;

    @Value("${monitor.sharding-item-parameters}")
    private String shardingItemParameters;

    @Value("${monitor.job-description}")
    private String jobDescription;

    @Value("${monitor.job-parameter}")
    private String jobParameter;

    @Autowired
    private ServiceMonitor serviceMonitor;

    @Bean
    public LiteJobConfiguration liteJobConfiguration() {
        JobCoreConfiguration.Builder builder = JobCoreConfiguration.newBuilder(serviceMonitor.getClass().getName()
                , cron, shardingTotalCount);
        JobCoreConfiguration jobCoreConfiguration = builder
                .shardingItemParameters(shardingItemParameters)
                .description(jobDescription)
                .jobParameter(jobParameter)
                .build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, serviceMonitor.getClass().getCanonicalName());
        return LiteJobConfiguration
                .newBuilder(simpleJobConfiguration)
                .overwrite(true)
                .build();
    }

}
