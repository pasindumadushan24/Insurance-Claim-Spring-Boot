package lk.ijse.backend.ServiceLayer;

import lk.ijse.backend.DTO.OrderDTO;
import java.util.List;

public interface OrderService {
    void placeOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
}