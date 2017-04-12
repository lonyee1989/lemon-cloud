package cn.lemon.cloud.gateway;

import cn.lemon.cloud.gateway.config.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by lonyee on 2017/4/6.
 * // @SpringCloudApplication  == @SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
 */
@EnableZuulProxy
@SpringCloudApplication
@EnableHystrix
@EnableHystrixDashboard
public class GatewayApplication {
    /**
     * 启动项目服务网关Gateway
     */
    public static void main(String[] args) {
        SpringApplicationBuilder springApplication = new SpringApplicationBuilder(GatewayApplication.class);
        springApplication.web(true);
        springApplication.run(args);
    }

    //@Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
