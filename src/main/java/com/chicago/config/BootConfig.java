package com.chicago.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by Pavel.
 * Date: 14.02.2016 20:23.
 */
@Configuration
@PropertySources({@PropertySource("classpath:properties/application.properties")})
//        @PropertySource("classpath:properties/webapp.properties")})
@ComponentScan({"com.chicago.controller", "com.chicago.entity", "com.chicago.dao", "com.chicago.service", "com.chicago.dto"})
public class BootConfig {

}
