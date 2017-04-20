package cn.lemon.cloud.server;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by lonyee on 2017/4/6.
 */
@EnableEurekaServer
@EnableConfigServer
@SpringBootApplication
public class ServerApplication {

    /**
     * 启动项目EurekaServer
     */
    public static void main(String[] args) {
        SpringApplicationBuilder springApplication = new SpringApplicationBuilder(ServerApplication.class);
        springApplication.web(true);
        springApplication.run(args);
    }
}
