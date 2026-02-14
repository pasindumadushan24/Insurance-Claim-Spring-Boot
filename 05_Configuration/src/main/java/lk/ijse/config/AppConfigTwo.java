package lk.ijse.config;


import lk.ijse.Been.C;
import lk.ijse.Been.D;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigTwo {
 public AppConfigTwo(){
    System.out.println("AppconfigTwo");
 }

 
  @Bean
    public C c(){
        return new C();
    }

     @Bean
    public D d(){
        return new D();
    }
}


