package lk.ijse.been;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
    public TestBean() {
        System.out.println("TestBean");
    }

public void printMessage() {
        System.out.println("TestBean");
}
}
