package cn.cib.entity;

import lombok.*;
import org.springframework.batch.core.ExitStatus;
import org.springframework.stereotype.Component;

/**
 * @author clyde lou
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@Builder
@Component
public class JobResult {
    private long jobId;
    private String jobName;
    private ExitStatus jobExitStatus;
    private long timestamp;
}
