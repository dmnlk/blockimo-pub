package com.dmnlk.blockimo.batch;


import lombok.extern.log4j.Log4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableBatchProcessing
@EnableConfigurationProperties
@Log4j
@ComponentScan
public class SampleJob implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SampleJob.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
       log.error("run batch");
    }
}
