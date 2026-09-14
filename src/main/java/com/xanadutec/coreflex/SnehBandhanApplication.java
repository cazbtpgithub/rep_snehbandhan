package com.xanadutec.coreflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ImportResource({
    "classpath:spring/servlet-context.xml",
    "classpath:spring/hibernate-context.xml",
    "classpath:spring/application-security.xml"
})
public class SnehBandhanApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SnehBandhanApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SnehBandhanApplication.class, args);
    }
}
