package lk.ijse.config;

import lk.ijse.Been.SpringBeen;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration


@ComponentScan(basePackages = "lk.ijse.Been")
@PropertySource("classpath:application.prperties")
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig");
    }
}
