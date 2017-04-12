package cn.lemon.cloud.security;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by lonyee on 2017/4/6.
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class SecurityApplication {
    /**
     * 启动项目DiscoveryClientService
     */
    public static void main(String[] args) {
        SpringApplicationBuilder springApplication = new SpringApplicationBuilder(SecurityApplication.class);
        springApplication.web(true);
        springApplication.run(args);
    }
}
