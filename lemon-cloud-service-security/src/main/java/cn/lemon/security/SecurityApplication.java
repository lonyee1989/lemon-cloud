package cn.lemon.security;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by lonyee on 2017/4/6.
 */
@EnableDiscoveryClient
@SpringBootApplication
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
