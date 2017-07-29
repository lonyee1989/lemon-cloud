package cn.lemon.security.config;

import cn.lemon.security.core.AuthorizationManager;
import cn.lemon.security.store.IAuthzStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by lonyee on 2017/6/7.
 */
@Configuration
public class SecurityConfiguration {
    @Resource
    public IAuthzStore authStoreService;

    @Bean
    public AuthorizationManager authorizationManager() {
        return new AuthorizationManager(authStoreService);
    }
}
