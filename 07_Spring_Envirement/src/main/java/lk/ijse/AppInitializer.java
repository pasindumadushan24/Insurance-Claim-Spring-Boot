package lk.ijse;

import lk.ijse.Been.SpringBeen;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AppInitializer {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringBeen.class);
        context.getBean(SpringBeen.class);
        context.close();

        Map<String,String> systemVariables = System.getenv();
        for (String key : systemVariables.keySet()) {
            System.out.println(key + " = " + systemVariables.get(key));
        }
    }
}