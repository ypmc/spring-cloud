package cn.cib.reader;

import cn.cib.entity.read.User;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;

/**
 * @author kangkang lou
 */
@Component
public class UserReader {

    @Autowired
    @Qualifier(value = "entityManagerFactoryPrimary")
    private EntityManagerFactory emf;

    @Bean
    public FlatFileItemReader<User> flatFileItemReader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<User>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{"firstName", "lastName"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
                    {
                        setTargetType(User.class);
                    }
                });
            }
        };
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    @Qualifier("jpaPaginItemReader")
    public ItemReader<User> jpaPagingItemReader() {
        JpaPagingItemReader<User> reader = new JpaPagingItemReader<User>();
        String sqlQuery = "select * from user where id like :limit ";
        JpaNativeQueryProvider<User> queryProvider = new JpaNativeQueryProvider<User>();
        queryProvider.setSqlQuery(sqlQuery);
        queryProvider.setEntityClass(User.class);
        reader.setEntityManagerFactory(emf);
        reader.setPageSize(3);
        reader.setQueryProvider(queryProvider);
        reader.setParameterValues(Collections.<String, Object>singletonMap("limit", "%"));
        reader.setSaveState(true);
        return reader;
    }

}
