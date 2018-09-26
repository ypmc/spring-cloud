package cn.cib.service;

import cn.cib.entity.JobResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author clyde lou
 */
@Service
@Slf4j
public class JobLaunchService {
    @Autowired
    private JobLauncher jobLauncher;

    public JobResult launchJob(Job job) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("timestamp", Calendar.getInstance().getTime())
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            return JobResult.builder()
                    .jobName(job.getName())
                    .jobId(jobExecution.getJobId())
                    .jobExitStatus(jobExecution.getExitStatus())
                    .timestamp(Calendar.getInstance().getTimeInMillis())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("launch job exception ", e);
        }
    }
}
