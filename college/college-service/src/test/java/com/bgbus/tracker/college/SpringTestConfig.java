package com.bgbus.tracker.college;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.college.model.College;


@Configuration
@PropertySource("classpath:com/bgbus/tracker/college/TestService.properties")
@SpringBootApplication(scanBasePackages = { "org.chenile.configuration", "com.bgbus.tracker.college.configuration" })
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer{
	
}

