package cn.lemon.cloud.consumer.rpc.hytrix;

import cn.lemon.cloud.consumer.rpc.feign.IComputeClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 当服务接口访问异常时候访问该实现类
 * Created by lonyee on 2017/4/6.
 */
@Component
public class ComputeClientHystrix implements IComputeClient {

    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -9999;
    }
}
