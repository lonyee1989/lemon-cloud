package cn.lemon.cloud.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lonyee on 2017/4/6.
 */
@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private DiscoveryClient client;

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public String add(@RequestParam String v) {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/test, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + v);
        return v;
    }
}
