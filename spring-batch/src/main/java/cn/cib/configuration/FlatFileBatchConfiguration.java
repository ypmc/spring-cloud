package cn.cib.configuration;

import cn.cib.entity.read.User;
import cn.cib.entity.write.People;
import cn.cib.listener.JdbcBatchItemWriterJobListener;
import cn.cib.listener.RepositoryItemWriterJobListener;
import cn.cib.processor.UserPeopleItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@Import({RepositoryPrimaryConfig.class, RepositorySecondaryConfig.class})
public class FlatFileBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    @Qualifier("w_ds")
    public DataSource w_dataSource;

    @Autowired
    private FlatFileItemReader flatFileItemReader;

    @Autowired
    private UserPeopleItemProcessor userPeopleItemProcessor;

    @Autowired
    private JdbcBatchItemWriter jdbcBatchItemWriter;

    @Autowired
    private RepositoryItemWriter repositoryItemWriter;

    @Bean
    public Job jobUseFlatFileWithRepositoryItemWriter(RepositoryItemWriterJobListener listener) {
        return jobBuilderFactory
                .get("jobUseFlatFileWithRepositoryItemWriter")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(repositoryItemWriterStep())
                .end()
                .build();
    }

    @Bean
    public Job jobUseFlatFileWithJdbcBatchItemWriter(JdbcBatchItemWriterJobListener listener) {
        return jobBuilderFactory.get("jobUseFlatFileWithJdbcBatchItemWriter")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(jdbcBatchItemWriterStep())
                .end()
                .build();
    }

    @Bean
    public Step repositoryItemWriterStep() {
        return stepBuilderFactory
                .get("repositoryItemWriterStep")
                .<User, People>chunk(10)
                .reader(flatFileItemReader)
                .processor(userPeopleItemProcessor)
                .writer(repositoryItemWriter)
                .build();
    }

    @Bean
    public Step jdbcBatchItemWriterStep() {
        return stepBuilderFactory
                .get("jdbcBatchItemWriterStep")
                .<User, People>chunk(10)
                .reader(flatFileItemReader)
                .processor(userPeopleItemProcessor)
                .writer(jdbcBatchItemWriter)
                .build();
    }

}
