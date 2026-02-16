package lk.ijse.backend.Controller;

import lk.ijse.backend.DTO.CustomerDTO;
import lk.ijse.backend.DTO.OrderDTO;
import lk.ijse.backend.ServiceLayer.OrderService;
import lk.ijse.backend.Utill.APIResponse;
import lk.ijse.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<APIResponse<String>> placeOrder(@RequestBody OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(new APIResponse<>(201, "Order Placed", null), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<OrderDTO>>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();

        return new ResponseEntity<>(new APIResponse<>(200, "Customers retrieved successfully", orders), HttpStatus.OK);
    }


    @GetMapping("/nextId")
    public ResponseEntity<APIResponse<Long>> getNextId() {
        return new ResponseEntity<>(new APIResponse<>(200, "Success", orderRepository.findNextId()), HttpStatus.OK);
    }
}