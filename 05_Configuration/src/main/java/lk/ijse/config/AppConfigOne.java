package lk.ijse.config;

import org.example.bean.A;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigOne {
    public AppConfigOne() {
        System.out.println("AppConfigOne Constructor");
    }
    @Bean
    public A a(){
        return new A();
    }

     @Bean
    public B b(){
        return new B();
    }
}