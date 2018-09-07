package cn.cib.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class CommonJobListener extends JobExecutionListenerSupport {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job [" + jobExecution.getJobId() + "] start " + DateFormatUtils.format(jobExecution.getStartTime(), DATE_TIME_FORMAT));


    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job [" + jobExecution.getJobId() + "] finish " + DateFormatUtils.format(jobExecution.getEndTime(), DATE_TIME_FORMAT));
        log.info("Job [" + jobExecution.getJobId() + "] status " + jobExecution.getStatus().name());
    }

    public static void main(String[] args) {

    }
}
