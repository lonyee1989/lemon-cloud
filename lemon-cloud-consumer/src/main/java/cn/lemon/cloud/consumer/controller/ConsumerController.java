package cn.lemon.cloud.consumer.controller;

import cn.lemon.cloud.consumer.rpc.feign.IComputeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lonyee on 2017/4/6.
 */
@RestController
public class ConsumerController {
    @Resource
    IComputeService computeClient;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(Integer a, Integer b) {
        return computeClient.add(a, b);
    }
}
