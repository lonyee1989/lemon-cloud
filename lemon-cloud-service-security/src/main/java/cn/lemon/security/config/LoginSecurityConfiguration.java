package cn.lemon.security.config;

import cn.lemon.security.service.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户登录权限
 * Created by lonyee on 2017/4/10.
 */
 @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
 @Configuration
 @EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 注册UserDetailsService 的bean
     */
    @Bean
    protected UserDetailsService userDetailsService(){
        return new UserService();
    }

    /**
     * user Details Service验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico", "/login", "/oauth/authorize", "/oauth/confirm_access");
    }


    /**
     * 拦截请求配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.and()
                //.requestMatchers()
                //.antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index")  //设置默认登录成功跳转页面
                .permitAll() //登录页面用户任意访问
                .and()
                .rememberMe()  //开启cookie保存用户数据
                .tokenValiditySeconds(60 * 60 * 24 * 7)  //设置cookie有效期 7 天
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login").permitAll(); //注销行为任意访问
    }
}