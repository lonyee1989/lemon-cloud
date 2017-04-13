package cn.lemon.fileservice;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FDFSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;


/**
 * 项目启动后加载FastDFS配置
 * Created by lonyee on 2017/4/13.
 */
public class FileServiceApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    static Logger logger = LoggerFactory.getLogger(FileServiceApplicationStartup.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        FDFSConfig fdfsConfig = event.getApplicationContext().getBean(FDFSConfig.class);
        try {
            ClientGlobal.init(fdfsConfig);
            logger.info("Initialized fdfs config with host {}", Arrays.toString(fdfsConfig.getTrackerServers()));
        } catch (Exception e) {
            logger.error("Initializing fdfs config error. {}", e.getMessage());
        }
    }
}