package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableHystrixDashboard
public class HystrixDashboardApplication {
	private static final Logger log = LoggerFactory.getLogger(HystrixDashboardApplication.class);
    public static void main(String[] args) {
    	log.debug("cib");
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
