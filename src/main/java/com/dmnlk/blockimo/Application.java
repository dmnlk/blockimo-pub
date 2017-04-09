package com.dmnlk.blockimo;

import com.dmnlk.blockimo.config.EnvironmentConfig;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;


@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ImportResource("classpath:applicationContext.xml")
@Log4j
public class Application extends SpringBootServletInitializer {
    @Autowired
    private EnvironmentConfig environmentConfig;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(Application.class);
    }

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        log.info("baseUrl=" + environmentConfig.getBaseUrl());
        return super.run(application);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletContextInitializer servletContextInitializer(@Value("${secure.cookie}")boolean secure) {
        ServletContextInitializer servletContextInitializer = new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.getSessionCookieConfig().setHttpOnly(true);
                servletContext.getSessionCookieConfig().setSecure(secure);
                servletContext.setSessionTrackingModes(
                        Collections.singleton(SessionTrackingMode.COOKIE)
                );
            }
        };
        return servletContextInitializer;
    }
}
