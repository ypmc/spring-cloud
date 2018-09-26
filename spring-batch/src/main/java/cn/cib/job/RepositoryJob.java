package cn.cib.job;

/**
 * @author clyde lou
 */

import cn.cib.listener.CommonJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Step stepWithRepositoryReaderAndWriter;

    @Autowired
    private Step stepWithRepository;

    @Bean
    public Job jobWithRepositoryStepWithParams(@Autowired CommonJobListener commonJobListener) {
        return jobBuilderFactory.get("jobWithRepositoryStepWithParams")
                .incrementer(new RunIdIncrementer())
                .listener(commonJobListener)
                .flow(stepWithRepositoryReaderAndWriter)
                .end()
                .build();
    }

    @Bean
    public Job jobWithRepositoryStep(@Autowired CommonJobListener commonJobListener) {
        return jobBuilderFactory.get("jobWithRepositoryStep")
                .incrementer(new RunIdIncrementer())
                .listener(commonJobListener)
                .flow(stepWithRepository)
                .end()
                .build();
    }
}
