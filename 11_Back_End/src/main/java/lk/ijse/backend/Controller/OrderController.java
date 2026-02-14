package lk.ijse.backend.Controller;

import lk.ijse.backend.DTO.OrderDTO;
import lk.ijse.backend.ServiceLayer.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/Order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void placeOrder(@RequestBody OrderDTO orderDTO){
        orderService.placeOrder(orderDTO);
    }

    @GetMapping
    public List<OrderDTO> getOrderHistory(){
        return orderService.getOrderHistory();
    }
}
