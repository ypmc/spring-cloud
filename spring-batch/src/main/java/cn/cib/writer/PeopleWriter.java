package cn.cib.writer;

import cn.cib.entity.write.People;
import cn.cib.repository.write.PeopleCrudRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author clyde lou
 */
@Component
public class PeopleWriter {
    @Autowired
    @Qualifier("w_ds")
    public DataSource w_dataSource;

    @Autowired
    private PeopleCrudRepository peopleCrudRepository;

    @Bean
    @Qualifier("jdbcBatchItemWriter")
    public JdbcBatchItemWriter<People> jdbcBatchItemWriter() {
        JdbcBatchItemWriter<People> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("insert into people (person_id, first_name, last_name) VALUES (uuid(), :firstName, :lastName)");
        writer.setDataSource(w_dataSource);
        return writer;
    }

    @Bean
    @Qualifier("repositoryItemWriter")
    public RepositoryItemWriter<People> repositoryItemWriter() {
        RepositoryItemWriter<People> peopleRepositoryItemWriter = new RepositoryItemWriter<>();
        peopleRepositoryItemWriter.setRepository(peopleCrudRepository);
        peopleRepositoryItemWriter.setMethodName("save");
        return peopleRepositoryItemWriter;
    }


}
