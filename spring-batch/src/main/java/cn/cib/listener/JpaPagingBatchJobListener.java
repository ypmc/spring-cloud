package cn.cib.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JpaPagingBatchJobListener extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("JpaPagingBatch job finished");
        } else {
            log.info("JpaPagingBatch job " + jobExecution.getStatus().name());
        }
    }
}
