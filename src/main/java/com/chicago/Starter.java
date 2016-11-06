package com.chicago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * User: Pavel Gaev <pavel.gaev@firstlinesoftware.com>
 * Date: 19.03.2016
 * Time: 12:17
 */

@Configuration
@ComponentScan("com.chicago.config")
@EnableAutoConfiguration
//@SpringBootApplication
public class Starter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Starter.class, args);
    }


}
