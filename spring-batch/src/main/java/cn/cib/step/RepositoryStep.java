package cn.cib.step;

import cn.cib.entity.read.User;
import cn.cib.entity.write.People;
import cn.cib.processor.UserPeopleItemProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author clyde lou
 */
@Component
public class RepositoryStep {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private RepositoryItemReader repositoryItemReaderWithParams;
    @Autowired
    private RepositoryItemReader repositoryItemReader;

    @Autowired
    private UserPeopleItemProcessor userPeopleItemProcessor;

    @Autowired
    private RepositoryItemWriter repositoryItemWriter;

    @Bean
    public Step stepWithRepositoryReaderAndWriter() {
        return stepBuilderFactory.get("stepWithRepositoryReaderAndWriter")
                .<User, People>chunk(10)
                .reader(repositoryItemReaderWithParams)
                .processor(userPeopleItemProcessor)
                .writer(repositoryItemWriter)
                .build();
    }

    @Bean
    public Step stepWithRepository() {
        return stepBuilderFactory.get("stepWithRepository")
                .<User, People>chunk(10)
                .reader(repositoryItemReader)
                .processor(userPeopleItemProcessor)
                .writer(repositoryItemWriter)
                .build();
    }
}
