package cn.cib;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author test
 */
@SpringBootApplication
@MapperScan("cn.cib.service")
public class SpringMyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMyBatisApplication.class, args);
    }
}
