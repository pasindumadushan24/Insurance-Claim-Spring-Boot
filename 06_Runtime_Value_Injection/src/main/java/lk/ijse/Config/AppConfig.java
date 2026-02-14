package lk.ijse.Config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "lk.ijse.Bean")

public class AppConfig {
    public AppConfig() {

        System.out.println("AppConfig Constructor");
    }
}