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

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.detailsList;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

//    @Override
//    public void placeOrder(OrderDTO orderDTO) {
//        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        Orders order = new Orders();
//        order.setDate(orderDTO.getDate());
//        order.setCustomer(customer);
//
//        List<OrderDetail> details = new ArrayList<>();
//        for (OrderDetailDTO dto : orderDTO.getOrderDetails()) {
//            Item item = itemRepository.findById(dto.getItemId())
//                    .orElseThrow(() -> new RuntimeException("Item not found: " + dto.getItemId()));
//
//            // Stock පරීක්ෂාව සහ අඩු කිරීම
//            if (item.getQty() < dto.getQty()) {
//                throw new RuntimeException("Insufficient stock for item: " + item.getIid());
//            }
//            item.setQty(item.getQty() - dto.getQty());
//            itemRepository.save(item); // Item stock update කිරීම
//
//            OrderDetail detail = new OrderDetail();
//            detail.setOrders(order); // Parent Order එක සම්බන්ධ කිරීම (මෙය වැදගත්!)
//            detail.setItem(item);
//            detail.setQty(dto.getQty());
//            detail.setUnitPrice(dto.getUnitPrice());
//
////            detailsList.add(detail);
//        }
//    }

@Override
@Transactional
public void placeOrder(OrderDTO orderDTO) {
    // 1. Customer සොයා ගැනීම
    Customer customer = customerRepository.findById(orderDTO.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    // 2. නව Order එකක් සෑදීම
    Orders order = new Orders();
    order.setDate(orderDTO.getDate());
    order.setCustomer(customer);

    List<OrderDetail> detailsList = new ArrayList<>();

    for (OrderDetailDTO dto : orderDTO.getOrderDetails()) {
        // 3. Item එක සොයා ගැනීම
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found: " + dto.getItemId()));

        // 4. Stock පරීක්ෂාව
        if (item.getQty() < dto.getQty()) {
            throw new RuntimeException("Insufficient stock for item: " + item.getIid());
        }

        // 5. Stock අඩු කිරීම සහ Update කිරීම
        item.setQty(item.getQty() - dto.getQty());
        itemRepository.save(item);

        // 6. Order Detail එක සෑදීම
        OrderDetail detail = new OrderDetail();
        detail.setOrders(order); // Parent එක සම්බන්ධ කිරීම
        detail.setItem(item);
        detail.setQty(dto.getQty());
        detail.setUnitPrice(dto.getUnitPrice());

        detailsList.add(detail); // List එකට එකතු කිරීම
    }

    // 7. Order එකට Details List එක ඇතුළු කිරීම
    order.setOrderDetails(detailsList);

    // 8. Order එක Save කිරීම (Cascade නිසා Details ද Save වේ)
    orderRepository.save(order);
}




    @Override
    public List<OrderDTO> getAllOrders() {
        List<Orders> all = orderRepository.findAll();
        List<OrderDTO> dtos = new ArrayList<>();

        for (Orders o : all) {
            // මෙහිදී සරලව mapping එකක් සිදු කරන්න
            OrderDTO dto = new OrderDTO();
            dto.setOid(o.getOid());
            dto.setDate(o.getDate());
            dto.setCustomerId(o.getCustomer().getCid());
            // Details අවශ්‍ය නම් ඒවාද loop එකකින් එකතු කරන්න
            dtos.add(dto);
        }
        return dtos;
    }
}