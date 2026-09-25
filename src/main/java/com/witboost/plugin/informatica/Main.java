package com.witboost.plugin.informatica;

import com.witboost.plugin.informatica.common.config.JaywayJsonPathConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/** This is the Main class. */
@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.witboost.plugin.informatica")
public class Main {

    /** This is the main method which acts as the entry point inside the application. */
    public static void main(String[] args) {
        JaywayJsonPathConfigurer.configureJaywayJsonPath();
        SpringApplication.run(Main.class, args);
    }
}
