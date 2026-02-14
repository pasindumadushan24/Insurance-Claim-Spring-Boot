package lk.ijse.backend.ServiceLayer;

import lk.ijse.backend.Entity.Customer;
import lk.ijse.backend.Entity.Order;
import jakarta.transaction.Transactional;
import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.DTO.OrderDTO;
import lk.ijse.backend.DTO.OrderItemDTO;
import lk.ijse.backend.repository.CustomerRepository;
import lk.ijse.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {


    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public void placeOrder(OrderDTO orderDTO) {
        double total = 0;

        Customer customer = customerRepository.findById(orderDTO.getCid())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        for (OrderItemDTO orderItem : orderDTO.getItems()) {
            ItemDTO item = itemService.getItemById(orderItem.getIid());
            if (orderItem.getQty() > item.getQty()) {
                throw new RuntimeException("Insufficient stock for item: " + item.getIName());
            }

            total += item.getPrice() * orderItem.getQty();
            itemService.reduceStock(orderItem.getIid(), orderItem.getQty());
        }

        Order order = new Order();
        order.setOid(orderDTO.getOid());
        order.setCustomer(customer);
        order.setDate(orderDTO.getDate());
        order.setTotal(total);


        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getOrderHistory() {
        return List.of();
    }


//    @Override
//    public List<OrderDTO> getOrderHistory() {
//        return orderRepository.findAll().stream()
//                .map(order -> new OrderDTO(
//                        order.getOid(),
//                        order.getCustomer().getCid(),
//                        order.getDate(),
//                        order.getTotal(),
//                        null
//                ))
//                .toList();
//    }
}




