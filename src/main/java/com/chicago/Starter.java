package com.chicago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * User: Pavel Gaev <pavel.gaev@firstlinesoftware.com>
 * Date: 19.03.2016
 * Time: 12:17
 */

@EnableAutoConfiguration
@ComponentScan("com.chicago.config")
public class Starter {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Starter.class, args);
    }


}
