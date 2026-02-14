package lk.ijse.config;


import lk.ijse.been.MyConnection;
import lk.ijse.been.SpringBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"lk.ijse.been","lk.ijse.newBeans" })
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig");
    }
    @Bean
    MyConnection myConnection() {
        return new MyConnection();
    }


}
