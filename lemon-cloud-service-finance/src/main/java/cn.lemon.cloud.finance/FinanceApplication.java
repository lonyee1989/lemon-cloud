package cn.lemon.cloud.finance;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by lonyee on 2017/4/6.
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class FinanceApplication {
    /**
     * 启动项目DiscoveryClientService
     */
    public static void main(String[] args) {
        SpringApplicationBuilder springApplication = new SpringApplicationBuilder(FinanceApplication.class);
        springApplication.web(true);
        springApplication.run(args);
    }
}
