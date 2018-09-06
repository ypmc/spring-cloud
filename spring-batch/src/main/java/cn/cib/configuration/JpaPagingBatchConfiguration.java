package cn.cib.configuration;

import cn.cib.entity.read.User;
import cn.cib.entity.write.People;
import cn.cib.listener.JpaPagingBatchJobListener;
import cn.cib.processor.UserPeopleItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Import({RepositoryPrimaryConfig.class, RepositorySecondaryConfig.class})
public class JpaPagingBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JdbcBatchItemWriter jdbcBatchItemWriter;
    @Autowired
    private RepositoryItemWriter repositoryItemWriter;

    @Autowired
    @Qualifier("w_ds")
    public DataSource w_dataSource;

    @Autowired
    @Qualifier("jpaPagingItemReader")
    private ItemReader jpaPagingItemReader;

    @Autowired
    private UserPeopleItemProcessor userPeopleItemProcessor;


    @Bean
    public Job jobInJpaPagingWithJdbcBatchItemWriter(JpaPagingBatchJobListener listener) {
        return jobBuilderFactory
                .get("jobInJpaPagingWithJdbcBatchItemWriter")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepWithJdbcBatchItemWriter())
                .end()
                .build();
    }

    @Bean
    public Job jobInJpaPagingWithRepositoryItemWriter(JpaPagingBatchJobListener listener) {
        return jobBuilderFactory
                .get("jobInJpaPagingWithJdbcBatchItemWriter")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepWithRepositoryItemWriter())
                .end()
                .build();
    }

    @Bean
    public Step stepWithJdbcBatchItemWriter() {
        return stepBuilderFactory
                .get("stepWithJdbcBatchItemWriter")
                .<User, People>chunk(10)
                .reader(jpaPagingItemReader)
                .processor(userPeopleItemProcessor)
                .writer(jdbcBatchItemWriter)
                .build();
    }

    @Bean
    public Step stepWithRepositoryItemWriter() {
        return stepBuilderFactory
                .get("stepWithRepositoryItemWriter")
                .<User, People>chunk(10)
                .reader(jpaPagingItemReader)
                .processor(userPeopleItemProcessor)
                .writer(repositoryItemWriter)
                .build();
    }


}
