package com.fan;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@ComponentScan
@EnableAutoConfiguration(exclude = {})
@EnableConfigurationProperties({})
@SpringBootApplication
@ServletComponentScan
public class ConsumerApp {

	private static final Logger log = LoggerFactory.getLogger(ConsumerApp.class);

	public static void main(String[] args) throws UnknownHostException {

		SpringApplication app = new SpringApplication(ConsumerApp.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		addDefaultProfile(app, source);
		Environment env = app.run(args).getEnvironment();
		log.info(
				"Access URLs:\n----------------------------------------------------------\n\t"
						+ "Local: \t\thttp://127.0.0.1:{}\n\t"
						+ "External: \thttp://{}:{}\n----------------------------------------------------------",
				env.getProperty("server.port"), InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
	}

	/**
	 * If no profile has been configured, set by default the "dev" profile.
	 */
	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")
				&& !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

			app.setAdditionalProfiles("dev");
		}
	}
}
