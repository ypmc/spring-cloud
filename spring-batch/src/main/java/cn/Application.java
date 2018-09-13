package cn;

import cn.cib.service.JobLaunchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableBatchProcessing
@Slf4j
@ComponentScan()
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JobLaunchService jobLaunchService;

    @Autowired
    private Job jobWithRepositoryStep;

    @PostConstruct
    public void runJob(){
        log.info("run job from scheduler");
        jobLaunchService.launchJob(jobWithRepositoryStep);
    }
}
