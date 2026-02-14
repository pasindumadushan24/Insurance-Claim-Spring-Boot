package lk.ijse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Hello")
public class HelloController {
    public HelloController(){
        System.out.println("HelloController Constructor");
    }
    @GetMapping
    public String hello(){
        return "index";
    }
}
