package main.java.lk.config;

import javax.swing.Spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.bean")
public class AppConfig {
    @Been
    public SpringBeanOne springBeanOne{
        SpringBeanTwo springBeanTwo1 = springBeenTwo();
        SpringBeanTwo springBeanTwo2 = springBeenTwo();
        System.out.println(springBeanTwo1);
        System.out.println(springBeanTwo2);
        return new SpringBeenOne();
        
    }

     @Been
    public SpringBeanTwo springBeanTwo{
        return new SpringBeenTwo();
        
    }
}