package com.ts.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.naming.NameParser;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DruidConfig {
    @Bean(name = "mainCon") //注入到spring容器中
    public DataSource druid(Environment env){
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        Properties prop = build(env, "spring.datasource.druid.","main.");
        xaDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        xaDataSource.setUniqueResourceName("main");
        xaDataSource.setXaProperties(prop);
        return xaDataSource;
    }
    //2 配置druid的监控（配置管理后台servlet）
    @Bean
    public ServletRegistrationBean StatViewServlet(Environment env){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        bean.addInitParameter("loginUsername",env.getProperty("ts.druid.loginUserName"));
        bean.addInitParameter("loginPassword",env.getProperty("ts.druid.loginPassWord"));
        return  bean;
    }
    //3配置一个filter
    @Bean
    public FilterRegistrationBean WebStatFilter(){
        //idea 方法返回类型 alt+enter
        //eclipse  alt +shirt +L
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter()); //注册filter
        Map<String,String>map = new HashMap<>();

        map.put("exclusions","*.js,*.css,*.gif,*.jpg,*.bmp,*.png,*.ico,/druid/*"); //不拦截 比如静态资源

        bean.setInitParameters(map);

        bean.setUrlPatterns(Arrays.asList("/*"));  //拦截所有请求

        return  bean;
    }

    /**
     * 注入事务支持
     * @return
     */
    @Bean(name = "transactionManager")
    public JtaTransactionManager regTransactionManager()throws Exception{
        UserTransactionManager userTransactionManager=new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        UserTransactionImp userTransactionImp=new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        JtaTransactionManager jta=new JtaTransactionManager(userTransactionImp,userTransactionManager);
        return jta;
    }

    @Bean
    NamedParameterJdbcTemplate commonTemplate(@Autowired()@Qualifier("mainCon") DataSource ds){
        return new NamedParameterJdbcTemplate(ds);
    }

    public Properties build(Environment env, String prefix,String sStr) {
        Properties prop = new Properties();
        prop.put("driverClassName", env.getProperty(prefix+sStr+ "driver-class-name"));
        prop.put("url", env.getProperty(prefix+sStr+ "url"));
        prop.put("username", env.getProperty(prefix+sStr+ "username"));
        prop.put("password", env.getProperty(prefix+sStr+ "password"));
        prop.put("initialSize", env.getProperty(prefix + "initial-size", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "min-idle", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "max-active", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "max-wait", Integer.class));
        prop.put("timeBetweenEvictionRunsMillis", env.getProperty(prefix + "time-between-eviction-runs-millis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "min-evictable-idle-time-millis", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validation-query"));
        prop.put("testOnBorrow", env.getProperty(prefix + "test-on-borrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "test-on-return", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "test-while-idle", Boolean.class));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "pool-prepared-statements", Boolean.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "max-pool-prepared-statement-per-connection-size", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));
        return prop;
    }

}
