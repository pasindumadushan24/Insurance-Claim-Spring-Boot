package lk.ijse.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
    public SpringBean() {
        System.out.println("SpringBean");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        
    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
