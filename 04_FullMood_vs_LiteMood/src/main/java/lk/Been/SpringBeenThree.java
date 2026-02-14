import org.springframework.context.annotation.Configuration;


@Component
public class SpringBeenThree {
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
