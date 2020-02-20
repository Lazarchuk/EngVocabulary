package voc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import voc.dao.DataAbstractFactory;

@Configuration
@EnableWebMvc
@ComponentScan("voc.controller")
@PropertySource("classpath:datasource.properties")
public class BeanConfig implements WebMvcConfigurer {

    @Autowired
    Environment env;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(20);        //SECONDS
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("sourceUrl"));
        dataSource.setUsername(env.getProperty("dbUsername"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }

    @Bean
    public DataAbstractFactory dataAbstractFactory(){
        return DataAbstractFactory.getFactory(driverManagerDataSource());
    }
}
