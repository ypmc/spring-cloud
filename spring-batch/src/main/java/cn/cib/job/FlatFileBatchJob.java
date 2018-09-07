package cn.cib.job;

import cn.cib.configuration.RepositoryPrimaryConfig;
import cn.cib.configuration.RepositorySecondaryConfig;
import cn.cib.listener.JdbcBatchItemWriterJobListener;
import cn.cib.listener.RepositoryItemWriterJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class FlatFileBatchJob {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Step repositoryItemWriterStep;

    @Autowired
    private Step jdbcBatchItemWriterStep;

    @Bean
    public Job jobUseFlatFileWithRepositoryItemWriter(RepositoryItemWriterJobListener listener) {
        return jobBuilderFactory
                .get("jobUseFlatFileWithRepositoryItemWriter")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(repositoryItemWriterStep)
                .end()
                .build();
    }

    @Bean
    public Job jobUseFlatFileWithJdbcBatchItemWriter(JdbcBatchItemWriterJobListener listener) {
        return jobBuilderFactory.get("jobUseFlatFileWithJdbcBatchItemWriter")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(jdbcBatchItemWriterStep)
                .end()
                .build();
    }


}
