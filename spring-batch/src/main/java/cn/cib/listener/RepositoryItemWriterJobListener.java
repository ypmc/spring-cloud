package cn.cib.listener;

import cn.cib.entity.write.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Component
@Slf4j
public class RepositoryItemWriterJobListener extends JobExecutionListenerSupport {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("w_ds")
    public DataSource w_dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate.setDataSource(w_dataSource);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("RepositoryItemWriter job finished, Time to verify the results");
            List<People> results = jdbcTemplate.query("SELECT person_id, first_name, last_name FROM people",
                    (rs, row) -> new People(rs.getString(1)
                            , rs.getString(2)
                            , rs.getString(3)
                    ));

            for (People people : results) {
                log.info("Found <" + people + "> in the database.");
            }
        }
    }
}
