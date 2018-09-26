package cn.cib.job;

/**
 * @author clyde lou
 */

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceMonitor implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("sharding context is {}", shardingContext);
    }
}