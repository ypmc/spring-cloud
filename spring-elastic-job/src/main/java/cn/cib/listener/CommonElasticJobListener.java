package cn.cib.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author clyde lou
 */
@Component
@Slf4j
public class CommonElasticJobListener implements ElasticJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("beforeJob {}", shardingContexts);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("afterJob {}", shardingContexts);
    }
}
