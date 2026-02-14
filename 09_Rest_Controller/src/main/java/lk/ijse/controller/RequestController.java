package lk.ijse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/request")
public class RequestController {
    @PostMapping
    public String post(@RequestHeader("AuthoriZation")String authoriZation, @RequestHeader("token")String token) {

        return "Hello World" + authoriZation + " +  : Token :" + token;
    }
    @PostMapping("querystring")
    public String getQuarStringParameters(@RequestParam("name") String name,
                                          @RequestParam("address") String address,
                                          @RequestParam("id") System id){
        return "Hello World" + name + address + id;
    }


}
