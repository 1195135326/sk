package com.ts.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.naming.NameParser;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")   //绑定配置文件的属性
    @Bean //注入到spring容器中
    public DataSource druid(){
        return new DruidDataSource();
    }
    //2 配置druid的监控（配置管理后台servlet）
    @Bean
    @Primary
    public ServletRegistrationBean StatViewServlet(){
        ServletRegistrationBean bean = new
                ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");

        Map<String,String> map = new HashMap<>();
        map.put("loginUsername","admin");  //登陆用户名
        map.put("loginPassword","123456");  //登陆密码
        //（不写就是允许所有访问）
//        map.put("allow","localhost");  //允许登陆ip（这样只能localhost访问）
//        map.put("deny","192.168.1.1");  //拒绝登陆地址

        bean.setInitParameters(map); //设置初始化参数（值是一个String map集合）
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

    @Bean
    NamedParameterJdbcTemplate commonTemplate(@Autowired DataSource ds){
        return new NamedParameterJdbcTemplate(ds);
    }

}
