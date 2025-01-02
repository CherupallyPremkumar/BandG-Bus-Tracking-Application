package com.bgbus.tracker;



import com.bgbus.tracker.bus.configuration.dao.AttendanceRepository;
import com.bgbus.tracker.bus.configuration.dao.BusRepository;
import com.bgbus.tracker.busstop.configuration.dao.BusstopRepository;
import com.bgbus.tracker.configuration.AppConfigConstants;
import com.bgbus.tracker.route.configuration.dao.RouteRepository;
import com.bgbus.tracker.student.configuration.dao.StudentRepository;
import com.bgbus.tracker.tracker.configuration.dao.TrackerRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = { "com.bgbus.tracker.**",
        "org.chenile.configuration.**" })
@EntityScan(basePackages = {"com.bgbus.tracker.route.model.**","com.bgbus.tracker.bus.model","com.bgbus.tracker.student.model",
                          "com.bgbus.tracker.tracker.model","com.bgbus.tracker.busstop.model","com.bgbus.tracker.college.model"})
@EnableJpaRepositories(basePackageClasses = {BusstopRepository.class,BusRepository.class, TrackerRepository.class, StudentRepository.class, RouteRepository.class, AttendanceRepository.class})
public class BuildApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(BuildApplication.class);

    private final Environment env;

    public BuildApplication(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (
                activeProfiles.contains(AppConfigConstants.SPRING_PROFILE_DEVELOPMENT) &&
                        activeProfiles.contains(AppConfigConstants.SPRING_PROFILE_PRODUCTION)
        ){
            log.error(
                    "You have misconfigured your application! It should not run " + "with both the 'dev' and 'prod' profiles at the same time."
            );
        }
        if (
                activeProfiles.contains(AppConfigConstants.SPRING_PROFILE_DEVELOPMENT) &&
                        activeProfiles.contains(AppConfigConstants.SPRING_PROFILE_CLOUD)
        ) {
            log.error(
                    "You have misconfigured your application! It should not " + "run with both the 'dev' and 'cloud' profiles at the same time."
            );
        }
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BuildApplication.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }
    private static void logApplicationStartup(Environment env) {
        String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
        String applicationName = env.getProperty("spring.application.name");
        String serverPort = env.getProperty("server.port");
        String contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path"))
                .filter(StringUtils::isNotBlank)
                .orElse("/");
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info(
                """
    
                ----------------------------------------------------------
                \tApplication '{}' is running! Access URLs:
                \tLocal: \t\t{}://localhost:{}{}
                \tExternal: \t{}://{}:{}{}
                \tProfile(s): \t{}
                ----------------------------------------------------------""",
                applicationName,
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles()
        );
    }

}