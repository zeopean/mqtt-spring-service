package com.mqtt.artqiyi;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zeopean
 */
@SpringBootApplication
@ComponentScan
@EnableConfigurationProperties
@EnableScheduling
public class AppApplication extends AppServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppApplication.class);
    }

    public static void main(String[] args) throws MqttException {
        SpringApplication app = new SpringApplication(AppApplication.class);
        app.run(args);
    }



}
