package cn.lemon.cloud.consumer.rpc.hytrix;

import cn.lemon.cloud.consumer.rpc.feign.IComputeService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 当服务接口访问异常时候访问该实现类
 * Created by lonyee on 2017/4/6.
 */
@Service
public class ComputeServiceFallbackFactory implements FallbackFactory<IComputeService> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public IComputeService create(Throwable throwable) {
        logger.error("FeignClient api [{}] error.", this.toString(), throwable);

        return new IComputeService() {
            @Override
            public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
                return -9999;
            }
        };
    }


}
