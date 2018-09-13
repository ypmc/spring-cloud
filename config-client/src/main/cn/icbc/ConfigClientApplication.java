package main.cn.icbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import main.cn.icbc.bean.User;
import main.cn.icbc.service.FindService;


@SpringBootApplication
@ComponentScan(basePackages = {"main.cn.icbc.controller", "main.cn.icbc.task","main.cn.icbc.service"})
@EnableAutoConfiguration
@RefreshScope
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrix
public class ConfigClientApplication {

    @Value("${username}")
    String username;

    @Autowired
    private FindService service;

    @RequestMapping("/")
    public String index() {
        return "hello config client";
    }

    @RequestMapping("/main")
    String hello() {
        return "Hello world " + username + "!";
    }

    @GetMapping("/find/{name}")
    String find(@PathVariable("name") String name) {
        return "find " + service.helloServer(name);
    }

    @PostMapping("/f")
    String findU(@RequestBody User user) {
        return "find " + service.helloUser(user);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}