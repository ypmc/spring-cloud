package cn.cib.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author clyde lou
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Configuration
@ConfigurationProperties(prefix = "scriptJob")
public class ElasticScriptJobProperties {
    private String cron;
    private int shardingTotalCount;
    private String shardingItemParameters;
    private String jobDescription;
    private String jobParameter;
    private String jobName;
    private String scriptCommandLine;

}
