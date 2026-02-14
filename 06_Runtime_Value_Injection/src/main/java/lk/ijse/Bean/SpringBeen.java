package lk.ijse.Bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SpringBeen  {

    @Autowired(required=false)
    public SpringBeen (@Value("Madushan")String namelist[],@Value("12")int number) {
        System.out.println(namelist.length);
        System.out.println(number);
    }

    @Autowired(required=false)
    public SpringBeen (@Value("Pasindu")String name,@Value("21") int age) {
        System.out.println(name);
        System.out.println(age);
    }






}
