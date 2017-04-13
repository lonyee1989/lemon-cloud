package cn.lemon.security.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/** 
 * 数据源配置
 * @author lonyee
 */
@Configuration
public class DatabaseConfiguration {
	private static Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Primary
	@Bean(name= "dataSource", destroyMethod= "close", initMethod="init")
    @ConfigurationProperties(prefix="spring.dataSource")
    public DataSource dataSource() {
		logger.debug("Configruing DataSource");
		return new DruidDataSource();
    }

	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}
}
