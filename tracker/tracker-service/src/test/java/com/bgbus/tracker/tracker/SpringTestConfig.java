package com.bgbus.tracker.tracker;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.tracker.model.Tracker;


@Configuration
@PropertySource("classpath:com/bgbus/tracker/tracker/TestService.properties")
@SpringBootApplication(scanBasePackages = { "org.chenile.configuration", "com.bgbus.tracker.tracker.configuration" })
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer{
	
}

