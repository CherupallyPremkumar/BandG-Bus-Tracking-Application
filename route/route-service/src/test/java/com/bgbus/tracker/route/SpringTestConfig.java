package com.bgbus.tracker.route;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.route.model.Route;


@Configuration
@PropertySource("classpath:com/bgbus/tracker/route/TestService.properties")
@SpringBootApplication(scanBasePackages = { "org.chenile.configuration", "com.bgbus.tracker.route.configuration" })
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer{
	
}

