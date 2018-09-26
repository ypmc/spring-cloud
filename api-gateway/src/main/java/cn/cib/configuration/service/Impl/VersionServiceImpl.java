package cn.cib.configuration.service.Impl;

import ch.qos.logback.core.util.TimeUtil;
import cn.cib.configuration.service.VersionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * @author clyde lou
 */
@Service
public class VersionServiceImpl implements VersionService {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @Override
    public String version() {
        return String.format("%s %s", "1.0.0", TimeUnit.NANOSECONDS);
    }

    @PostConstruct
    public void init() {
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("***service shutdown***");
        executor.shutdown();
    }
}
