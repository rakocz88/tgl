package pl.pilaf.inz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import pl.pilaf.inz.config.AppConfig;
import pl.pilaf.inz.server.ServletContainerCustomizer;

@Configuration
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(new Object[]{AppConfig.class, ServletContainerCustomizer.class}, args);
    }
}
