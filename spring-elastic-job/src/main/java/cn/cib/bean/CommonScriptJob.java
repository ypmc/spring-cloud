package cn.cib.bean;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author clyde lou
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString(doNotUseGetters = true)
public class CommonScriptJob {
    private String cron;
    private int shardingTotalCount;
    private String shardingItemParameters;
    private String jobDescription;
    private String jobParameter;
    private String jobName;
    private String scriptCommandLine;
}
