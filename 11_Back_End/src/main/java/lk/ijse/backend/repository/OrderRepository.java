package lk.ijse.backend.repository;

import lk.ijse.backend.Entity.Customer;
import lk.ijse.backend.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
