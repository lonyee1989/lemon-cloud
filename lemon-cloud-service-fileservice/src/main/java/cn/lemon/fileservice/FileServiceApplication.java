package cn.lemon.fileservice;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FDFSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 图片文件处理服务
 * Created by lonyee on 2017/4/6.
 */
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class FileServiceApplication {
    static Logger logger = LoggerFactory.getLogger(FileServiceApplication.class);

    /**
     * 启动项目DiscoveryClientService
     */
    public static void main(String[] args) {
        SpringApplicationBuilder springApplication = new SpringApplicationBuilder(FileServiceApplication.class);
        springApplication.web(true);
        springApplication.listeners(new FileServiceApplicationStartup());
        springApplication.run(args);
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50 * 1024 * 1024);//上传文件大小 50M 50*1024*1024
        return resolver;
    }

    @Bean(name = "fdfsConfig")
    public FDFSConfig fdfsConfig() {
        return new FDFSConfig();
    }
}
