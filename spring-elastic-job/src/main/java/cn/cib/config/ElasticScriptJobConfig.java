package cn.cib.config;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clyde lou
 */
@Configuration
public class ElasticScriptJobConfig {

    @Autowired
    private ElasticScriptJobProperties elasticScriptJobProperties;

    @Bean
    public LiteJobConfiguration scriptLiteJobConfiguration() {
        JobCoreConfiguration.Builder builder = JobCoreConfiguration.newBuilder(elasticScriptJobProperties.getJobName()
                , elasticScriptJobProperties.getCron()
                , elasticScriptJobProperties.getShardingTotalCount());
        JobCoreConfiguration jobCoreConfiguration = builder
                .shardingItemParameters(elasticScriptJobProperties.getShardingItemParameters())
                .description(elasticScriptJobProperties.getJobDescription())
                .jobParameter(elasticScriptJobProperties.getJobParameter())
                .build();
        ScriptJobConfiguration scriptJobConfiguration = new ScriptJobConfiguration(jobCoreConfiguration
                , elasticScriptJobProperties.getScriptCommandLine());
        return LiteJobConfiguration
                .newBuilder(scriptJobConfiguration)
                .overwrite(true)
                .build();
    }

}
