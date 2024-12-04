package com.bgbus.tracker.bus;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.bus.model.Bus;


@Configuration
@PropertySource("classpath:com/bgbus/tracker/bus/TestService.properties")
@SpringBootApplication(scanBasePackages = { "org.chenile.configuration", "com.bgbus.tracker.bus.configuration" })
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer{
	
}

