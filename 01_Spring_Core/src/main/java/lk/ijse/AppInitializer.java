package lk.ijse;

import lk.ijse.been.MyConnection;
import lk.ijse.been.SpringBean;
import lk.ijse.been.TestBean;
import lk.ijse.config.AppConfig;
import lk.ijse.newBeans.NewTestBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();


//        SpringBean springBean1 = new SpringBean(); // POJO
//        SpringBean springBean2 = new SpringBean(); // POJO
//        System.out.println(springBean1);
//        System.out.println(springBean2);
//
//
//        SpringBean springBean3 = context.getBean(SpringBean.class);
//        SpringBean springBean4 = context.getBean(SpringBean.class);
//        System.out.println("bean : " + springBean3);
//        System.out.println("bean : " + springBean4);


        SpringBean springBean = context.getBean(SpringBean.class);
        System.out.println("Bean" + springBean);

        TestBean testBean = (TestBean) context.getBean("testBean");
        System.out.println("bean" + testBean);


        TestBean testBean2 = (TestBean) context.getBean("testBean");
        System.out.println("bean" + testBean2);

        TestBean testBean3 = (TestBean) context.getBean("testBean");
        System.out.println("bean" + testBean3);




        testBean.printMessage();
        testBean2.printMessage();
        testBean3.printMessage();

        NewTestBean newTestBean = (NewTestBean) context.getBean("newTestBean");
        System.out.println("NewTestBean : " + newTestBean);

        MyConnection myConnection = (MyConnection) context.getBean("myConnection");
        System.out.println("Myconnection : " + myConnection);

        context.registerShutdownHook();
    }
}