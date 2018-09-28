package cn.cib.config;

import cn.cib.bean.CommonScriptJob;
import cn.cib.listener.CommonElasticJobListener;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author clyde lou
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Configuration
@ConfigurationProperties("script-job-list")
@PropertySource(name = "批量作业集合", value = "file:${config.path}/jobs.yml", factory = YamlPropertySourceFactory.class)
@Slf4j
public class ScriptJobsConfig {
    private List<CommonScriptJob> jobs;

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Autowired
    private CommonElasticJobListener commonElasticJobListener;

    @Autowired
    private JobEventConfiguration jobEventConfiguration;


    @PostConstruct
    public void registerJob() {
        log.info("jobs is {}", jobs);
        jobs.stream().forEach(job -> (new JobScheduler(regCenter
                , scriptLiteJobConfiguration(job)
                , jobEventConfiguration, commonElasticJobListener)
        ).init());
    }


    private LiteJobConfiguration scriptLiteJobConfiguration(CommonScriptJob commonScriptJob) {
        JobCoreConfiguration.Builder builder = JobCoreConfiguration.newBuilder(commonScriptJob.getJobName()
                , commonScriptJob.getCron()
                , commonScriptJob.getShardingTotalCount());
        JobCoreConfiguration jobCoreConfiguration = builder
                .shardingItemParameters(commonScriptJob.getShardingItemParameters())
                .description(commonScriptJob.getJobDescription())
                .jobParameter(commonScriptJob.getJobParameter())
                .build();
        ScriptJobConfiguration scriptJobConfiguration = new ScriptJobConfiguration(jobCoreConfiguration
                , commonScriptJob.getScriptCommandLine());
        return LiteJobConfiguration
                .newBuilder(scriptJobConfiguration)
                .overwrite(true)
                .build();
    }
}
