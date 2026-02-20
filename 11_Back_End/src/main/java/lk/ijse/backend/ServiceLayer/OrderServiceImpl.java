package lk.ijse.backend.ServiceLayer;

import jakarta.transaction.Transactional;
import lk.ijse.backend.DTO.OrderDTO;
import lk.ijse.backend.DTO.OrderDetailDTO;
import lk.ijse.backend.Entity.Customer;
import lk.ijse.backend.Entity.Item;
import lk.ijse.backend.Entity.OrderDetail;
import lk.ijse.backend.Entity.Orders;
import lk.ijse.backend.repository.CustomerRepository;
import lk.ijse.backend.repository.ItemRepository;
import lk.ijse.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

@Override
@Transactional
public void placeOrder(OrderDTO orderDTO) {

    Customer customer = customerRepository.findById(orderDTO.getCid())
            .orElseThrow(() -> new RuntimeException("Customer not found"));


    Orders order = new Orders();
    order.setDate(orderDTO.getDate());
    order.setCustomer(customer);

    List<OrderDetail> detailsList = new ArrayList<>();

    for (OrderDetailDTO dto : orderDTO.getOrderDetails()) {

        Item item = itemRepository.findById(dto.getIid())
                .orElseThrow(() -> new RuntimeException("Item not found: " + dto.getIid()));


        if (item.getQty() < dto.getQty()) {
            throw new RuntimeException("Insufficient stock for item: " + item.getIid());
        }


        item.setQty(item.getQty() - dto.getQty());
        itemRepository.save(item);


        OrderDetail detail = new OrderDetail();
        detail.setOrders(order);
        detail.setItem(item);
        detail.setQty(dto.getQty());
        detail.setUnitPrice(dto.getUnitPrice());

        detailsList.add(detail);
    }


    order.setOrderDetails(detailsList);


    orderRepository.save(order);
}




    @Override
    public List<OrderDTO> getAllOrders() {
        List<Orders> all = orderRepository.findAll();
        List<OrderDTO> dtos = new ArrayList<>();

        for (Orders o : all) {

            OrderDTO dto = new OrderDTO();
            dto.setOid(o.getOid());
            dto.setDate(o.getDate());
            dto.setCid(o.getCustomer().getCid());

            dtos.add(dto);
        }
        return dtos;
    }
}