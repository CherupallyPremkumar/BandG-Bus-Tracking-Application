package com.bgbus.tracker.student;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.student.model.Student;


@Configuration
@PropertySource("classpath:com/bgbus/tracker/student/TestService.properties")
@SpringBootApplication(scanBasePackages = { "org.chenile.configuration", "com.bgbus.tracker.student.configuration" })
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer{
	
}

