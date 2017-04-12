package cn.lemon.cloud.security.config;

import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by lonyee on 2017/4/10.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableWebSecurity
public class LoginConfiguration extends WebSecurityConfigurerAdapter {

    //@Resource(name = "authenticationManager")
    //AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll()
                .and().requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
                .and().authorizeRequests().anyRequest().authenticated();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager);
    }*/
}