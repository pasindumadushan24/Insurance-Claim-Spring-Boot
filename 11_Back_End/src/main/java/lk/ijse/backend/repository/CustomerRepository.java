package lk.ijse.backend.repository;

import lk.ijse.backend.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT COALESCE(MAX(c.cid), 0) + 1 FROM Customer c")
    Long findNextId();
}