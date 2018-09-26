package cn.cib.step;

import cn.cib.entity.read.User;
import cn.cib.entity.write.People;
import cn.cib.processor.UserPeopleItemProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author clyde lou
 */
@Component
public class FlatFileBatchStep {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FlatFileItemReader flatFileItemReader;

    @Autowired
    private UserPeopleItemProcessor userPeopleItemProcessor;

    @Autowired
    private JdbcBatchItemWriter jdbcBatchItemWriter;

    @Autowired
    private RepositoryItemWriter repositoryItemWriter;


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
