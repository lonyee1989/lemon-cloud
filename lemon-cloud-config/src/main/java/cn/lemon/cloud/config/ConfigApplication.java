package cn.lemon.cloud.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * Created by lonyee on 2017/4/7.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {
    /**
     * 启动ConfigServer
     */
    public static void main(String[] args) {
        SpringApplicationBuilder springApplication = new SpringApplicationBuilder(ConfigApplication.class);
        springApplication.web(true);
        springApplication.run(args);
    }
}
